/**
 * VMClusterInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetworkDataTypes;

public class VMClusterInfo  implements java.io.Serializable {
    private long ID;

    private java.lang.String name;

    private java.lang.String description;

    private NetworkDataTypes.Person responsiblePerson;

    private java.lang.String[] services;

    public VMClusterInfo() {
    }

    public VMClusterInfo(
        long ID,
        java.lang.String name,
        java.lang.String description,
        NetworkDataTypes.Person responsiblePerson,
        java.lang.String[] services) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.responsiblePerson = responsiblePerson;
        this.services = services;
    }


    /**
     * Gets the ID value for this VMClusterInfo.
     *
     * @return ID
     */
    public long getID() {
        return ID;
    }


    /**
     * Sets the ID value for this VMClusterInfo.
     *
     * @param ID
     */
    public void setID(long ID) {
        this.ID = ID;
    }


    /**
     * Gets the name value for this VMClusterInfo.
     *
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this VMClusterInfo.
     *
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the description value for this VMClusterInfo.
     *
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this VMClusterInfo.
     *
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the responsiblePerson value for this VMClusterInfo.
     *
     * @return responsiblePerson
     */
    public NetworkDataTypes.Person getResponsiblePerson() {
        return responsiblePerson;
    }


    /**
     * Sets the responsiblePerson value for this VMClusterInfo.
     *
     * @param responsiblePerson
     */
    public void setResponsiblePerson(NetworkDataTypes.Person responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }


    /**
     * Gets the services value for this VMClusterInfo.
     *
     * @return services
     */
    public java.lang.String[] getServices() {
        return services;
    }


    /**
     * Sets the services value for this VMClusterInfo.
     *
     * @param services
     */
    public void setServices(java.lang.String[] services) {
        this.services = services;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VMClusterInfo)) return false;
        VMClusterInfo other = (VMClusterInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
                  this.ID == other.getID() &&
                  ((this.name==null && other.getName()==null) ||
                   (this.name!=null &&
                    this.name.equals(other.getName()))) &&
                  ((this.description==null && other.getDescription()==null) ||
                   (this.description!=null &&
                    this.description.equals(other.getDescription()))) &&
                  ((this.responsiblePerson==null && other.getResponsiblePerson()==null) ||
                   (this.responsiblePerson!=null &&
                    this.responsiblePerson.equals(other.getResponsiblePerson()))) &&
                  ((this.services==null && other.getServices()==null) ||
                   (this.services!=null &&
                    java.util.Arrays.equals(this.services, other.getServices())));
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
        _hashCode += new Long(getID()).hashCode();
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getResponsiblePerson() != null) {
            _hashCode += getResponsiblePerson().hashCode();
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VMClusterInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "VMClusterInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ID");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responsiblePerson");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "ResponsiblePerson"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Person"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("services");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Services"));
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
