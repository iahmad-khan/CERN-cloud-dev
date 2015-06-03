package main

import (
	"io/ioutil"
	"net/http"
	"net/http/httptest"
	"testing"
)

type SecretTest struct {
	m string
	q string
	r string
	c int
}

var secretTests = []SecretTest{
	SecretTest{m: "GET", q: "", r: "", c: http.StatusOK},
	SecretTest{m: "GET", q: "/aaaa?bbbb", r: "{\"secret\":\"31b743f97660a22af6012ca6d0ffe7c9\"}", c: http.StatusOK},
	SecretTest{m: "GET", q: "/tbag/v1/hosttree//controller-keystone/secret/unit_test_secret_key?hostgroup=", r: "{\"secret\":\"unit_test_secret_value\"}", c: http.StatusOK},
}

func TestSecretHandler(t *testing.T) {

	for _, test := range secretTests {
		req, _ := http.NewRequest(test.m, test.q, nil)
		resp := httptest.NewRecorder()
		secretHandler, nil := New("teigi-db.json")
		secretHandler.ServeHTTP(resp, req)
		if resp.Code != test.c {
			t.Errorf("Failed %v :: expected %v got %v\n", test, test.c, resp.Code)
			return
		}
		result, err := ioutil.ReadAll(resp.Body)
		if err != nil {
			t.Errorf("Failed to read response :: %v", err)
			return
		}
		if string(result) != test.r {
			t.Errorf("Failed %v :: expected %v got %v\n", test, test.r, string(result))
			return
		}

	}

}
