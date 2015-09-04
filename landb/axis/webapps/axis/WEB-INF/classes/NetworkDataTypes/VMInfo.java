/**
 * VMInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetworkDataTypes;

public class VMInfo  implements java.io.Serializable {
    private java.lang.String name;

    private boolean isVM;

    private java.lang.String VMParent;

    private java.lang.String[] VMGuestList;

    public VMInfo() {
    }

    public VMInfo(
        java.lang.String name,
        boolean isVM,
        java.lang.String VMParent,
        java.lang.String[] VMGuestList) {
        this.name = name;
        this.isVM = isVM;
        this.VMParent = VMParent;
        this.VMGuestList = VMGuestList;
    }


    /**
     * Gets the name value for this VMInfo.
     *
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this VMInfo.
     *
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the isVM value for this VMInfo.
     *
     * @return isVM
     */
    public boolean isIsVM() {
        return isVM;
    }


    /**
     * Sets the isVM value for this VMInfo.
     *
     * @param isVM
     */
    public void setIsVM(boolean isVM) {
        this.isVM = isVM;
    }


    /**
     * Gets the VMParent value for this VMInfo.
     *
     * @return VMParent
     */
    public java.lang.String getVMParent() {
        return VMParent;
    }


    /**
     * Sets the VMParent value for this VMInfo.
     *
     * @param VMParent
     */
    public void setVMParent(java.lang.String VMParent) {
        this.VMParent = VMParent;
    }


    /**
     * Gets the VMGuestList value for this VMInfo.
     *
     * @return VMGuestList
     */
    public java.lang.String[] getVMGuestList() {
        return VMGuestList;
    }


    /**
     * Sets the VMGuestList value for this VMInfo.
     *
     * @param VMGuestList
     */
    public void setVMGuestList(java.lang.String[] VMGuestList) {
        this.VMGuestList = VMGuestList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VMInfo)) return false;
        VMInfo other = (VMInfo) obj;
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
                  this.isVM == other.isIsVM() &&
                  ((this.VMParent==null && other.getVMParent()==null) ||
                   (this.VMParent!=null &&
                    this.VMParent.equals(other.getVMParent()))) &&
                  ((this.VMGuestList==null && other.getVMGuestList()==null) ||
                   (this.VMGuestList!=null &&
                    java.util.Arrays.equals(this.VMGuestList, other.getVMGuestList())));
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
        _hashCode += (isIsVM() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getVMParent() != null) {
            _hashCode += getVMParent().hashCode();
        }
        if (getVMGuestList() != null) {
            for (int i=0;
                    i<java.lang.reflect.Array.getLength(getVMGuestList());
                    i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getVMGuestList(), i);
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
        new org.apache.axis.description.TypeDesc(VMInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "VMInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isVM");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "IsVM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VMParent");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "VMParent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VMGuestList");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "VMGuestList"));
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
