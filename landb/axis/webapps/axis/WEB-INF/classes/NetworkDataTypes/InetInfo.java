/**
 * InetInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetworkDataTypes;

public class InetInfo  implements java.io.Serializable {
    private java.lang.String hostName;

    private java.lang.String hardwareAddress;

    private java.lang.String IP;

    private java.lang.String netAddress;

    private java.lang.String mask;

    private java.lang.String gatewayAddress;

    public InetInfo() {
    }

    public InetInfo(
        java.lang.String hostName,
        java.lang.String hardwareAddress,
        java.lang.String IP,
        java.lang.String netAddress,
        java.lang.String mask,
        java.lang.String gatewayAddress) {
        this.hostName = hostName;
        this.hardwareAddress = hardwareAddress;
        this.IP = IP;
        this.netAddress = netAddress;
        this.mask = mask;
        this.gatewayAddress = gatewayAddress;
    }


    /**
     * Gets the hostName value for this InetInfo.
     *
     * @return hostName
     */
    public java.lang.String getHostName() {
        return hostName;
    }


    /**
     * Sets the hostName value for this InetInfo.
     *
     * @param hostName
     */
    public void setHostName(java.lang.String hostName) {
        this.hostName = hostName;
    }


    /**
     * Gets the hardwareAddress value for this InetInfo.
     *
     * @return hardwareAddress
     */
    public java.lang.String getHardwareAddress() {
        return hardwareAddress;
    }


    /**
     * Sets the hardwareAddress value for this InetInfo.
     *
     * @param hardwareAddress
     */
    public void setHardwareAddress(java.lang.String hardwareAddress) {
        this.hardwareAddress = hardwareAddress;
    }


    /**
     * Gets the IP value for this InetInfo.
     *
     * @return IP
     */
    public java.lang.String getIP() {
        return IP;
    }


    /**
     * Sets the IP value for this InetInfo.
     *
     * @param IP
     */
    public void setIP(java.lang.String IP) {
        this.IP = IP;
    }


    /**
     * Gets the netAddress value for this InetInfo.
     *
     * @return netAddress
     */
    public java.lang.String getNetAddress() {
        return netAddress;
    }


    /**
     * Sets the netAddress value for this InetInfo.
     *
     * @param netAddress
     */
    public void setNetAddress(java.lang.String netAddress) {
        this.netAddress = netAddress;
    }


    /**
     * Gets the mask value for this InetInfo.
     *
     * @return mask
     */
    public java.lang.String getMask() {
        return mask;
    }


    /**
     * Sets the mask value for this InetInfo.
     *
     * @param mask
     */
    public void setMask(java.lang.String mask) {
        this.mask = mask;
    }


    /**
     * Gets the gatewayAddress value for this InetInfo.
     *
     * @return gatewayAddress
     */
    public java.lang.String getGatewayAddress() {
        return gatewayAddress;
    }


    /**
     * Sets the gatewayAddress value for this InetInfo.
     *
     * @param gatewayAddress
     */
    public void setGatewayAddress(java.lang.String gatewayAddress) {
        this.gatewayAddress = gatewayAddress;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InetInfo)) return false;
        InetInfo other = (InetInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
                  ((this.hostName==null && other.getHostName()==null) ||
                   (this.hostName!=null &&
                    this.hostName.equals(other.getHostName()))) &&
                  ((this.hardwareAddress==null && other.getHardwareAddress()==null) ||
                   (this.hardwareAddress!=null &&
                    this.hardwareAddress.equals(other.getHardwareAddress()))) &&
                  ((this.IP==null && other.getIP()==null) ||
                   (this.IP!=null &&
                    this.IP.equals(other.getIP()))) &&
                  ((this.netAddress==null && other.getNetAddress()==null) ||
                   (this.netAddress!=null &&
                    this.netAddress.equals(other.getNetAddress()))) &&
                  ((this.mask==null && other.getMask()==null) ||
                   (this.mask!=null &&
                    this.mask.equals(other.getMask()))) &&
                  ((this.gatewayAddress==null && other.getGatewayAddress()==null) ||
                   (this.gatewayAddress!=null &&
                    this.gatewayAddress.equals(other.getGatewayAddress())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getHostName() != null) {
            _hashCode += getHostName().hashCode();
        }
        if (getHardwareAddress() != null) {
            _hashCode += getHardwareAddress().hashCode();
        }
        if (getIP() != null) {
            _hashCode += getIP().hashCode();
        }
        if (getNetAddress() != null) {
            _hashCode += getNetAddress().hashCode();
        }
        if (getMask() != null) {
            _hashCode += getMask().hashCode();
        }
        if (getGatewayAddress() != null) {
            _hashCode += getGatewayAddress().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InetInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "InetInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hostName");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "HostName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hardwareAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "HardwareAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IP");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "IP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("netAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "NetAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mask");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Mask"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gatewayAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "GatewayAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
        java.lang.String mechType,
        java.lang.Class _javaType,
        javax.xml.namespace.QName _xmlType) {
        return
            new  org.apache.axis.encoding.ser.BeanSerializer(
                _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
        java.lang.String mechType,
        java.lang.Class _javaType,
        javax.xml.namespace.QName _xmlType) {
        return
            new  org.apache.axis.encoding.ser.BeanDeserializer(
                _javaType, _xmlType, typeDesc);
    }

}
