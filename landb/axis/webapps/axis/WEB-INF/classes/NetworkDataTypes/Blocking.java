/**
 * Blocking.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetworkDataTypes;

public class Blocking  implements java.io.Serializable {
    private NetworkDataTypes.Person by;

    private NetworkDataTypes.Time date;

    private java.lang.String reason;

    private java.lang.String unblockRole;

    public Blocking() {
    }

    public Blocking(
        NetworkDataTypes.Person by,
        NetworkDataTypes.Time date,
        java.lang.String reason,
        java.lang.String unblockRole) {
        this.by = by;
        this.date = date;
        this.reason = reason;
        this.unblockRole = unblockRole;
    }


    /**
     * Gets the by value for this Blocking.
     *
     * @return by
     */
    public NetworkDataTypes.Person getBy() {
        return by;
    }


    /**
     * Sets the by value for this Blocking.
     *
     * @param by
     */
    public void setBy(NetworkDataTypes.Person by) {
        this.by = by;
    }


    /**
     * Gets the date value for this Blocking.
     *
     * @return date
     */
    public NetworkDataTypes.Time getDate() {
        return date;
    }


    /**
     * Sets the date value for this Blocking.
     *
     * @param date
     */
    public void setDate(NetworkDataTypes.Time date) {
        this.date = date;
    }


    /**
     * Gets the reason value for this Blocking.
     *
     * @return reason
     */
    public java.lang.String getReason() {
        return reason;
    }


    /**
     * Sets the reason value for this Blocking.
     *
     * @param reason
     */
    public void setReason(java.lang.String reason) {
        this.reason = reason;
    }


    /**
     * Gets the unblockRole value for this Blocking.
     *
     * @return unblockRole
     */
    public java.lang.String getUnblockRole() {
        return unblockRole;
    }


    /**
     * Sets the unblockRole value for this Blocking.
     *
     * @param unblockRole
     */
    public void setUnblockRole(java.lang.String unblockRole) {
        this.unblockRole = unblockRole;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Blocking)) return false;
        Blocking other = (Blocking) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
                  ((this.by==null && other.getBy()==null) ||
                   (this.by!=null &&
                    this.by.equals(other.getBy()))) &&
                  ((this.date==null && other.getDate()==null) ||
                   (this.date!=null &&
                    this.date.equals(other.getDate()))) &&
                  ((this.reason==null && other.getReason()==null) ||
                   (this.reason!=null &&
                    this.reason.equals(other.getReason()))) &&
                  ((this.unblockRole==null && other.getUnblockRole()==null) ||
                   (this.unblockRole!=null &&
                    this.unblockRole.equals(other.getUnblockRole())));
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
        if (getBy() != null) {
            _hashCode += getBy().hashCode();
        }
        if (getDate() != null) {
            _hashCode += getDate().hashCode();
        }
        if (getReason() != null) {
            _hashCode += getReason().hashCode();
        }
        if (getUnblockRole() != null) {
            _hashCode += getUnblockRole().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Blocking.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Blocking"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("by");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "By"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Person"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("date");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Date"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Time"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reason");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Reason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unblockRole");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "UnblockRole"));
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
