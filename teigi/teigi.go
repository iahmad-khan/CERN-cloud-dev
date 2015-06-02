// Package teigi provides a mock server of the CERN Teigi application.
//
// Teigi is an internal CERN server handling storage and retrieval of secrets.
//
// Author: Ricardo Rocha <ricardo.rocha@cern.ch>
package main

import (
	"crypto/md5"
	"crypto/tls"
	"crypto/x509"
	"encoding/json"
	"flag"
	"fmt"
	"io/ioutil"
	"net/http"
	"os"
	"time"
)

var (
	Port     = flag.Int("port", 8082, "port teigi server will listen")
	Host     = flag.String("host", "0.0.0.0", "ip or hostname the server will listen at")
	PubCert  = flag.String("pubcert", "", "location of the public certificate for the server")
	PrivCert = flag.String("privcert", "", "location of the private certificate for the server")
	CaCert   = flag.String("cacert", "", "location of the CA root certificate for the server")
	Sleep    = flag.Int("sleep", 0, "sleep seconds before starting server")
)

// SecretHandler handles requests for secrets.
type SecretHandler struct {
}

// ServeHTTP is the SecretHandler implementation of http.Server.ServerHTTP.
// Returns the md5sum of the url request.
func (*SecretHandler) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	req := fmt.Sprintf("%v%v", r.URL.Path, r.URL.RawQuery)
	if req == "" {
		return
	}
	resp, _ := json.Marshal(map[string]string{"secret": fmt.Sprintf("%x", md5.Sum([]byte(req)))})
	fmt.Printf("http: %v %v %x\n", r, req, string(resp))
	fmt.Fprintf(w, "%v", string(resp))
}

func getServer(ca string) (*http.Server, error) {
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

	server := &http.Server{Addr: fmt.Sprintf("%v:%v", *Host, *Port),
		TLSConfig: mTLSConfig, Handler: &SecretHandler{}}
	return server, nil
}

func main() {
	flag.Parse()
	server, err := getServer(*CaCert)
	if err != nil {
		fmt.Printf("%v\n", err)
		os.Exit(-1)
	}
	time.Sleep(time.Duration(*Sleep) * time.Second)
	fmt.Printf("Starting teigi server\n  %v\n  %v\n  %v\n  %v\n  %v\n  %v\n",
		*Host, *Port, *PubCert, *PrivCert, *CaCert, int64(time.Duration(*Sleep)*time.Second))
	err = server.ListenAndServeTLS(*PubCert, *PrivCert)
	if err != nil {
		fmt.Printf("Failed to initialize server :: %v\n", err)
		os.Exit(-1)
	}
}
