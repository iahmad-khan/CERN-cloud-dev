/**
 * SwitchPort.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetworkDataTypes;

public class SwitchPort  implements java.io.Serializable {
    private java.lang.String name;

    private java.lang.String serviceName;

    private java.lang.String medium;

    private java.lang.String type;

    private java.lang.String status;

    private boolean inUse;

    private java.lang.String[] devices;

    private java.lang.String[] terminals;

    public SwitchPort() {
    }

    public SwitchPort(
        java.lang.String name,
        java.lang.String serviceName,
        java.lang.String medium,
        java.lang.String type,
        java.lang.String status,
        boolean inUse,
        java.lang.String[] devices,
        java.lang.String[] terminals) {
        this.name = name;
        this.serviceName = serviceName;
        this.medium = medium;
        this.type = type;
        this.status = status;
        this.inUse = inUse;
        this.devices = devices;
        this.terminals = terminals;
    }


    /**
     * Gets the name value for this SwitchPort.
     *
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this SwitchPort.
     *
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the serviceName value for this SwitchPort.
     *
     * @return serviceName
     */
    public java.lang.String getServiceName() {
        return serviceName;
    }


    /**
     * Sets the serviceName value for this SwitchPort.
     *
     * @param serviceName
     */
    public void setServiceName(java.lang.String serviceName) {
        this.serviceName = serviceName;
    }


    /**
     * Gets the medium value for this SwitchPort.
     *
     * @return medium
     */
    public java.lang.String getMedium() {
        return medium;
    }


    /**
     * Sets the medium value for this SwitchPort.
     *
     * @param medium
     */
    public void setMedium(java.lang.String medium) {
        this.medium = medium;
    }


    /**
     * Gets the type value for this SwitchPort.
     *
     * @return type
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this SwitchPort.
     *
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }


    /**
     * Gets the status value for this SwitchPort.
     *
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this SwitchPort.
     *
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the inUse value for this SwitchPort.
     *
     * @return inUse
     */
    public boolean isInUse() {
        return inUse;
    }


    /**
     * Sets the inUse value for this SwitchPort.
     *
     * @param inUse
     */
    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }


    /**
     * Gets the devices value for this SwitchPort.
     *
     * @return devices
     */
    public java.lang.String[] getDevices() {
        return devices;
    }


    /**
     * Sets the devices value for this SwitchPort.
     *
     * @param devices
     */
    public void setDevices(java.lang.String[] devices) {
        this.devices = devices;
    }


    /**
     * Gets the terminals value for this SwitchPort.
     *
     * @return terminals
     */
    public java.lang.String[] getTerminals() {
        return terminals;
    }


    /**
     * Sets the terminals value for this SwitchPort.
     *
     * @param terminals
     */
    public void setTerminals(java.lang.String[] terminals) {
        this.terminals = terminals;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SwitchPort)) return false;
        SwitchPort other = (SwitchPort) obj;
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
                  ((this.serviceName==null && other.getServiceName()==null) ||
                   (this.serviceName!=null &&
                    this.serviceName.equals(other.getServiceName()))) &&
                  ((this.medium==null && other.getMedium()==null) ||
                   (this.medium!=null &&
                    this.medium.equals(other.getMedium()))) &&
                  ((this.type==null && other.getType()==null) ||
                   (this.type!=null &&
                    this.type.equals(other.getType()))) &&
                  ((this.status==null && other.getStatus()==null) ||
                   (this.status!=null &&
                    this.status.equals(other.getStatus()))) &&
                  this.inUse == other.isInUse() &&
                  ((this.devices==null && other.getDevices()==null) ||
                   (this.devices!=null &&
                    java.util.Arrays.equals(this.devices, other.getDevices()))) &&
                  ((this.terminals==null && other.getTerminals()==null) ||
                   (this.terminals!=null &&
                    java.util.Arrays.equals(this.terminals, other.getTerminals())));
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
        if (getServiceName() != null) {
            _hashCode += getServiceName().hashCode();
        }
        if (getMedium() != null) {
            _hashCode += getMedium().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        _hashCode += (isInUse() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getDevices() != null) {
            for (int i=0;
                    i<java.lang.reflect.Array.getLength(getDevices());
                    i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDevices(), i);
                if (obj != null &&
                        !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTerminals() != null) {
            for (int i=0;
                    i<java.lang.reflect.Array.getLength(getTerminals());
                    i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTerminals(), i);
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
        new org.apache.axis.description.TypeDesc(SwitchPort.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "SwitchPort"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceName");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "ServiceName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("medium");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Medium"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inUse");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "InUse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("devices");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Devices"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("terminals");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Terminals"));
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
