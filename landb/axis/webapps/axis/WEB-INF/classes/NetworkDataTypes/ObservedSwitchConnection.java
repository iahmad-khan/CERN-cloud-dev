/**
 * ObservedSwitchConnection.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetworkDataTypes;

public class ObservedSwitchConnection  implements java.io.Serializable {
    private java.lang.String switchName;

    private java.lang.String switchPort;

    private java.lang.String hardwareAddress;

    public ObservedSwitchConnection() {
    }

    public ObservedSwitchConnection(
        java.lang.String switchName,
        java.lang.String switchPort,
        java.lang.String hardwareAddress) {
        this.switchName = switchName;
        this.switchPort = switchPort;
        this.hardwareAddress = hardwareAddress;
    }


    /**
     * Gets the switchName value for this ObservedSwitchConnection.
     *
     * @return switchName
     */
    public java.lang.String getSwitchName() {
        return switchName;
    }


    /**
     * Sets the switchName value for this ObservedSwitchConnection.
     *
     * @param switchName
     */
    public void setSwitchName(java.lang.String switchName) {
        this.switchName = switchName;
    }


    /**
     * Gets the switchPort value for this ObservedSwitchConnection.
     *
     * @return switchPort
     */
    public java.lang.String getSwitchPort() {
        return switchPort;
    }


    /**
     * Sets the switchPort value for this ObservedSwitchConnection.
     *
     * @param switchPort
     */
    public void setSwitchPort(java.lang.String switchPort) {
        this.switchPort = switchPort;
    }


    /**
     * Gets the hardwareAddress value for this ObservedSwitchConnection.
     *
     * @return hardwareAddress
     */
    public java.lang.String getHardwareAddress() {
        return hardwareAddress;
    }


    /**
     * Sets the hardwareAddress value for this ObservedSwitchConnection.
     *
     * @param hardwareAddress
     */
    public void setHardwareAddress(java.lang.String hardwareAddress) {
        this.hardwareAddress = hardwareAddress;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObservedSwitchConnection)) return false;
        ObservedSwitchConnection other = (ObservedSwitchConnection) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
                  ((this.switchName==null && other.getSwitchName()==null) ||
                   (this.switchName!=null &&
                    this.switchName.equals(other.getSwitchName()))) &&
                  ((this.switchPort==null && other.getSwitchPort()==null) ||
                   (this.switchPort!=null &&
                    this.switchPort.equals(other.getSwitchPort()))) &&
                  ((this.hardwareAddress==null && other.getHardwareAddress()==null) ||
                   (this.hardwareAddress!=null &&
                    this.hardwareAddress.equals(other.getHardwareAddress())));
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
        if (getSwitchName() != null) {
            _hashCode += getSwitchName().hashCode();
        }
        if (getSwitchPort() != null) {
            _hashCode += getSwitchPort().hashCode();
        }
        if (getHardwareAddress() != null) {
            _hashCode += getHardwareAddress().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObservedSwitchConnection.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "ObservedSwitchConnection"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("switchName");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "SwitchName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("switchPort");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "SwitchPort"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hardwareAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "HardwareAddress"));
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
