/**
 * OutletLocation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetworkDataTypes;

public class OutletLocation  implements java.io.Serializable {
    private NetworkDataTypes.Location location;

    private NetworkDataTypes.Outlet outlet;

    public OutletLocation() {
    }

    public OutletLocation(
        NetworkDataTypes.Location location,
        NetworkDataTypes.Outlet outlet) {
        this.location = location;
        this.outlet = outlet;
    }


    /**
     * Gets the location value for this OutletLocation.
     *
     * @return location
     */
    public NetworkDataTypes.Location getLocation() {
        return location;
    }


    /**
     * Sets the location value for this OutletLocation.
     *
     * @param location
     */
    public void setLocation(NetworkDataTypes.Location location) {
        this.location = location;
    }


    /**
     * Gets the outlet value for this OutletLocation.
     *
     * @return outlet
     */
    public NetworkDataTypes.Outlet getOutlet() {
        return outlet;
    }


    /**
     * Sets the outlet value for this OutletLocation.
     *
     * @param outlet
     */
    public void setOutlet(NetworkDataTypes.Outlet outlet) {
        this.outlet = outlet;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OutletLocation)) return false;
        OutletLocation other = (OutletLocation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
                  ((this.location==null && other.getLocation()==null) ||
                   (this.location!=null &&
                    this.location.equals(other.getLocation()))) &&
                  ((this.outlet==null && other.getOutlet()==null) ||
                   (this.outlet!=null &&
                    this.outlet.equals(other.getOutlet())));
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
        if (getLocation() != null) {
            _hashCode += getLocation().hashCode();
        }
        if (getOutlet() != null) {
            _hashCode += getOutlet().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OutletLocation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "OutletLocation"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("location");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Location"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Location"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("outlet");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Outlet"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Outlet"));
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
