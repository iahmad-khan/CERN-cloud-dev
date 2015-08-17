#!/usr/bin/env python
#
# Handy tool to perform landb calls.
#
# ./landb.py <user> <pass> <method> <args...>
#
# <method> can be any of the requests defined in the WSDL.
#
# Output is the SOAP request and response payload.
#
# Examples:
#
# ./landb.py rocha mypass getAuthToken
# ./landb.py rocha mypass vmGetClusterMembership rocha-vm01
# ./landb.py rocha mypass getDeviceBasicInfo rocha-vm01
#

import argparse
import ConfigParser
import random
import string
import sys
import logging
from suds.xsd.doctor import ImportDoctor, Import
from suds.client import Client

class LanDB:
    def __init__(self, username=None, password=None, host=None, port=None, client=None):
        if client != None:
            self.client = client
        else:
            self.client = self.__auth(username, password, host, port)

    def __auth(self, username, password, host, port):
        """Authenticates in landb"""
        url = "https://%s:%s/sc/soap/soap.fcgi?v=5&WSDL" % (host, port)
        imp = Import('http://schemas.xmlsoap.org/soap/encoding/')
        d = ImportDoctor(imp)
        client = Client(url, doctor = d)
        logging.getLogger('suds.client').setLevel(logging.CRITICAL)
        token = client.service.getAuthToken(username, password, 'CERN')
        print client.last_sent()
        print client.last_received()
        myheader = dict(Auth={'token': token})
        client.set_options(soapheaders = myheader)
        return client

def parse_cmdline_args():
    parser = argparse.ArgumentParser()
    parser.add_argument("user", type=str, help="landb username")
    parser.add_argument("password", type=str, help="landb username")
    parser.add_argument("method", type=str, help="method to call")
    parser.add_argument("args", type=str, nargs='+', help="args to pass to the method")
    parser.add_argument("--host", default="network.cern.ch", type=str, help="method to call")
    parser.add_argument("--port", default="443", type=str, help="method to call")
    return parser.parse_args()


def main():
    try:
        args = parse_cmdline_args()
    except Exception as e:
        sys.stdout.write("Wrong command line arguments (%s)" % e.strerror)

    print args
    landb = LanDB(args.user, args.password, args.host, args.port)
    method = getattr(landb.client.service, args.method)
    clusters = method(*args.args)
    print landb.client.last_sent()
    print landb.client.last_received()

if __name__ == "__main__":
    main()
