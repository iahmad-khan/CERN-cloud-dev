// Package main implements a dhcp server talking to the CERN landb.
//
// It replies to DISCOVER requests with the IP matching the given MAC address
// (by asking landb). It then confirms the given IP from the client on a
// REQUEST.
package main

import (
	"flag"
	"fmt"
	"log"
	"net"
	"os/exec"
	"regexp"
	"strconv"
	"strings"
	"time"

	dhcp "github.com/krolaw/dhcp4"
)

func main() {

	var landbHost = flag.String("landb-host", "localhost", "host of the landb server")
	var landbPort = flag.Int("landb-port", 8443, "port of the landb server")
	var serverIP = flag.String("listen-ip", "0.0.0.0", "ip of the dhcp server (this server)")
	var serverIface = flag.String("listen-iface", "docker0", "interface name of the dhcp server (this server)")

	flag.Parse()

	server := net.ParseIP(*serverIP)
	handler := &DHCPHandler{
		ip:            server,
		leaseDuration: 2 * time.Hour,
		leaseRange:    50,
		options: dhcp.Options{
			dhcp.OptionSubnetMask:       []byte{255, 255, 255, 0},
			dhcp.OptionRouter:           []byte(*serverIP), // Presuming Server is also your router
			dhcp.OptionDomainNameServer: []byte(*serverIP), // Presuming Server is also your DNS server
		},
		landbArgs: []string{"--landb-host", *landbHost, "--landb-port", strconv.Itoa(*landbPort),
			"--landb-user", "dummy", "--landb-password", "123456"},
	}
	log.Fatal(dhcp.ListenAndServeIf(*serverIface, handler))

}

// DHCPHandler holds common info to be used later.
type DHCPHandler struct {
	ip            net.IP        // Server IP to use
	options       dhcp.Options  // Options to send to DHCP Clients
	leaseRange    int           // Number of IPs to distribute (starting from start)
	leaseDuration time.Duration // Lease period
	landbArgs     []string      // Default landb arguments
}

// ServeDHCP is the DHCP server implementation.
// It servers DISCOVER, REQUEST and RELEASE, using the landb client to fetch
// the information containing the mac/ip correlation.
// Returns a DHCP packet matching the request.
func (h *DHCPHandler) ServeDHCP(p dhcp.Packet, msgType dhcp.MessageType, options dhcp.Options) (d dhcp.Packet) {

	mac := p.CHAddr().String()

	switch msgType {

	case dhcp.Discover:

		fmt.Printf("dhcp discover :: %v :: start\n", mac)

		device, err := h.DeviceSearch(mac)
		if err != nil {
			fmt.Printf("dhcp discover :: %v :: search :: %v\n", mac, err)
			return nil
		}

		ip, err := h.DeviceIP(device)
		if err != nil {
			fmt.Printf("dhcp discover :: %v :: device_ip :: %v\n", mac, err)
			return nil
		}
		fmt.Printf("dhcp discover :: %v :: %v :: done\n", mac, ip)

		p := dhcp.ReplyPacket(p, dhcp.Offer, h.ip, net.ParseIP(ip), h.leaseDuration,
			h.options.SelectOrderOrAll(options[dhcp.OptionParameterRequestList]))
		return p

	case dhcp.Request:
		fmt.Printf("dhcp request :: %v\n", mac)
		// FIXME: OptionServerIdentifier is coming as 0.0.0.0, need to fix this check
		//if server, ok := options[dhcp.OptionServerIdentifier]; ok && !net.IP(server).Equal(h.ip) {
		//	fmt.Printf("dhcp request :: %v :: wrong server :: %v\n", server)
		//	return nil // Message not for this dhcp server
		//}
		reqIP := net.IP(options[dhcp.OptionRequestedIPAddress])
		fmt.Printf("dhcp request :: %v :: %v\n", mac, reqIP)
		if reqIP == nil {
			reqIP = net.IP(p.CIAddr())
		}

		if len(reqIP) == 4 && !reqIP.Equal(net.IPv4zero) {
			return dhcp.ReplyPacket(p, dhcp.ACK, h.ip, net.IP(options[dhcp.OptionRequestedIPAddress]), h.leaseDuration,
				h.options.SelectOrderOrAll(options[dhcp.OptionParameterRequestList]))
		}
		return dhcp.ReplyPacket(p, dhcp.NAK, h.ip, nil, 0, nil)

	case dhcp.Release, dhcp.Decline:
	}
	return nil
}

// DeviceSearch returns the device IP for the given mac.
// Think landb dev-search --mac <mac-address>.
func (h *DHCPHandler) DeviceSearch(mac string) (string, error) {
	args := h.landbArgs
	args = append(args, "dev-search", "--mac", mac)
	cmd := exec.Command("landb", args...)
	out, err := cmd.Output()
	if err != nil {
		return "", fmt.Errorf("%v :: %v", string(out), err)
	}
	return strings.TrimSpace(string(out)), nil
}

// DeviceIP returns the IP address matching the given hostname.
// Think landb dev-info | grep IPAddress.
func (h *DHCPHandler) DeviceIP(device string) (string, error) {
	args := h.landbArgs
	args = append(args, "dev-info", device)
	cmd := exec.Command("landb", args...)
	out, err := cmd.CombinedOutput()
	if err != nil {
		return "", fmt.Errorf("%v :: %v", string(out), err)
	}

	reg, err := regexp.Compile(`(?ms)(.*IPAddress = ")(?P<ipaddress>\d+.\d+.\d+.\d+)(".*)`)
	result := reg.FindAllStringSubmatch(string(out), -1)
	ip := result[0][2]

	return ip, nil

}
