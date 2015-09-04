/**
 * SetInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetworkDataTypes;

public class SetInfo  implements java.io.Serializable {
    private java.lang.Long ID;

    private java.lang.String[] addresses;

    private java.lang.String[] services;

    private java.lang.String[] sets;

    public SetInfo() {
    }

    public SetInfo(
        java.lang.Long ID,
        java.lang.String[] addresses,
        java.lang.String[] services,
        java.lang.String[] sets) {
        this.ID = ID;
        this.addresses = addresses;
        this.services = services;
        this.sets = sets;
    }


    /**
     * Gets the ID value for this SetInfo.
     *
     * @return ID
     */
    public java.lang.Long getID() {
        return ID;
    }


    /**
     * Sets the ID value for this SetInfo.
     *
     * @param ID
     */
    public void setID(java.lang.Long ID) {
        this.ID = ID;
    }


    /**
     * Gets the addresses value for this SetInfo.
     *
     * @return addresses
     */
    public java.lang.String[] getAddresses() {
        return addresses;
    }


    /**
     * Sets the addresses value for this SetInfo.
     *
     * @param addresses
     */
    public void setAddresses(java.lang.String[] addresses) {
        this.addresses = addresses;
    }


    /**
     * Gets the services value for this SetInfo.
     *
     * @return services
     */
    public java.lang.String[] getServices() {
        return services;
    }


    /**
     * Sets the services value for this SetInfo.
     *
     * @param services
     */
    public void setServices(java.lang.String[] services) {
        this.services = services;
    }


    /**
     * Gets the sets value for this SetInfo.
     *
     * @return sets
     */
    public java.lang.String[] getSets() {
        return sets;
    }


    /**
     * Sets the sets value for this SetInfo.
     *
     * @param sets
     */
    public void setSets(java.lang.String[] sets) {
        this.sets = sets;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SetInfo)) return false;
        SetInfo other = (SetInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
                  ((this.ID==null && other.getID()==null) ||
                   (this.ID!=null &&
                    this.ID.equals(other.getID()))) &&
                  ((this.addresses==null && other.getAddresses()==null) ||
                   (this.addresses!=null &&
                    java.util.Arrays.equals(this.addresses, other.getAddresses()))) &&
                  ((this.services==null && other.getServices()==null) ||
                   (this.services!=null &&
                    java.util.Arrays.equals(this.services, other.getServices()))) &&
                  ((this.sets==null && other.getSets()==null) ||
                   (this.sets!=null &&
                    java.util.Arrays.equals(this.sets, other.getSets())));
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
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getAddresses() != null) {
            for (int i=0;
                    i<java.lang.reflect.Array.getLength(getAddresses());
                    i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAddresses(), i);
                if (obj != null &&
                        !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getServices() != null) {
            for (int i=0;
                    i<java.lang.reflect.Array.getLength(getServices());
                    i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getServices(), i);
                if (obj != null &&
                        !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSets() != null) {
            for (int i=0;
                    i<java.lang.reflect.Array.getLength(getSets());
                    i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSets(), i);
                if (obj != null &&
                        !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SetInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "SetInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ID");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addresses");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Addresses"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("services");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Services"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sets");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Sets"));
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
