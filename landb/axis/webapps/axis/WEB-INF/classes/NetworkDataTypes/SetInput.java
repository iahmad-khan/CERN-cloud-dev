/**
 * SetInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetworkDataTypes;

public class SetInput  implements java.io.Serializable {
    private java.lang.String name;

    private java.lang.String domain;

    private NetworkDataTypes.Person responsiblePerson;

    private java.lang.String description;

    private java.lang.String projectUrl;

    private java.lang.String type;

    public SetInput() {
    }

    public SetInput(
        java.lang.String name,
        java.lang.String domain,
        NetworkDataTypes.Person responsiblePerson,
        java.lang.String description,
        java.lang.String projectUrl,
        java.lang.String type) {
        this.name = name;
        this.domain = domain;
        this.responsiblePerson = responsiblePerson;
        this.description = description;
        this.projectUrl = projectUrl;
        this.type = type;
    }


    /**
     * Gets the name value for this SetInput.
     *
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this SetInput.
     *
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the domain value for this SetInput.
     *
     * @return domain
     */
    public java.lang.String getDomain() {
        return domain;
    }


    /**
     * Sets the domain value for this SetInput.
     *
     * @param domain
     */
    public void setDomain(java.lang.String domain) {
        this.domain = domain;
    }


    /**
     * Gets the responsiblePerson value for this SetInput.
     *
     * @return responsiblePerson
     */
    public NetworkDataTypes.Person getResponsiblePerson() {
        return responsiblePerson;
    }


    /**
     * Sets the responsiblePerson value for this SetInput.
     *
     * @param responsiblePerson
     */
    public void setResponsiblePerson(NetworkDataTypes.Person responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }


    /**
     * Gets the description value for this SetInput.
     *
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this SetInput.
     *
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the projectUrl value for this SetInput.
     *
     * @return projectUrl
     */
    public java.lang.String getProjectUrl() {
        return projectUrl;
    }


    /**
     * Sets the projectUrl value for this SetInput.
     *
     * @param projectUrl
     */
    public void setProjectUrl(java.lang.String projectUrl) {
        this.projectUrl = projectUrl;
    }


    /**
     * Gets the type value for this SetInput.
     *
     * @return type
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this SetInput.
     *
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SetInput)) return false;
        SetInput other = (SetInput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
                  ((this.name==null && other.getName()==null) ||
                   (this.name!=null &&
                    this.name.equals(other.getName()))) &&
                  ((this.domain==null && other.getDomain()==null) ||
                   (this.domain!=null &&
                    this.domain.equals(other.getDomain()))) &&
                  ((this.responsiblePerson==null && other.getResponsiblePerson()==null) ||
                   (this.responsiblePerson!=null &&
                    this.responsiblePerson.equals(other.getResponsiblePerson()))) &&
                  ((this.description==null && other.getDescription()==null) ||
                   (this.description!=null &&
                    this.description.equals(other.getDescription()))) &&
                  ((this.projectUrl==null && other.getProjectUrl()==null) ||
                   (this.projectUrl!=null &&
                    this.projectUrl.equals(other.getProjectUrl()))) &&
                  ((this.type==null && other.getType()==null) ||
                   (this.type!=null &&
                    this.type.equals(other.getType())));
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
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getDomain() != null) {
            _hashCode += getDomain().hashCode();
        }
        if (getResponsiblePerson() != null) {
            _hashCode += getResponsiblePerson().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getProjectUrl() != null) {
            _hashCode += getProjectUrl().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SetInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "SetInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("domain");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Domain"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responsiblePerson");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "ResponsiblePerson"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Person"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("projectUrl");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "ProjectUrl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Type"));
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
