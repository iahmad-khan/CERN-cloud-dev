/**
 * VMOptions.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetworkDataTypes;

public class VMOptions  implements java.io.Serializable {
    private java.lang.String IP;

    private java.lang.String IPv6;

    private java.lang.String serviceName;

    private java.lang.String internetConnectivity;

    private java.lang.String addressType;

    private java.lang.Boolean isExtManaged;

    public VMOptions() {
    }

    public VMOptions(
        java.lang.String IP,
        java.lang.String IPv6,
        java.lang.String serviceName,
        java.lang.String internetConnectivity,
        java.lang.String addressType,
        java.lang.Boolean isExtManaged) {
        this.IP = IP;
        this.IPv6 = IPv6;
        this.serviceName = serviceName;
        this.internetConnectivity = internetConnectivity;
        this.addressType = addressType;
        this.isExtManaged = isExtManaged;
    }


    /**
     * Gets the IP value for this VMOptions.
     *
     * @return IP
     */
    public java.lang.String getIP() {
        return IP;
    }


    /**
     * Sets the IP value for this VMOptions.
     *
     * @param IP
     */
    public void setIP(java.lang.String IP) {
        this.IP = IP;
    }


    /**
     * Gets the IPv6 value for this VMOptions.
     *
     * @return IPv6
     */
    public java.lang.String getIPv6() {
        return IPv6;
    }


    /**
     * Sets the IPv6 value for this VMOptions.
     *
     * @param IPv6
     */
    public void setIPv6(java.lang.String IPv6) {
        this.IPv6 = IPv6;
    }


    /**
     * Gets the serviceName value for this VMOptions.
     *
     * @return serviceName
     */
    public java.lang.String getServiceName() {
        return serviceName;
    }


    /**
     * Sets the serviceName value for this VMOptions.
     *
     * @param serviceName
     */
    public void setServiceName(java.lang.String serviceName) {
        this.serviceName = serviceName;
    }


    /**
     * Gets the internetConnectivity value for this VMOptions.
     *
     * @return internetConnectivity
     */
    public java.lang.String getInternetConnectivity() {
        return internetConnectivity;
    }


    /**
     * Sets the internetConnectivity value for this VMOptions.
     *
     * @param internetConnectivity
     */
    public void setInternetConnectivity(java.lang.String internetConnectivity) {
        this.internetConnectivity = internetConnectivity;
    }


    /**
     * Gets the addressType value for this VMOptions.
     *
     * @return addressType
     */
    public java.lang.String getAddressType() {
        return addressType;
    }


    /**
     * Sets the addressType value for this VMOptions.
     *
     * @param addressType
     */
    public void setAddressType(java.lang.String addressType) {
        this.addressType = addressType;
    }


    /**
     * Gets the isExtManaged value for this VMOptions.
     *
     * @return isExtManaged
     */
    public java.lang.Boolean getIsExtManaged() {
        return isExtManaged;
    }


    /**
     * Sets the isExtManaged value for this VMOptions.
     *
     * @param isExtManaged
     */
    public void setIsExtManaged(java.lang.Boolean isExtManaged) {
        this.isExtManaged = isExtManaged;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VMOptions)) return false;
        VMOptions other = (VMOptions) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
                  ((this.IP==null && other.getIP()==null) ||
                   (this.IP!=null &&
                    this.IP.equals(other.getIP()))) &&
                  ((this.IPv6==null && other.getIPv6()==null) ||
                   (this.IPv6!=null &&
                    this.IPv6.equals(other.getIPv6()))) &&
                  ((this.serviceName==null && other.getServiceName()==null) ||
                   (this.serviceName!=null &&
                    this.serviceName.equals(other.getServiceName()))) &&
                  ((this.internetConnectivity==null && other.getInternetConnectivity()==null) ||
                   (this.internetConnectivity!=null &&
                    this.internetConnectivity.equals(other.getInternetConnectivity()))) &&
                  ((this.addressType==null && other.getAddressType()==null) ||
                   (this.addressType!=null &&
                    this.addressType.equals(other.getAddressType()))) &&
                  ((this.isExtManaged==null && other.getIsExtManaged()==null) ||
                   (this.isExtManaged!=null &&
                    this.isExtManaged.equals(other.getIsExtManaged())));
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
        if (getIP() != null) {
            _hashCode += getIP().hashCode();
        }
        if (getIPv6() != null) {
            _hashCode += getIPv6().hashCode();
        }
        if (getServiceName() != null) {
            _hashCode += getServiceName().hashCode();
        }
        if (getInternetConnectivity() != null) {
            _hashCode += getInternetConnectivity().hashCode();
        }
        if (getAddressType() != null) {
            _hashCode += getAddressType().hashCode();
        }
        if (getIsExtManaged() != null) {
            _hashCode += getIsExtManaged().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VMOptions.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "VMOptions"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("serviceName");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "ServiceName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("internetConnectivity");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "InternetConnectivity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressType");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "AddressType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isExtManaged");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "IsExtManaged"));
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
