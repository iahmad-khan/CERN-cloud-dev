package main

import (
	"io/ioutil"
	"net/http"
	"net/http/httptest"
	"strings"
	"testing"
)

type LandbTest struct {
	name string
	req  string
	body string
	resp string
	code int
}

var landbTests = []LandbTest{
	LandbTest{
		name: "getAuthToken",
		req:  "/sc/soap/soap.fcgi?v=5",
		body: "<?xml version='1.0' encoding='utf-8'?><SOAP-ENV:Envelope xmlns:ns3='http://www.w3.org/2001/XMLSchema' xmlns:SOAP-ENC='http://schemas.xmlsoap.org/soap/encoding/' xmlns:ns0='urn:NetworkService' xmlns:ns1='http://schemas.xmlsoap.org/soap/encoding/' xmlns:ns2='http://schemas.xmlsoap.org/soap/envelope/' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:SOAP-ENV='http://schemas.xmlsoap.org/soap/envelope/' SOAP-ENV:encodingStyle='http://schemas.xmlsoap.org/soap/encoding/'><ns2:Body><ns0:getAuthTokenResponse><ns0:token>123456</ns0:token></ns0:getAuthTokenResponse></ns2:Body></SOAP-ENV:Envelope>",
		resp: "<?xml version='1.0' encoding='UTF-8'?><SOAP-ENV:Envelope SOAP-ENV:encodingStyle='http://schemas.xmlsoap.org/soap/encoding/'><SOAP-ENV:Body><getAuthTokenResponse><token>landbtoken123456</token></getAuthTokenResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>",
		code: http.StatusOK,
	},
	LandbTest{
		name: "vmClusterGetMembership",
		req:  "/sc/soap/soap.fcgi?v=5",
		body: "<?xml version='1.0' encoding='UTF-8'?><SOAP-ENV:Envelope xmlns:ns3='http://www.w3.org/2001/XMLSchema' xmlns:SOAP-ENC='http://schemas.xmlsoap.org/soap/encoding/' xmlns:ns0='urn:NetworkService' xmlns:ns1='urn:NetworkDataTypes' xmlns:ns2='http://schemas.xmlsoap.org/soap/envelope/' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:types='urn:NetworkDataTypes' xmlns:SOAP-ENV='http://schemas.xmlsoap.org/soap/envelope/' SOAP-ENV:encodingStyle='http://schemas.xmlsoap.org/soap/encoding/'><SOAP-ENV:Header><Auth xsi:type='ns1:Auth'><types:token xmlns:ns1='http://www.w3.org/2001/XMLSchema' xsi:type='ns1:string'>H4sIAAAAAAAA/2Nh5zA0MjYxNTO3YOHg4GDmYGBgYGHiAZJsaXeYGUCAzR/G2FAIZRwNgjLCvKGMICco4ww3lHEaxtD5xARhvH8OZZxaDWV4hkIYrECck1lcUszF5ucf5OvowwkUKM5PLIgvqSxIZZO5dDUUpIwFiEsyc1O5WIxMzE3ZQZoS81KSMlNYwMbwA3FiTk5+eWpKfG5qSUZ+SjEXj6GRhZ6hiaGekZ6xOUiVZwAXa1F+ckYiyLDS4tQiLhY/T2dXsNFAuwAHtdciEAEAAA==,CjKx+9ice/hfMvTDbzBCsbQjdmQ=</types:token></Auth></SOAP-ENV:Header><ns2:Body xmlns:ns1='http://schemas.xmlsoap.org/soap/encoding/'><ns0:vmGetClusterMembership><DeviceName xsi:type='ns3:string'>cci-jenkins</DeviceName></ns0:vmGetClusterMembership></ns2:Body></SOAP-ENV:Envelope>",
		resp: "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Envelope SOAP-ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><SOAP-ENV:Body><vmGetClusterMembershipResponse><VMClusterList SOAP-ENC:arrayType=\"xsd:string[1]\" xsi:type=\"SOAP-ENC:Array\"><item xsi:type=\"xsd:string\">CLUSTER1</item></VMClusterList></vmGetClusterMembershipResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>",
		code: http.StatusOK,
	},
}

func TestLandbHandler(t *testing.T) {

	for _, test := range landbTests {
		req, _ := http.NewRequest("POST", test.req, strings.NewReader(test.body))
		resp := httptest.NewRecorder()
		landbHandler, nil := New("data/landb.json")
		landbHandler.ServeHTTP(resp, req)
		if resp.Code != test.code {
			t.Errorf("Failed %v :: expected %v got %v\n", test.name, test.code, resp.Code)
			return
		}
		result, err := ioutil.ReadAll(resp.Body)
		if err != nil {
			t.Errorf("Failed to read response :: %v", err)
			return
		}
		if string(result) != test.resp {
			t.Errorf("Failed %v :: expected\n%v\ngot\n%v\n", test.name, test.resp, string(result))
			return
		}

	}

}
