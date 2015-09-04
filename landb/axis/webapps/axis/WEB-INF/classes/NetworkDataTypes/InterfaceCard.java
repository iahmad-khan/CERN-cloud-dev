/**
 * InterfaceCard.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetworkDataTypes;

public class InterfaceCard  implements java.io.Serializable {
    private java.lang.String hardwareAddress;

    private java.lang.String cardType;

    public InterfaceCard() {
    }

    public InterfaceCard(
        java.lang.String hardwareAddress,
        java.lang.String cardType) {
        this.hardwareAddress = hardwareAddress;
        this.cardType = cardType;
    }


    /**
     * Gets the hardwareAddress value for this InterfaceCard.
     *
     * @return hardwareAddress
     */
    public java.lang.String getHardwareAddress() {
        return hardwareAddress;
    }


    /**
     * Sets the hardwareAddress value for this InterfaceCard.
     *
     * @param hardwareAddress
     */
    public void setHardwareAddress(java.lang.String hardwareAddress) {
        this.hardwareAddress = hardwareAddress;
    }


    /**
     * Gets the cardType value for this InterfaceCard.
     *
     * @return cardType
     */
    public java.lang.String getCardType() {
        return cardType;
    }


    /**
     * Sets the cardType value for this InterfaceCard.
     *
     * @param cardType
     */
    public void setCardType(java.lang.String cardType) {
        this.cardType = cardType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InterfaceCard)) return false;
        InterfaceCard other = (InterfaceCard) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
                  ((this.hardwareAddress==null && other.getHardwareAddress()==null) ||
                   (this.hardwareAddress!=null &&
                    this.hardwareAddress.equals(other.getHardwareAddress()))) &&
                  ((this.cardType==null && other.getCardType()==null) ||
                   (this.cardType!=null &&
                    this.cardType.equals(other.getCardType())));
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
        if (getHardwareAddress() != null) {
            _hashCode += getHardwareAddress().hashCode();
        }
        if (getCardType() != null) {
            _hashCode += getCardType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InterfaceCard.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "InterfaceCard"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hardwareAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "HardwareAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardType");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "CardType"));
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
