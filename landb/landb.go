// Package landb provides a mock server of the CERN landb setup.
//
// landb is the internal CERN service handling networking devices.
//
// Author: Ricardo Rocha <ricardo.rocha@cern.ch>
package main

import (
	"crypto/tls"
	"crypto/x509"
	"encoding/json"
	"encoding/xml"
	"flag"
	"fmt"
	"io/ioutil"
	"net/http"
	"os"
	"regexp"
	"strings"
	"time"
)

var (
	// Port is the port the server listens at
	Port = flag.Int("port", 443, "port server will listen")
	// Host is the hostname (ip address) the server listens at
	Host = flag.String("host", "0.0.0.0", "ip or hostname the server will listen at")
	// PubCert is the location of the server's public certificate
	PubCert = flag.String("pubcert", "", "location of the public certificate for the server")
	// PrivCert is the location of the server's private certificate
	PrivCert = flag.String("privcert", "", "location of the private certificate for the server")
	// CaCert is the location of the server's CA certificate
	CaCert = flag.String("cacert", "", "location of the CA root certificate for the server")
	// Db is the location of the file with the data to be preloaded
	Db = flag.String("db", "", "location of db with data to preload")
	// Sleep is the number of seconds to sleep before starting the server
	Sleep = flag.Int("sleep", 0, "sleep seconds before starting server")
)

// Location is the device's location
type Location struct {
	Building string
	Floor    string
	Room     string
}

// OperatingSystem is the device's OS
type OperatingSystem struct {
	Name    string
	Version string
}

// PersonInput has the person details
type PersonInput struct {
	Name       string
	FirstName  string
	Department string
	Group      string
	PersonID   int
}

// DeviceInput contains device details
type DeviceInput struct {
	DeviceName        string
	Location          Location
	Zone              string
	Manufacturer      string
	Model             string
	Description       string
	Tag               string
	SerialNumber      string
	OperatingSystem   OperatingSystem
	InventoryNumber   string
	ResponsiblePerson PersonInput
	UserPerson        PersonInput
	HCPResponse       bool
	IPv6Ready         bool
}

// InterfaceCard holds interface card details
type InterfaceCard struct {
	HardwareAddress string
	CardType        string
}

// VMOptions holds additional info about a VM
type VMOptions struct {
	IP                   string
	IPv6                 string
	ServiceName          string
	InternetConnectivity string
	AddressType          string
	IsExtManaged         bool
}

// Auth holds the token
type Auth struct {
	token string
}

// VMClusterList is a list of clusters
type VMClusterList struct {
	Items   []Item `xml:"item"`
	Soapenc string `xml:"SOAP-ENC:arrayType,attr,omitempty"`
	Xsitype string `xml:"xsi:type,attr,omitempty"`
}

type vmGetClusterMembership struct {
	DeviceName string `xml:"DeviceName"`
}

// Item is
type Item struct {
	Value string `xml:",chardata"`
	XSI   string `xml:"xsi:type,attr"`
}

// New returns a new SecretHandler instance.
// db is the location of the json database file
// directory where file secrets are stored.
func New(db string) (*LandbHandler, error) {
	sh := LandbHandler{}
	err := sh.LoadDB(db)
	if err != nil {
		fmt.Printf("failed to reload json secret db :: %v", err)
	}
	// enable refresh of json secrets without restart
	ticker := time.NewTicker(5 * time.Second)
	quit := make(chan struct{})
	go func() {
		for {
			select {
			case <-ticker.C:
				err := sh.LoadDB(db)
				if err != nil {
					fmt.Printf("failed to reload json secret db :: %v", err)
				}
			case <-quit:
				ticker.Stop()
				return
			}
		}
	}()

	return &sh, nil
}

// LandbHandler handles requests.
type LandbHandler struct {
	// Results contains the data to be retrieved for each request
	Results map[string]string
}

// LoadDB loads the data from the json file at location.
func (ldbh *LandbHandler) LoadDB(location string) error {
	f, err := ioutil.ReadFile(location)
	if err != nil {
		return fmt.Errorf("Failed to load db :: %v\n", err)
	}
	err = json.Unmarshal(f, &ldbh.Results)
	if err != nil {
		return fmt.Errorf("Failed to load dd :: %v\n", err)
	}
	return nil
}

// ServeHTTP is the LandbHandler implementation of http.Server.ServerHTTP.
func (ldbh *LandbHandler) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	query := r.URL.RawQuery
	var resp string

	bodyb, err := ioutil.ReadAll(r.Body)
	if err != nil {
		return
	}
	body := string(bodyb)

	re, err := regexp.Compile("(?m)(?:.*:Body.*><.*:)(.*)(?:>.*:Body>)")
	if err != nil {
		fmt.Printf("%v\n", err)
		return
	}
	reqn := re.FindStringSubmatch(body)[1]
	xmlreq := ldbh.getRequestXML(reqn, body)
	fmt.Printf("%v :: %v\n", reqn, xmlreq)

	if strings.Contains(query, "WSDL") || strings.Contains(query, "wsdl") {
		if f, err := ioutil.ReadFile("/data/landb.wsdl"); err == nil {
			resp = string(f)
		}
	} else {
		switch reqn {
		case "getAuthTokenResponse":
			resp = ldbh.buildResponse("getAuthToken")
		case "vmGetClusterMembership":
			s := vmGetClusterMembership{}
			xml.Unmarshal([]byte(xmlreq), &s)
			resp = ldbh.getResponse("vmGetClusterMembership",
				VMClusterList{Items: []Item{
					Item{Value: "CLUSTER1", XSI: "xsd:string"},
					//Item{Value: "CLUSTER2", XSI: "xsd:string"},
				},
					Soapenc: "xsd:string[1]", Xsitype: "SOAP-ENC:Array"})
		}
	}

	fmt.Fprintf(w, "%v", string(resp))
}

func (ldbh *LandbHandler) getRequestXML(method string, body string) string {
	re, _ := regexp.Compile(fmt.Sprintf("(?m)(?:%s>)(?P<xml>.*)(?:<.*:%s)", method, method))
	return re.FindStringSubmatch(body)[1]
}

func (ldbh *LandbHandler) getResponse(method string, v interface{}) string {
	xmlresp, _ := xml.Marshal(v)
	resp := fmt.Sprintf("<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Envelope SOAP-ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><SOAP-ENV:Body><%sResponse>%s</%sResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>", method, xmlresp, method)
	return resp
}

func (ldbh *LandbHandler) buildResponse(method string) string {

	resp := fmt.Sprintf("%s", ldbh.Results[method])

	return resp

}

func getServer(db string, ca string) (*http.Server, error) {
	mTLSConfig := &tls.Config{
		CipherSuites: []uint16{
			tls.TLS_RSA_WITH_RC4_128_SHA,
			tls.TLS_RSA_WITH_3DES_EDE_CBC_SHA,
			tls.TLS_RSA_WITH_AES_128_CBC_SHA,
			tls.TLS_ECDHE_RSA_WITH_RC4_128_SHA,
			tls.TLS_RSA_WITH_AES_128_CBC_SHA,
			tls.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,
		},
	}

	mTLSConfig.PreferServerCipherSuites = true
	mTLSConfig.MinVersion = tls.VersionTLS10
	mTLSConfig.MaxVersion = tls.VersionTLS10

	certs := x509.NewCertPool()
	pemData, err := ioutil.ReadFile(ca)
	if err != nil {
		return nil, fmt.Errorf("Failed to read ca cert ::%v", err)
	}
	certs.AppendCertsFromPEM(pemData)
	mTLSConfig.RootCAs = certs

	sh, err := New(db)
	if err != nil {
		return nil, err
	}
	server := &http.Server{Addr: fmt.Sprintf("%v:%v", *Host, *Port),
		TLSConfig: mTLSConfig, Handler: sh}
	return server, nil
}

func main() {
	flag.Parse()
	server, err := getServer(*Db, *CaCert)
	if err != nil {
		fmt.Printf("%v\n", err)
		os.Exit(-1)
	}
	time.Sleep(time.Duration(*Sleep) * time.Second)
	fmt.Printf("Starting landb server\n  %v\n  %v\n  %v\n  %v\n  %v\n  %v\n  %v\n",
		*Host, *Port, *PubCert, *PrivCert, *CaCert, int64(time.Duration(*Sleep)*time.Second), *Db)
	err = server.ListenAndServeTLS(*PubCert, *PrivCert)
	if err != nil {
		fmt.Printf("Failed to initialize server :: %v\n", err)
		os.Exit(-1)
	}
}
