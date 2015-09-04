/**
 * LogicalInterfaceInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetworkDataTypes;

public class LogicalInterfaceInput  implements java.io.Serializable {
    private java.lang.String interfaceName;

    private java.lang.String serviceName;

    private java.lang.String securityClass;

    private java.lang.String IP;

    private java.lang.String IPv6;

    private java.lang.Boolean internetConnectivity;

    public LogicalInterfaceInput() {
    }

    public LogicalInterfaceInput(
        java.lang.String interfaceName,
        java.lang.String serviceName,
        java.lang.String securityClass,
        java.lang.String IP,
        java.lang.String IPv6,
        java.lang.Boolean internetConnectivity) {
        this.interfaceName = interfaceName;
        this.serviceName = serviceName;
        this.securityClass = securityClass;
        this.IP = IP;
        this.IPv6 = IPv6;
        this.internetConnectivity = internetConnectivity;
    }


    /**
     * Gets the interfaceName value for this LogicalInterfaceInput.
     *
     * @return interfaceName
     */
    public java.lang.String getInterfaceName() {
        return interfaceName;
    }


    /**
     * Sets the interfaceName value for this LogicalInterfaceInput.
     *
     * @param interfaceName
     */
    public void setInterfaceName(java.lang.String interfaceName) {
        this.interfaceName = interfaceName;
    }


    /**
     * Gets the serviceName value for this LogicalInterfaceInput.
     *
     * @return serviceName
     */
    public java.lang.String getServiceName() {
        return serviceName;
    }


    /**
     * Sets the serviceName value for this LogicalInterfaceInput.
     *
     * @param serviceName
     */
    public void setServiceName(java.lang.String serviceName) {
        this.serviceName = serviceName;
    }


    /**
     * Gets the securityClass value for this LogicalInterfaceInput.
     *
     * @return securityClass
     */
    public java.lang.String getSecurityClass() {
        return securityClass;
    }


    /**
     * Sets the securityClass value for this LogicalInterfaceInput.
     *
     * @param securityClass
     */
    public void setSecurityClass(java.lang.String securityClass) {
        this.securityClass = securityClass;
    }


    /**
     * Gets the IP value for this LogicalInterfaceInput.
     *
     * @return IP
     */
    public java.lang.String getIP() {
        return IP;
    }


    /**
     * Sets the IP value for this LogicalInterfaceInput.
     *
     * @param IP
     */
    public void setIP(java.lang.String IP) {
        this.IP = IP;
    }


    /**
     * Gets the IPv6 value for this LogicalInterfaceInput.
     *
     * @return IPv6
     */
    public java.lang.String getIPv6() {
        return IPv6;
    }


    /**
     * Sets the IPv6 value for this LogicalInterfaceInput.
     *
     * @param IPv6
     */
    public void setIPv6(java.lang.String IPv6) {
        this.IPv6 = IPv6;
    }


    /**
     * Gets the internetConnectivity value for this LogicalInterfaceInput.
     *
     * @return internetConnectivity
     */
    public java.lang.Boolean getInternetConnectivity() {
        return internetConnectivity;
    }


    /**
     * Sets the internetConnectivity value for this LogicalInterfaceInput.
     *
     * @param internetConnectivity
     */
    public void setInternetConnectivity(java.lang.Boolean internetConnectivity) {
        this.internetConnectivity = internetConnectivity;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LogicalInterfaceInput)) return false;
        LogicalInterfaceInput other = (LogicalInterfaceInput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
                  ((this.interfaceName==null && other.getInterfaceName()==null) ||
                   (this.interfaceName!=null &&
                    this.interfaceName.equals(other.getInterfaceName()))) &&
                  ((this.serviceName==null && other.getServiceName()==null) ||
                   (this.serviceName!=null &&
                    this.serviceName.equals(other.getServiceName()))) &&
                  ((this.securityClass==null && other.getSecurityClass()==null) ||
                   (this.securityClass!=null &&
                    this.securityClass.equals(other.getSecurityClass()))) &&
                  ((this.IP==null && other.getIP()==null) ||
                   (this.IP!=null &&
                    this.IP.equals(other.getIP()))) &&
                  ((this.IPv6==null && other.getIPv6()==null) ||
                   (this.IPv6!=null &&
                    this.IPv6.equals(other.getIPv6()))) &&
                  ((this.internetConnectivity==null && other.getInternetConnectivity()==null) ||
                   (this.internetConnectivity!=null &&
                    this.internetConnectivity.equals(other.getInternetConnectivity())));
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
        if (getInterfaceName() != null) {
            _hashCode += getInterfaceName().hashCode();
        }
        if (getServiceName() != null) {
            _hashCode += getServiceName().hashCode();
        }
        if (getSecurityClass() != null) {
            _hashCode += getSecurityClass().hashCode();
        }
        if (getIP() != null) {
            _hashCode += getIP().hashCode();
        }
        if (getIPv6() != null) {
            _hashCode += getIPv6().hashCode();
        }
        if (getInternetConnectivity() != null) {
            _hashCode += getInternetConnectivity().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LogicalInterfaceInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "LogicalInterfaceInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("interfaceName");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "InterfaceName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceName");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "ServiceName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("securityClass");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "SecurityClass"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IP");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "IP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IPv6");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "IPv6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("internetConnectivity");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "InternetConnectivity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
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
