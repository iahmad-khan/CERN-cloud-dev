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
	"strings"
	"time"
)

var (
	Port     = flag.Int("port", 8082, "port teigi server will listen")
	Host     = flag.String("host", "0.0.0.0", "ip or hostname the server will listen at")
	PubCert  = flag.String("pubcert", "", "location of the public certificate for the server")
	PrivCert = flag.String("privcert", "", "location of the private certificate for the server")
	CaCert   = flag.String("cacert", "", "location of the CA root certificate for the server")
	Db       = flag.String("db", "", "location of db with secrets to preload")
	FilePath = flag.String("filepath", "", "location of the directory with secret files")
	Sleep    = flag.Int("sleep", 0, "sleep seconds before starting server")
)

// New returns a new SecretHandler instance.
// db is the location of the json database file, filePath is the local
// directory where file secrets are stored.
func New(db string, filePath string) (*SecretHandler, error) {
	sh := SecretHandler{FilePath: filePath}
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

// SecretHandler handles requests for secrets.
type SecretHandler struct {
	// Secrets is a map containing key/value pairs of secrets (id/secret).
	Secrets map[string]string
	// FilePath is the local directory where file secrets can be found.
	FilePath string
}

// LoadDB loads the SecretHandler with the secrets from the json file at location.
func (sh *SecretHandler) LoadDB(location string) error {
	f, err := ioutil.ReadFile(location)
	if err != nil {
		return fmt.Errorf("Failed to load db :: %v\n", err)
	}
	err = json.Unmarshal(f, &sh.Secrets)
	if err != nil {
		return fmt.Errorf("Failed to load dd :: %v\n", err)
	}
	return nil
}

// ServeHTTP is the SecretHandler implementation of http.Server.ServerHTTP.
// Returns the md5sum of the url request.
func (sh *SecretHandler) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	fmt.Printf("new request :: %v\n", r)

	req := fmt.Sprintf("%v%v", r.URL.Path, r.URL.RawQuery)
	if req == "" {
		return
	}
	var item string
	items := strings.Split(r.URL.Path, "/")
	if len(items) > 7 {
		item = items[7]
	}

	var secret string
	// start with a check if a file with the key name exists
	if f, err := ioutil.ReadFile(fmt.Sprintf("%s/%s", sh.FilePath, item)); err == nil {
		fmt.Printf("Returning file %s/%s\n", sh.FilePath, item)
		secret = string(f)
	} else if s, ok := sh.Secrets[item]; ok { // check if there's an entry in the json secrets
		fmt.Printf("Returning json entry %s\n", item)
		secret = s
	} else { // nothing found, generate the md5sum of the request
		fmt.Printf("returning md5sum for %s\n", req)
		secret = fmt.Sprintf("%x", md5.Sum([]byte(req)))
	}
	resp, _ := json.Marshal(map[string]string{"secret": secret})
	fmt.Fprintf(w, "%v", string(resp))
}

func getServer(db string, filePath string, ca string) (*http.Server, error) {
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

	sh, err := New(db, filePath)
	if err != nil {
		return nil, err
	}
	server := &http.Server{Addr: fmt.Sprintf("%v:%v", *Host, *Port),
		TLSConfig: mTLSConfig, Handler: sh}
	return server, nil
}

func main() {
	flag.Parse()
	server, err := getServer(*Db, *FilePath, *CaCert)
	if err != nil {
		fmt.Printf("%v\n", err)
		os.Exit(-1)
	}
	time.Sleep(time.Duration(*Sleep) * time.Second)
	fmt.Printf("Starting teigi server\n  %v\n  %v\n  %v\n  %v\n  %v\n  %v\n  %v\n",
		*Host, *Port, *PubCert, *PrivCert, *CaCert, int64(time.Duration(*Sleep)*time.Second), *Db)
	err = server.ListenAndServeTLS(*PubCert, *PrivCert)
	if err != nil {
		fmt.Printf("Failed to initialize server :: %v\n", err)
		os.Exit(-1)
	}
}
