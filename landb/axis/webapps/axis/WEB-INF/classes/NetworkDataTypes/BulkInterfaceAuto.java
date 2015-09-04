/**
 * BulkInterfaceAuto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetworkDataTypes;

public class BulkInterfaceAuto  implements java.io.Serializable {
    private java.lang.String interfaceName;

    private java.lang.String[] IPAliases;

    private NetworkDataTypes.Location location;

    private java.lang.String outletLabel;

    private java.lang.String securityClass;

    private boolean internetConnectivity;

    private java.lang.String medium;

    private java.lang.String switchName;

    private java.lang.String portNumber;

    private java.lang.String cableNumber;

    private java.lang.String IP;

    private java.lang.String IPv6;

    private java.lang.String serviceName;

    private java.lang.String bindHardwareAddress;

    public BulkInterfaceAuto() {
    }

    public BulkInterfaceAuto(
        java.lang.String interfaceName,
        java.lang.String[] IPAliases,
        NetworkDataTypes.Location location,
        java.lang.String outletLabel,
        java.lang.String securityClass,
        boolean internetConnectivity,
        java.lang.String medium,
        java.lang.String switchName,
        java.lang.String portNumber,
        java.lang.String cableNumber,
        java.lang.String IP,
        java.lang.String IPv6,
        java.lang.String serviceName,
        java.lang.String bindHardwareAddress) {
        this.interfaceName = interfaceName;
        this.IPAliases = IPAliases;
        this.location = location;
        this.outletLabel = outletLabel;
        this.securityClass = securityClass;
        this.internetConnectivity = internetConnectivity;
        this.medium = medium;
        this.switchName = switchName;
        this.portNumber = portNumber;
        this.cableNumber = cableNumber;
        this.IP = IP;
        this.IPv6 = IPv6;
        this.serviceName = serviceName;
        this.bindHardwareAddress = bindHardwareAddress;
    }


    /**
     * Gets the interfaceName value for this BulkInterfaceAuto.
     *
     * @return interfaceName
     */
    public java.lang.String getInterfaceName() {
        return interfaceName;
    }


    /**
     * Sets the interfaceName value for this BulkInterfaceAuto.
     *
     * @param interfaceName
     */
    public void setInterfaceName(java.lang.String interfaceName) {
        this.interfaceName = interfaceName;
    }


    /**
     * Gets the IPAliases value for this BulkInterfaceAuto.
     *
     * @return IPAliases
     */
    public java.lang.String[] getIPAliases() {
        return IPAliases;
    }


    /**
     * Sets the IPAliases value for this BulkInterfaceAuto.
     *
     * @param IPAliases
     */
    public void setIPAliases(java.lang.String[] IPAliases) {
        this.IPAliases = IPAliases;
    }


    /**
     * Gets the location value for this BulkInterfaceAuto.
     *
     * @return location
     */
    public NetworkDataTypes.Location getLocation() {
        return location;
    }


    /**
     * Sets the location value for this BulkInterfaceAuto.
     *
     * @param location
     */
    public void setLocation(NetworkDataTypes.Location location) {
        this.location = location;
    }


    /**
     * Gets the outletLabel value for this BulkInterfaceAuto.
     *
     * @return outletLabel
     */
    public java.lang.String getOutletLabel() {
        return outletLabel;
    }


    /**
     * Sets the outletLabel value for this BulkInterfaceAuto.
     *
     * @param outletLabel
     */
    public void setOutletLabel(java.lang.String outletLabel) {
        this.outletLabel = outletLabel;
    }


    /**
     * Gets the securityClass value for this BulkInterfaceAuto.
     *
     * @return securityClass
     */
    public java.lang.String getSecurityClass() {
        return securityClass;
    }


    /**
     * Sets the securityClass value for this BulkInterfaceAuto.
     *
     * @param securityClass
     */
    public void setSecurityClass(java.lang.String securityClass) {
        this.securityClass = securityClass;
    }


    /**
     * Gets the internetConnectivity value for this BulkInterfaceAuto.
     *
     * @return internetConnectivity
     */
    public boolean isInternetConnectivity() {
        return internetConnectivity;
    }


    /**
     * Sets the internetConnectivity value for this BulkInterfaceAuto.
     *
     * @param internetConnectivity
     */
    public void setInternetConnectivity(boolean internetConnectivity) {
        this.internetConnectivity = internetConnectivity;
    }


    /**
     * Gets the medium value for this BulkInterfaceAuto.
     *
     * @return medium
     */
    public java.lang.String getMedium() {
        return medium;
    }


    /**
     * Sets the medium value for this BulkInterfaceAuto.
     *
     * @param medium
     */
    public void setMedium(java.lang.String medium) {
        this.medium = medium;
    }


    /**
     * Gets the switchName value for this BulkInterfaceAuto.
     *
     * @return switchName
     */
    public java.lang.String getSwitchName() {
        return switchName;
    }


    /**
     * Sets the switchName value for this BulkInterfaceAuto.
     *
     * @param switchName
     */
    public void setSwitchName(java.lang.String switchName) {
        this.switchName = switchName;
    }


    /**
     * Gets the portNumber value for this BulkInterfaceAuto.
     *
     * @return portNumber
     */
    public java.lang.String getPortNumber() {
        return portNumber;
    }


    /**
     * Sets the portNumber value for this BulkInterfaceAuto.
     *
     * @param portNumber
     */
    public void setPortNumber(java.lang.String portNumber) {
        this.portNumber = portNumber;
    }


    /**
     * Gets the cableNumber value for this BulkInterfaceAuto.
     *
     * @return cableNumber
     */
    public java.lang.String getCableNumber() {
        return cableNumber;
    }


    /**
     * Sets the cableNumber value for this BulkInterfaceAuto.
     *
     * @param cableNumber
     */
    public void setCableNumber(java.lang.String cableNumber) {
        this.cableNumber = cableNumber;
    }


    /**
     * Gets the IP value for this BulkInterfaceAuto.
     *
     * @return IP
     */
    public java.lang.String getIP() {
        return IP;
    }


    /**
     * Sets the IP value for this BulkInterfaceAuto.
     *
     * @param IP
     */
    public void setIP(java.lang.String IP) {
        this.IP = IP;
    }


    /**
     * Gets the IPv6 value for this BulkInterfaceAuto.
     *
     * @return IPv6
     */
    public java.lang.String getIPv6() {
        return IPv6;
    }


    /**
     * Sets the IPv6 value for this BulkInterfaceAuto.
     *
     * @param IPv6
     */
    public void setIPv6(java.lang.String IPv6) {
        this.IPv6 = IPv6;
    }


    /**
     * Gets the serviceName value for this BulkInterfaceAuto.
     *
     * @return serviceName
     */
    public java.lang.String getServiceName() {
        return serviceName;
    }


    /**
     * Sets the serviceName value for this BulkInterfaceAuto.
     *
     * @param serviceName
     */
    public void setServiceName(java.lang.String serviceName) {
        this.serviceName = serviceName;
    }


    /**
     * Gets the bindHardwareAddress value for this BulkInterfaceAuto.
     *
     * @return bindHardwareAddress
     */
    public java.lang.String getBindHardwareAddress() {
        return bindHardwareAddress;
    }


    /**
     * Sets the bindHardwareAddress value for this BulkInterfaceAuto.
     *
     * @param bindHardwareAddress
     */
    public void setBindHardwareAddress(java.lang.String bindHardwareAddress) {
        this.bindHardwareAddress = bindHardwareAddress;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BulkInterfaceAuto)) return false;
        BulkInterfaceAuto other = (BulkInterfaceAuto) obj;
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
                  ((this.IPAliases==null && other.getIPAliases()==null) ||
                   (this.IPAliases!=null &&
                    java.util.Arrays.equals(this.IPAliases, other.getIPAliases()))) &&
                  ((this.location==null && other.getLocation()==null) ||
                   (this.location!=null &&
                    this.location.equals(other.getLocation()))) &&
                  ((this.outletLabel==null && other.getOutletLabel()==null) ||
                   (this.outletLabel!=null &&
                    this.outletLabel.equals(other.getOutletLabel()))) &&
                  ((this.securityClass==null && other.getSecurityClass()==null) ||
                   (this.securityClass!=null &&
                    this.securityClass.equals(other.getSecurityClass()))) &&
                  this.internetConnectivity == other.isInternetConnectivity() &&
                  ((this.medium==null && other.getMedium()==null) ||
                   (this.medium!=null &&
                    this.medium.equals(other.getMedium()))) &&
                  ((this.switchName==null && other.getSwitchName()==null) ||
                   (this.switchName!=null &&
                    this.switchName.equals(other.getSwitchName()))) &&
                  ((this.portNumber==null && other.getPortNumber()==null) ||
                   (this.portNumber!=null &&
                    this.portNumber.equals(other.getPortNumber()))) &&
                  ((this.cableNumber==null && other.getCableNumber()==null) ||
                   (this.cableNumber!=null &&
                    this.cableNumber.equals(other.getCableNumber()))) &&
                  ((this.IP==null && other.getIP()==null) ||
                   (this.IP!=null &&
                    this.IP.equals(other.getIP()))) &&
                  ((this.IPv6==null && other.getIPv6()==null) ||
                   (this.IPv6!=null &&
                    this.IPv6.equals(other.getIPv6()))) &&
                  ((this.serviceName==null && other.getServiceName()==null) ||
                   (this.serviceName!=null &&
                    this.serviceName.equals(other.getServiceName()))) &&
                  ((this.bindHardwareAddress==null && other.getBindHardwareAddress()==null) ||
                   (this.bindHardwareAddress!=null &&
                    this.bindHardwareAddress.equals(other.getBindHardwareAddress())));
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
        if (getIPAliases() != null) {
            for (int i=0;
                    i<java.lang.reflect.Array.getLength(getIPAliases());
                    i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIPAliases(), i);
                if (obj != null &&
                        !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getLocation() != null) {
            _hashCode += getLocation().hashCode();
        }
        if (getOutletLabel() != null) {
            _hashCode += getOutletLabel().hashCode();
        }
        if (getSecurityClass() != null) {
            _hashCode += getSecurityClass().hashCode();
        }
        _hashCode += (isInternetConnectivity() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getMedium() != null) {
            _hashCode += getMedium().hashCode();
        }
        if (getSwitchName() != null) {
            _hashCode += getSwitchName().hashCode();
        }
        if (getPortNumber() != null) {
            _hashCode += getPortNumber().hashCode();
        }
        if (getCableNumber() != null) {
            _hashCode += getCableNumber().hashCode();
        }
        if (getIP() != null) {
            _hashCode += getIP().hashCode();
        }
        if (getIPv6() != null) {
            _hashCode += getIPv6().hashCode();
        }
        if (getServiceName() != null) {
            _hashCode += getServiceName().hashCode();
        }
        if (getBindHardwareAddress() != null) {
            _hashCode += getBindHardwareAddress().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BulkInterfaceAuto.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "BulkInterfaceAuto"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("interfaceName");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "InterfaceName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IPAliases");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "IPAliases"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("location");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Location"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Location"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("outletLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "OutletLabel"));
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
        elemField.setFieldName("internetConnectivity");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "InternetConnectivity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("medium");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Medium"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("switchName");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "SwitchName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("portNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "PortNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cableNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "CableNumber"));
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
        elemField.setFieldName("serviceName");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "ServiceName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bindHardwareAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "BindHardwareAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
