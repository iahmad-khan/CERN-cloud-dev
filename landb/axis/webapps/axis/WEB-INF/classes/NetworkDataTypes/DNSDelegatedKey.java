/**
 * DNSDelegatedKey.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetworkDataTypes;

public class DNSDelegatedKey  implements java.io.Serializable {
    private java.lang.Long ID;

    private java.lang.String keyName;

    private NetworkDataTypes.Person responsible;

    public DNSDelegatedKey() {
    }

    public DNSDelegatedKey(
        java.lang.Long ID,
        java.lang.String keyName,
        NetworkDataTypes.Person responsible) {
        this.ID = ID;
        this.keyName = keyName;
        this.responsible = responsible;
    }


    /**
     * Gets the ID value for this DNSDelegatedKey.
     *
     * @return ID
     */
    public java.lang.Long getID() {
        return ID;
    }


    /**
     * Sets the ID value for this DNSDelegatedKey.
     *
     * @param ID
     */
    public void setID(java.lang.Long ID) {
        this.ID = ID;
    }


    /**
     * Gets the keyName value for this DNSDelegatedKey.
     *
     * @return keyName
     */
    public java.lang.String getKeyName() {
        return keyName;
    }


    /**
     * Sets the keyName value for this DNSDelegatedKey.
     *
     * @param keyName
     */
    public void setKeyName(java.lang.String keyName) {
        this.keyName = keyName;
    }


    /**
     * Gets the responsible value for this DNSDelegatedKey.
     *
     * @return responsible
     */
    public NetworkDataTypes.Person getResponsible() {
        return responsible;
    }


    /**
     * Sets the responsible value for this DNSDelegatedKey.
     *
     * @param responsible
     */
    public void setResponsible(NetworkDataTypes.Person responsible) {
        this.responsible = responsible;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DNSDelegatedKey)) return false;
        DNSDelegatedKey other = (DNSDelegatedKey) obj;
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
                  ((this.keyName==null && other.getKeyName()==null) ||
                   (this.keyName!=null &&
                    this.keyName.equals(other.getKeyName()))) &&
                  ((this.responsible==null && other.getResponsible()==null) ||
                   (this.responsible!=null &&
                    this.responsible.equals(other.getResponsible())));
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
        if (getKeyName() != null) {
            _hashCode += getKeyName().hashCode();
        }
        if (getResponsible() != null) {
            _hashCode += getResponsible().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DNSDelegatedKey.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "DNSDelegatedKey"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ID");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("keyName");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "KeyName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responsible");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Responsible"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Person"));
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
