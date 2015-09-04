/**
 * DNSDelegatedInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetworkDataTypes;

public class DNSDelegatedInput  implements java.io.Serializable {
    private java.lang.String domain;

    private java.lang.String view;

    private java.lang.String keyName;

    private java.lang.String description;

    private java.lang.String userDescription;

    public DNSDelegatedInput() {
    }

    public DNSDelegatedInput(
        java.lang.String domain,
        java.lang.String view,
        java.lang.String keyName,
        java.lang.String description,
        java.lang.String userDescription) {
        this.domain = domain;
        this.view = view;
        this.keyName = keyName;
        this.description = description;
        this.userDescription = userDescription;
    }


    /**
     * Gets the domain value for this DNSDelegatedInput.
     *
     * @return domain
     */
    public java.lang.String getDomain() {
        return domain;
    }


    /**
     * Sets the domain value for this DNSDelegatedInput.
     *
     * @param domain
     */
    public void setDomain(java.lang.String domain) {
        this.domain = domain;
    }


    /**
     * Gets the view value for this DNSDelegatedInput.
     *
     * @return view
     */
    public java.lang.String getView() {
        return view;
    }


    /**
     * Sets the view value for this DNSDelegatedInput.
     *
     * @param view
     */
    public void setView(java.lang.String view) {
        this.view = view;
    }


    /**
     * Gets the keyName value for this DNSDelegatedInput.
     *
     * @return keyName
     */
    public java.lang.String getKeyName() {
        return keyName;
    }


    /**
     * Sets the keyName value for this DNSDelegatedInput.
     *
     * @param keyName
     */
    public void setKeyName(java.lang.String keyName) {
        this.keyName = keyName;
    }


    /**
     * Gets the description value for this DNSDelegatedInput.
     *
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this DNSDelegatedInput.
     *
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the userDescription value for this DNSDelegatedInput.
     *
     * @return userDescription
     */
    public java.lang.String getUserDescription() {
        return userDescription;
    }


    /**
     * Sets the userDescription value for this DNSDelegatedInput.
     *
     * @param userDescription
     */
    public void setUserDescription(java.lang.String userDescription) {
        this.userDescription = userDescription;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DNSDelegatedInput)) return false;
        DNSDelegatedInput other = (DNSDelegatedInput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
                  ((this.domain==null && other.getDomain()==null) ||
                   (this.domain!=null &&
                    this.domain.equals(other.getDomain()))) &&
                  ((this.view==null && other.getView()==null) ||
                   (this.view!=null &&
                    this.view.equals(other.getView()))) &&
                  ((this.keyName==null && other.getKeyName()==null) ||
                   (this.keyName!=null &&
                    this.keyName.equals(other.getKeyName()))) &&
                  ((this.description==null && other.getDescription()==null) ||
                   (this.description!=null &&
                    this.description.equals(other.getDescription()))) &&
                  ((this.userDescription==null && other.getUserDescription()==null) ||
                   (this.userDescription!=null &&
                    this.userDescription.equals(other.getUserDescription())));
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
        if (getDomain() != null) {
            _hashCode += getDomain().hashCode();
        }
        if (getView() != null) {
            _hashCode += getView().hashCode();
        }
        if (getKeyName() != null) {
            _hashCode += getKeyName().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getUserDescription() != null) {
            _hashCode += getUserDescription().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DNSDelegatedInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "DNSDelegatedInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("domain");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Domain"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("view");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "View"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("keyName");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "KeyName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "UserDescription"));
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
