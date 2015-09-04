/**
 * Time.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetworkDataTypes;

public class Time  implements java.io.Serializable {
    private java.util.Calendar timeUTC;

    private java.lang.Long unixtime;

    public Time() {
    }

    public Time(
        java.util.Calendar timeUTC,
        java.lang.Long unixtime) {
        this.timeUTC = timeUTC;
        this.unixtime = unixtime;
    }


    /**
     * Gets the timeUTC value for this Time.
     *
     * @return timeUTC
     */
    public java.util.Calendar getTimeUTC() {
        return timeUTC;
    }


    /**
     * Sets the timeUTC value for this Time.
     *
     * @param timeUTC
     */
    public void setTimeUTC(java.util.Calendar timeUTC) {
        this.timeUTC = timeUTC;
    }


    /**
     * Gets the unixtime value for this Time.
     *
     * @return unixtime
     */
    public java.lang.Long getUnixtime() {
        return unixtime;
    }


    /**
     * Sets the unixtime value for this Time.
     *
     * @param unixtime
     */
    public void setUnixtime(java.lang.Long unixtime) {
        this.unixtime = unixtime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Time)) return false;
        Time other = (Time) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
                  ((this.timeUTC==null && other.getTimeUTC()==null) ||
                   (this.timeUTC!=null &&
                    this.timeUTC.equals(other.getTimeUTC()))) &&
                  ((this.unixtime==null && other.getUnixtime()==null) ||
                   (this.unixtime!=null &&
                    this.unixtime.equals(other.getUnixtime())));
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
        if (getTimeUTC() != null) {
            _hashCode += getTimeUTC().hashCode();
        }
        if (getUnixtime() != null) {
            _hashCode += getUnixtime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Time.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Time"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeUTC");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "TimeUTC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unixtime");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "unixtime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
