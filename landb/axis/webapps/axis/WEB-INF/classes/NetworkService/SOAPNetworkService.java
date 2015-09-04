/**
 * SOAPNetworkService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetworkService;

public interface SOAPNetworkService extends javax.xml.rpc.Service {
    public java.lang.String getNetworkServicePortAddress();

    public NetworkService.NetworkServiceInterface getNetworkServicePort() throws javax.xml.rpc.ServiceException;

    public NetworkService.NetworkServiceInterface getNetworkServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
