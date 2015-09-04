/**
 * SOAPNetworkServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetworkService;

public class SOAPNetworkServiceLocator extends org.apache.axis.client.Service implements NetworkService.SOAPNetworkService {

    public SOAPNetworkServiceLocator() {
    }


    public SOAPNetworkServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SOAPNetworkServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for NetworkServicePort
    private java.lang.String NetworkServicePort_address = "https://localhost:8043/sc/soap/soap.fcgi?v=5";

    public java.lang.String getNetworkServicePortAddress() {
        return NetworkServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String NetworkServicePortWSDDServiceName = "NetworkServicePort";

    public java.lang.String getNetworkServicePortWSDDServiceName() {
        return NetworkServicePortWSDDServiceName;
    }

    public void setNetworkServicePortWSDDServiceName(java.lang.String name) {
        NetworkServicePortWSDDServiceName = name;
    }

    public NetworkService.NetworkServiceInterface getNetworkServicePort() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(NetworkServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getNetworkServicePort(endpoint);
    }

    public NetworkService.NetworkServiceInterface getNetworkServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            NetworkService.NetworkServiceBindingStub _stub = new NetworkService.NetworkServiceBindingStub(portAddress, this);
            _stub.setPortName(getNetworkServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setNetworkServicePortEndpointAddress(java.lang.String address) {
        NetworkServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (NetworkService.NetworkServiceInterface.class.isAssignableFrom(serviceEndpointInterface)) {
                NetworkService.NetworkServiceBindingStub _stub = new NetworkService.NetworkServiceBindingStub(new java.net.URL(NetworkServicePort_address), this);
                _stub.setPortName(getNetworkServicePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("NetworkServicePort".equals(inputPortName)) {
            return getNetworkServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:NetworkService", "SOAPNetworkService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:NetworkService", "NetworkServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

        if ("NetworkServicePort".equals(portName)) {
            setNetworkServicePortEndpointAddress(address);
        }
        else
        {   // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
