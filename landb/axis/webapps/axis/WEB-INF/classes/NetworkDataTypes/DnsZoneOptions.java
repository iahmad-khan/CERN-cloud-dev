/**
 * DnsZoneOptions.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetworkDataTypes;

public class DnsZoneOptions  implements java.io.Serializable {
    private java.lang.Boolean internal;

    private java.lang.Boolean external;

    public DnsZoneOptions() {
    }

    public DnsZoneOptions(
        java.lang.Boolean internal,
        java.lang.Boolean external) {
        this.internal = internal;
        this.external = external;
    }


    /**
     * Gets the internal value for this DnsZoneOptions.
     *
     * @return internal
     */
    public java.lang.Boolean getInternal() {
        return internal;
    }


    /**
     * Sets the internal value for this DnsZoneOptions.
     *
     * @param internal
     */
    public void setInternal(java.lang.Boolean internal) {
        this.internal = internal;
    }


    /**
     * Gets the external value for this DnsZoneOptions.
     *
     * @return external
     */
    public java.lang.Boolean getExternal() {
        return external;
    }


    /**
     * Sets the external value for this DnsZoneOptions.
     *
     * @param external
     */
    public void setExternal(java.lang.Boolean external) {
        this.external = external;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DnsZoneOptions)) return false;
        DnsZoneOptions other = (DnsZoneOptions) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
                  ((this.internal==null && other.getInternal()==null) ||
                   (this.internal!=null &&
                    this.internal.equals(other.getInternal()))) &&
                  ((this.external==null && other.getExternal()==null) ||
                   (this.external!=null &&
                    this.external.equals(other.getExternal())));
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
        if (getInternal() != null) {
            _hashCode += getInternal().hashCode();
        }
        if (getExternal() != null) {
            _hashCode += getExternal().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DnsZoneOptions.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "DnsZoneOptions"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("internal");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Internal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("external");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "External"));
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
