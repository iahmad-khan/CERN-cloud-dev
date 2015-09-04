/**
 * DeviceBasicInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetworkDataTypes;

public class DeviceBasicInfo  implements java.io.Serializable {
    private java.lang.String deviceName;

    private NetworkDataTypes.Location location;

    private java.lang.String zone;

    private java.lang.String status;

    private java.lang.String manufacturer;

    private java.lang.String model;

    private java.lang.String genericType;

    private java.lang.String description;

    private java.lang.String tag;

    private java.lang.String serialNumber;

    private NetworkDataTypes.OperatingSystem operatingSystem;

    private java.lang.String inventoryNumber;

    private NetworkDataTypes.Time startDate;

    private NetworkDataTypes.Time endDate;

    private NetworkDataTypes.Person responsiblePerson;

    private NetworkDataTypes.Person userPerson;

    private boolean HCPResponse;

    private NetworkDataTypes.Time lastChangeDate;

    private java.lang.Boolean IPv6Ready;

    public DeviceBasicInfo() {
    }

    public DeviceBasicInfo(
        java.lang.String deviceName,
        NetworkDataTypes.Location location,
        java.lang.String zone,
        java.lang.String status,
        java.lang.String manufacturer,
        java.lang.String model,
        java.lang.String genericType,
        java.lang.String description,
        java.lang.String tag,
        java.lang.String serialNumber,
        NetworkDataTypes.OperatingSystem operatingSystem,
        java.lang.String inventoryNumber,
        NetworkDataTypes.Time startDate,
        NetworkDataTypes.Time endDate,
        NetworkDataTypes.Person responsiblePerson,
        NetworkDataTypes.Person userPerson,
        boolean HCPResponse,
        NetworkDataTypes.Time lastChangeDate,
        java.lang.Boolean IPv6Ready) {
        this.deviceName = deviceName;
        this.location = location;
        this.zone = zone;
        this.status = status;
        this.manufacturer = manufacturer;
        this.model = model;
        this.genericType = genericType;
        this.description = description;
        this.tag = tag;
        this.serialNumber = serialNumber;
        this.operatingSystem = operatingSystem;
        this.inventoryNumber = inventoryNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.responsiblePerson = responsiblePerson;
        this.userPerson = userPerson;
        this.HCPResponse = HCPResponse;
        this.lastChangeDate = lastChangeDate;
        this.IPv6Ready = IPv6Ready;
    }


    /**
     * Gets the deviceName value for this DeviceBasicInfo.
     *
     * @return deviceName
     */
    public java.lang.String getDeviceName() {
        return deviceName;
    }


    /**
     * Sets the deviceName value for this DeviceBasicInfo.
     *
     * @param deviceName
     */
    public void setDeviceName(java.lang.String deviceName) {
        this.deviceName = deviceName;
    }


    /**
     * Gets the location value for this DeviceBasicInfo.
     *
     * @return location
     */
    public NetworkDataTypes.Location getLocation() {
        return location;
    }


    /**
     * Sets the location value for this DeviceBasicInfo.
     *
     * @param location
     */
    public void setLocation(NetworkDataTypes.Location location) {
        this.location = location;
    }


    /**
     * Gets the zone value for this DeviceBasicInfo.
     *
     * @return zone
     */
    public java.lang.String getZone() {
        return zone;
    }


    /**
     * Sets the zone value for this DeviceBasicInfo.
     *
     * @param zone
     */
    public void setZone(java.lang.String zone) {
        this.zone = zone;
    }


    /**
     * Gets the status value for this DeviceBasicInfo.
     *
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this DeviceBasicInfo.
     *
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the manufacturer value for this DeviceBasicInfo.
     *
     * @return manufacturer
     */
    public java.lang.String getManufacturer() {
        return manufacturer;
    }


    /**
     * Sets the manufacturer value for this DeviceBasicInfo.
     *
     * @param manufacturer
     */
    public void setManufacturer(java.lang.String manufacturer) {
        this.manufacturer = manufacturer;
    }


    /**
     * Gets the model value for this DeviceBasicInfo.
     *
     * @return model
     */
    public java.lang.String getModel() {
        return model;
    }


    /**
     * Sets the model value for this DeviceBasicInfo.
     *
     * @param model
     */
    public void setModel(java.lang.String model) {
        this.model = model;
    }


    /**
     * Gets the genericType value for this DeviceBasicInfo.
     *
     * @return genericType
     */
    public java.lang.String getGenericType() {
        return genericType;
    }


    /**
     * Sets the genericType value for this DeviceBasicInfo.
     *
     * @param genericType
     */
    public void setGenericType(java.lang.String genericType) {
        this.genericType = genericType;
    }


    /**
     * Gets the description value for this DeviceBasicInfo.
     *
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this DeviceBasicInfo.
     *
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the tag value for this DeviceBasicInfo.
     *
     * @return tag
     */
    public java.lang.String getTag() {
        return tag;
    }


    /**
     * Sets the tag value for this DeviceBasicInfo.
     *
     * @param tag
     */
    public void setTag(java.lang.String tag) {
        this.tag = tag;
    }


    /**
     * Gets the serialNumber value for this DeviceBasicInfo.
     *
     * @return serialNumber
     */
    public java.lang.String getSerialNumber() {
        return serialNumber;
    }


    /**
     * Sets the serialNumber value for this DeviceBasicInfo.
     *
     * @param serialNumber
     */
    public void setSerialNumber(java.lang.String serialNumber) {
        this.serialNumber = serialNumber;
    }


    /**
     * Gets the operatingSystem value for this DeviceBasicInfo.
     *
     * @return operatingSystem
     */
    public NetworkDataTypes.OperatingSystem getOperatingSystem() {
        return operatingSystem;
    }


    /**
     * Sets the operatingSystem value for this DeviceBasicInfo.
     *
     * @param operatingSystem
     */
    public void setOperatingSystem(NetworkDataTypes.OperatingSystem operatingSystem) {
        this.operatingSystem = operatingSystem;
    }


    /**
     * Gets the inventoryNumber value for this DeviceBasicInfo.
     *
     * @return inventoryNumber
     */
    public java.lang.String getInventoryNumber() {
        return inventoryNumber;
    }


    /**
     * Sets the inventoryNumber value for this DeviceBasicInfo.
     *
     * @param inventoryNumber
     */
    public void setInventoryNumber(java.lang.String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }


    /**
     * Gets the startDate value for this DeviceBasicInfo.
     *
     * @return startDate
     */
    public NetworkDataTypes.Time getStartDate() {
        return startDate;
    }


    /**
     * Sets the startDate value for this DeviceBasicInfo.
     *
     * @param startDate
     */
    public void setStartDate(NetworkDataTypes.Time startDate) {
        this.startDate = startDate;
    }


    /**
     * Gets the endDate value for this DeviceBasicInfo.
     *
     * @return endDate
     */
    public NetworkDataTypes.Time getEndDate() {
        return endDate;
    }


    /**
     * Sets the endDate value for this DeviceBasicInfo.
     *
     * @param endDate
     */
    public void setEndDate(NetworkDataTypes.Time endDate) {
        this.endDate = endDate;
    }


    /**
     * Gets the responsiblePerson value for this DeviceBasicInfo.
     *
     * @return responsiblePerson
     */
    public NetworkDataTypes.Person getResponsiblePerson() {
        return responsiblePerson;
    }


    /**
     * Sets the responsiblePerson value for this DeviceBasicInfo.
     *
     * @param responsiblePerson
     */
    public void setResponsiblePerson(NetworkDataTypes.Person responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }


    /**
     * Gets the userPerson value for this DeviceBasicInfo.
     *
     * @return userPerson
     */
    public NetworkDataTypes.Person getUserPerson() {
        return userPerson;
    }


    /**
     * Sets the userPerson value for this DeviceBasicInfo.
     *
     * @param userPerson
     */
    public void setUserPerson(NetworkDataTypes.Person userPerson) {
        this.userPerson = userPerson;
    }


    /**
     * Gets the HCPResponse value for this DeviceBasicInfo.
     *
     * @return HCPResponse
     */
    public boolean isHCPResponse() {
        return HCPResponse;
    }


    /**
     * Sets the HCPResponse value for this DeviceBasicInfo.
     *
     * @param HCPResponse
     */
    public void setHCPResponse(boolean HCPResponse) {
        this.HCPResponse = HCPResponse;
    }


    /**
     * Gets the lastChangeDate value for this DeviceBasicInfo.
     *
     * @return lastChangeDate
     */
    public NetworkDataTypes.Time getLastChangeDate() {
        return lastChangeDate;
    }


    /**
     * Sets the lastChangeDate value for this DeviceBasicInfo.
     *
     * @param lastChangeDate
     */
    public void setLastChangeDate(NetworkDataTypes.Time lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }


    /**
     * Gets the IPv6Ready value for this DeviceBasicInfo.
     *
     * @return IPv6Ready
     */
    public java.lang.Boolean getIPv6Ready() {
        return IPv6Ready;
    }


    /**
     * Sets the IPv6Ready value for this DeviceBasicInfo.
     *
     * @param IPv6Ready
     */
    public void setIPv6Ready(java.lang.Boolean IPv6Ready) {
        this.IPv6Ready = IPv6Ready;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DeviceBasicInfo)) return false;
        DeviceBasicInfo other = (DeviceBasicInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
                  ((this.deviceName==null && other.getDeviceName()==null) ||
                   (this.deviceName!=null &&
                    this.deviceName.equals(other.getDeviceName()))) &&
                  ((this.location==null && other.getLocation()==null) ||
                   (this.location!=null &&
                    this.location.equals(other.getLocation()))) &&
                  ((this.zone==null && other.getZone()==null) ||
                   (this.zone!=null &&
                    this.zone.equals(other.getZone()))) &&
                  ((this.status==null && other.getStatus()==null) ||
                   (this.status!=null &&
                    this.status.equals(other.getStatus()))) &&
                  ((this.manufacturer==null && other.getManufacturer()==null) ||
                   (this.manufacturer!=null &&
                    this.manufacturer.equals(other.getManufacturer()))) &&
                  ((this.model==null && other.getModel()==null) ||
                   (this.model!=null &&
                    this.model.equals(other.getModel()))) &&
                  ((this.genericType==null && other.getGenericType()==null) ||
                   (this.genericType!=null &&
                    this.genericType.equals(other.getGenericType()))) &&
                  ((this.description==null && other.getDescription()==null) ||
                   (this.description!=null &&
                    this.description.equals(other.getDescription()))) &&
                  ((this.tag==null && other.getTag()==null) ||
                   (this.tag!=null &&
                    this.tag.equals(other.getTag()))) &&
                  ((this.serialNumber==null && other.getSerialNumber()==null) ||
                   (this.serialNumber!=null &&
                    this.serialNumber.equals(other.getSerialNumber()))) &&
                  ((this.operatingSystem==null && other.getOperatingSystem()==null) ||
                   (this.operatingSystem!=null &&
                    this.operatingSystem.equals(other.getOperatingSystem()))) &&
                  ((this.inventoryNumber==null && other.getInventoryNumber()==null) ||
                   (this.inventoryNumber!=null &&
                    this.inventoryNumber.equals(other.getInventoryNumber()))) &&
                  ((this.startDate==null && other.getStartDate()==null) ||
                   (this.startDate!=null &&
                    this.startDate.equals(other.getStartDate()))) &&
                  ((this.endDate==null && other.getEndDate()==null) ||
                   (this.endDate!=null &&
                    this.endDate.equals(other.getEndDate()))) &&
                  ((this.responsiblePerson==null && other.getResponsiblePerson()==null) ||
                   (this.responsiblePerson!=null &&
                    this.responsiblePerson.equals(other.getResponsiblePerson()))) &&
                  ((this.userPerson==null && other.getUserPerson()==null) ||
                   (this.userPerson!=null &&
                    this.userPerson.equals(other.getUserPerson()))) &&
                  this.HCPResponse == other.isHCPResponse() &&
                  ((this.lastChangeDate==null && other.getLastChangeDate()==null) ||
                   (this.lastChangeDate!=null &&
                    this.lastChangeDate.equals(other.getLastChangeDate()))) &&
                  ((this.IPv6Ready==null && other.getIPv6Ready()==null) ||
                   (this.IPv6Ready!=null &&
                    this.IPv6Ready.equals(other.getIPv6Ready())));
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
        if (getDeviceName() != null) {
            _hashCode += getDeviceName().hashCode();
        }
        if (getLocation() != null) {
            _hashCode += getLocation().hashCode();
        }
        if (getZone() != null) {
            _hashCode += getZone().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getManufacturer() != null) {
            _hashCode += getManufacturer().hashCode();
        }
        if (getModel() != null) {
            _hashCode += getModel().hashCode();
        }
        if (getGenericType() != null) {
            _hashCode += getGenericType().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getTag() != null) {
            _hashCode += getTag().hashCode();
        }
        if (getSerialNumber() != null) {
            _hashCode += getSerialNumber().hashCode();
        }
        if (getOperatingSystem() != null) {
            _hashCode += getOperatingSystem().hashCode();
        }
        if (getInventoryNumber() != null) {
            _hashCode += getInventoryNumber().hashCode();
        }
        if (getStartDate() != null) {
            _hashCode += getStartDate().hashCode();
        }
        if (getEndDate() != null) {
            _hashCode += getEndDate().hashCode();
        }
        if (getResponsiblePerson() != null) {
            _hashCode += getResponsiblePerson().hashCode();
        }
        if (getUserPerson() != null) {
            _hashCode += getUserPerson().hashCode();
        }
        _hashCode += (isHCPResponse() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getLastChangeDate() != null) {
            _hashCode += getLastChangeDate().hashCode();
        }
        if (getIPv6Ready() != null) {
            _hashCode += getIPv6Ready().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DeviceBasicInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "DeviceBasicInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deviceName");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "DeviceName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("location");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Location"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Location"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("zone");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Zone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manufacturer");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Manufacturer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("model");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Model"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("genericType");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "GenericType"));
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
        elemField.setFieldName("operatingSystem");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "OperatingSystem"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "OperatingSystem"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inventoryNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "InventoryNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startDate");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "StartDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Time"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endDate");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "EndDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Time"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responsiblePerson");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "ResponsiblePerson"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Person"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userPerson");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "UserPerson"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Person"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("HCPResponse");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "HCPResponse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastChangeDate");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "LastChangeDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Time"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IPv6Ready");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "IPv6Ready"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
