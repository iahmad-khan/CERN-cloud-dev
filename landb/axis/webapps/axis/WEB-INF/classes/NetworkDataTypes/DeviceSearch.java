/**
 * DeviceSearch.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetworkDataTypes;

public class DeviceSearch  implements java.io.Serializable {
    private java.lang.String name;

    private java.lang.String surname;

    private java.lang.String firstName;

    private NetworkDataTypes.Location location;

    private java.lang.String outletID;

    private java.lang.String tag;

    private java.lang.String serialNumber;

    private java.lang.String inventoryNumber;

    private java.lang.String hardwareAddress;

    private java.lang.String IPAddress;

    private java.lang.String rackName;

    private java.lang.Long personID;

    private java.lang.String domain;

    private java.lang.String responsibleDepGroup;

    private java.lang.String userResponsibleDepGroup;

    private java.lang.String operatingSystem;

    private java.util.Calendar lastChangeDate;

    private java.util.Calendar lastActiveDate;

    public DeviceSearch() {
    }

    public DeviceSearch(
        java.lang.String name,
        java.lang.String surname,
        java.lang.String firstName,
        NetworkDataTypes.Location location,
        java.lang.String outletID,
        java.lang.String tag,
        java.lang.String serialNumber,
        java.lang.String inventoryNumber,
        java.lang.String hardwareAddress,
        java.lang.String IPAddress,
        java.lang.String rackName,
        java.lang.Long personID,
        java.lang.String domain,
        java.lang.String responsibleDepGroup,
        java.lang.String userResponsibleDepGroup,
        java.lang.String operatingSystem,
        java.util.Calendar lastChangeDate,
        java.util.Calendar lastActiveDate) {
        this.name = name;
        this.surname = surname;
        this.firstName = firstName;
        this.location = location;
        this.outletID = outletID;
        this.tag = tag;
        this.serialNumber = serialNumber;
        this.inventoryNumber = inventoryNumber;
        this.hardwareAddress = hardwareAddress;
        this.IPAddress = IPAddress;
        this.rackName = rackName;
        this.personID = personID;
        this.domain = domain;
        this.responsibleDepGroup = responsibleDepGroup;
        this.userResponsibleDepGroup = userResponsibleDepGroup;
        this.operatingSystem = operatingSystem;
        this.lastChangeDate = lastChangeDate;
        this.lastActiveDate = lastActiveDate;
    }


    /**
     * Gets the name value for this DeviceSearch.
     *
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this DeviceSearch.
     *
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the surname value for this DeviceSearch.
     *
     * @return surname
     */
    public java.lang.String getSurname() {
        return surname;
    }


    /**
     * Sets the surname value for this DeviceSearch.
     *
     * @param surname
     */
    public void setSurname(java.lang.String surname) {
        this.surname = surname;
    }


    /**
     * Gets the firstName value for this DeviceSearch.
     *
     * @return firstName
     */
    public java.lang.String getFirstName() {
        return firstName;
    }


    /**
     * Sets the firstName value for this DeviceSearch.
     *
     * @param firstName
     */
    public void setFirstName(java.lang.String firstName) {
        this.firstName = firstName;
    }


    /**
     * Gets the location value for this DeviceSearch.
     *
     * @return location
     */
    public NetworkDataTypes.Location getLocation() {
        return location;
    }


    /**
     * Sets the location value for this DeviceSearch.
     *
     * @param location
     */
    public void setLocation(NetworkDataTypes.Location location) {
        this.location = location;
    }


    /**
     * Gets the outletID value for this DeviceSearch.
     *
     * @return outletID
     */
    public java.lang.String getOutletID() {
        return outletID;
    }


    /**
     * Sets the outletID value for this DeviceSearch.
     *
     * @param outletID
     */
    public void setOutletID(java.lang.String outletID) {
        this.outletID = outletID;
    }


    /**
     * Gets the tag value for this DeviceSearch.
     *
     * @return tag
     */
    public java.lang.String getTag() {
        return tag;
    }


    /**
     * Sets the tag value for this DeviceSearch.
     *
     * @param tag
     */
    public void setTag(java.lang.String tag) {
        this.tag = tag;
    }


    /**
     * Gets the serialNumber value for this DeviceSearch.
     *
     * @return serialNumber
     */
    public java.lang.String getSerialNumber() {
        return serialNumber;
    }


    /**
     * Sets the serialNumber value for this DeviceSearch.
     *
     * @param serialNumber
     */
    public void setSerialNumber(java.lang.String serialNumber) {
        this.serialNumber = serialNumber;
    }


    /**
     * Gets the inventoryNumber value for this DeviceSearch.
     *
     * @return inventoryNumber
     */
    public java.lang.String getInventoryNumber() {
        return inventoryNumber;
    }


    /**
     * Sets the inventoryNumber value for this DeviceSearch.
     *
     * @param inventoryNumber
     */
    public void setInventoryNumber(java.lang.String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }


    /**
     * Gets the hardwareAddress value for this DeviceSearch.
     *
     * @return hardwareAddress
     */
    public java.lang.String getHardwareAddress() {
        return hardwareAddress;
    }


    /**
     * Sets the hardwareAddress value for this DeviceSearch.
     *
     * @param hardwareAddress
     */
    public void setHardwareAddress(java.lang.String hardwareAddress) {
        this.hardwareAddress = hardwareAddress;
    }


    /**
     * Gets the IPAddress value for this DeviceSearch.
     *
     * @return IPAddress
     */
    public java.lang.String getIPAddress() {
        return IPAddress;
    }


    /**
     * Sets the IPAddress value for this DeviceSearch.
     *
     * @param IPAddress
     */
    public void setIPAddress(java.lang.String IPAddress) {
        this.IPAddress = IPAddress;
    }


    /**
     * Gets the rackName value for this DeviceSearch.
     *
     * @return rackName
     */
    public java.lang.String getRackName() {
        return rackName;
    }


    /**
     * Sets the rackName value for this DeviceSearch.
     *
     * @param rackName
     */
    public void setRackName(java.lang.String rackName) {
        this.rackName = rackName;
    }


    /**
     * Gets the personID value for this DeviceSearch.
     *
     * @return personID
     */
    public java.lang.Long getPersonID() {
        return personID;
    }


    /**
     * Sets the personID value for this DeviceSearch.
     *
     * @param personID
     */
    public void setPersonID(java.lang.Long personID) {
        this.personID = personID;
    }


    /**
     * Gets the domain value for this DeviceSearch.
     *
     * @return domain
     */
    public java.lang.String getDomain() {
        return domain;
    }


    /**
     * Sets the domain value for this DeviceSearch.
     *
     * @param domain
     */
    public void setDomain(java.lang.String domain) {
        this.domain = domain;
    }


    /**
     * Gets the responsibleDepGroup value for this DeviceSearch.
     *
     * @return responsibleDepGroup
     */
    public java.lang.String getResponsibleDepGroup() {
        return responsibleDepGroup;
    }


    /**
     * Sets the responsibleDepGroup value for this DeviceSearch.
     *
     * @param responsibleDepGroup
     */
    public void setResponsibleDepGroup(java.lang.String responsibleDepGroup) {
        this.responsibleDepGroup = responsibleDepGroup;
    }


    /**
     * Gets the userResponsibleDepGroup value for this DeviceSearch.
     *
     * @return userResponsibleDepGroup
     */
    public java.lang.String getUserResponsibleDepGroup() {
        return userResponsibleDepGroup;
    }


    /**
     * Sets the userResponsibleDepGroup value for this DeviceSearch.
     *
     * @param userResponsibleDepGroup
     */
    public void setUserResponsibleDepGroup(java.lang.String userResponsibleDepGroup) {
        this.userResponsibleDepGroup = userResponsibleDepGroup;
    }


    /**
     * Gets the operatingSystem value for this DeviceSearch.
     *
     * @return operatingSystem
     */
    public java.lang.String getOperatingSystem() {
        return operatingSystem;
    }


    /**
     * Sets the operatingSystem value for this DeviceSearch.
     *
     * @param operatingSystem
     */
    public void setOperatingSystem(java.lang.String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }


    /**
     * Gets the lastChangeDate value for this DeviceSearch.
     *
     * @return lastChangeDate
     */
    public java.util.Calendar getLastChangeDate() {
        return lastChangeDate;
    }


    /**
     * Sets the lastChangeDate value for this DeviceSearch.
     *
     * @param lastChangeDate
     */
    public void setLastChangeDate(java.util.Calendar lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }


    /**
     * Gets the lastActiveDate value for this DeviceSearch.
     *
     * @return lastActiveDate
     */
    public java.util.Calendar getLastActiveDate() {
        return lastActiveDate;
    }


    /**
     * Sets the lastActiveDate value for this DeviceSearch.
     *
     * @param lastActiveDate
     */
    public void setLastActiveDate(java.util.Calendar lastActiveDate) {
        this.lastActiveDate = lastActiveDate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DeviceSearch)) return false;
        DeviceSearch other = (DeviceSearch) obj;
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
                  ((this.surname==null && other.getSurname()==null) ||
                   (this.surname!=null &&
                    this.surname.equals(other.getSurname()))) &&
                  ((this.firstName==null && other.getFirstName()==null) ||
                   (this.firstName!=null &&
                    this.firstName.equals(other.getFirstName()))) &&
                  ((this.location==null && other.getLocation()==null) ||
                   (this.location!=null &&
                    this.location.equals(other.getLocation()))) &&
                  ((this.outletID==null && other.getOutletID()==null) ||
                   (this.outletID!=null &&
                    this.outletID.equals(other.getOutletID()))) &&
                  ((this.tag==null && other.getTag()==null) ||
                   (this.tag!=null &&
                    this.tag.equals(other.getTag()))) &&
                  ((this.serialNumber==null && other.getSerialNumber()==null) ||
                   (this.serialNumber!=null &&
                    this.serialNumber.equals(other.getSerialNumber()))) &&
                  ((this.inventoryNumber==null && other.getInventoryNumber()==null) ||
                   (this.inventoryNumber!=null &&
                    this.inventoryNumber.equals(other.getInventoryNumber()))) &&
                  ((this.hardwareAddress==null && other.getHardwareAddress()==null) ||
                   (this.hardwareAddress!=null &&
                    this.hardwareAddress.equals(other.getHardwareAddress()))) &&
                  ((this.IPAddress==null && other.getIPAddress()==null) ||
                   (this.IPAddress!=null &&
                    this.IPAddress.equals(other.getIPAddress()))) &&
                  ((this.rackName==null && other.getRackName()==null) ||
                   (this.rackName!=null &&
                    this.rackName.equals(other.getRackName()))) &&
                  ((this.personID==null && other.getPersonID()==null) ||
                   (this.personID!=null &&
                    this.personID.equals(other.getPersonID()))) &&
                  ((this.domain==null && other.getDomain()==null) ||
                   (this.domain!=null &&
                    this.domain.equals(other.getDomain()))) &&
                  ((this.responsibleDepGroup==null && other.getResponsibleDepGroup()==null) ||
                   (this.responsibleDepGroup!=null &&
                    this.responsibleDepGroup.equals(other.getResponsibleDepGroup()))) &&
                  ((this.userResponsibleDepGroup==null && other.getUserResponsibleDepGroup()==null) ||
                   (this.userResponsibleDepGroup!=null &&
                    this.userResponsibleDepGroup.equals(other.getUserResponsibleDepGroup()))) &&
                  ((this.operatingSystem==null && other.getOperatingSystem()==null) ||
                   (this.operatingSystem!=null &&
                    this.operatingSystem.equals(other.getOperatingSystem()))) &&
                  ((this.lastChangeDate==null && other.getLastChangeDate()==null) ||
                   (this.lastChangeDate!=null &&
                    this.lastChangeDate.equals(other.getLastChangeDate()))) &&
                  ((this.lastActiveDate==null && other.getLastActiveDate()==null) ||
                   (this.lastActiveDate!=null &&
                    this.lastActiveDate.equals(other.getLastActiveDate())));
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
        if (getSurname() != null) {
            _hashCode += getSurname().hashCode();
        }
        if (getFirstName() != null) {
            _hashCode += getFirstName().hashCode();
        }
        if (getLocation() != null) {
            _hashCode += getLocation().hashCode();
        }
        if (getOutletID() != null) {
            _hashCode += getOutletID().hashCode();
        }
        if (getTag() != null) {
            _hashCode += getTag().hashCode();
        }
        if (getSerialNumber() != null) {
            _hashCode += getSerialNumber().hashCode();
        }
        if (getInventoryNumber() != null) {
            _hashCode += getInventoryNumber().hashCode();
        }
        if (getHardwareAddress() != null) {
            _hashCode += getHardwareAddress().hashCode();
        }
        if (getIPAddress() != null) {
            _hashCode += getIPAddress().hashCode();
        }
        if (getRackName() != null) {
            _hashCode += getRackName().hashCode();
        }
        if (getPersonID() != null) {
            _hashCode += getPersonID().hashCode();
        }
        if (getDomain() != null) {
            _hashCode += getDomain().hashCode();
        }
        if (getResponsibleDepGroup() != null) {
            _hashCode += getResponsibleDepGroup().hashCode();
        }
        if (getUserResponsibleDepGroup() != null) {
            _hashCode += getUserResponsibleDepGroup().hashCode();
        }
        if (getOperatingSystem() != null) {
            _hashCode += getOperatingSystem().hashCode();
        }
        if (getLastChangeDate() != null) {
            _hashCode += getLastChangeDate().hashCode();
        }
        if (getLastActiveDate() != null) {
            _hashCode += getLastActiveDate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DeviceSearch.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "DeviceSearch"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("surname");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Surname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firstName");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "FirstName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("location");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Location"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Location"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("outletID");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "OutletID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tag");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Tag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serialNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "SerialNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inventoryNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "InventoryNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hardwareAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "HardwareAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IPAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "IPAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rackName");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "RackName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("personID");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "PersonID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("domain");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Domain"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responsibleDepGroup");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "ResponsibleDepGroup"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userResponsibleDepGroup");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "UserResponsibleDepGroup"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operatingSystem");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "OperatingSystem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastChangeDate");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "LastChangeDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastActiveDate");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "LastActiveDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
