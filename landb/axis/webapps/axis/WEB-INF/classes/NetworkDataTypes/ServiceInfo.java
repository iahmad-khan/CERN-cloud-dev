/**
 * ServiceInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetworkDataTypes;

public class ServiceInfo  implements java.io.Serializable {
    private java.lang.String name;

    private java.lang.String primary;

    private java.lang.String addressIni;

    private java.lang.String addressEnd;

    private int addressCount;

    private java.lang.String subnetMask;

    private java.lang.String defaultGateway;

    private java.lang.String[] nameServers;

    private java.lang.String[] timeServers;

    private java.lang.String[] mediums;

    private java.lang.String networkDomain;

    private java.lang.String description;

    private int userIPTotal;

    private int userIPFree;

    private java.lang.String IPv6Network;

    private int IPv6NetMask;

    private java.lang.String IPv6DefaultGateway;

    private java.lang.String[] IPv6NameServers;

    private java.lang.String[] IPv6TimeServers;

    public ServiceInfo() {
    }

    public ServiceInfo(
        java.lang.String name,
        java.lang.String primary,
        java.lang.String addressIni,
        java.lang.String addressEnd,
        int addressCount,
        java.lang.String subnetMask,
        java.lang.String defaultGateway,
        java.lang.String[] nameServers,
        java.lang.String[] timeServers,
        java.lang.String[] mediums,
        java.lang.String networkDomain,
        java.lang.String description,
        int userIPTotal,
        int userIPFree,
        java.lang.String IPv6Network,
        int IPv6NetMask,
        java.lang.String IPv6DefaultGateway,
        java.lang.String[] IPv6NameServers,
        java.lang.String[] IPv6TimeServers) {
        this.name = name;
        this.primary = primary;
        this.addressIni = addressIni;
        this.addressEnd = addressEnd;
        this.addressCount = addressCount;
        this.subnetMask = subnetMask;
        this.defaultGateway = defaultGateway;
        this.nameServers = nameServers;
        this.timeServers = timeServers;
        this.mediums = mediums;
        this.networkDomain = networkDomain;
        this.description = description;
        this.userIPTotal = userIPTotal;
        this.userIPFree = userIPFree;
        this.IPv6Network = IPv6Network;
        this.IPv6NetMask = IPv6NetMask;
        this.IPv6DefaultGateway = IPv6DefaultGateway;
        this.IPv6NameServers = IPv6NameServers;
        this.IPv6TimeServers = IPv6TimeServers;
    }


    /**
     * Gets the name value for this ServiceInfo.
     *
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this ServiceInfo.
     *
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the primary value for this ServiceInfo.
     *
     * @return primary
     */
    public java.lang.String getPrimary() {
        return primary;
    }


    /**
     * Sets the primary value for this ServiceInfo.
     *
     * @param primary
     */
    public void setPrimary(java.lang.String primary) {
        this.primary = primary;
    }


    /**
     * Gets the addressIni value for this ServiceInfo.
     *
     * @return addressIni
     */
    public java.lang.String getAddressIni() {
        return addressIni;
    }


    /**
     * Sets the addressIni value for this ServiceInfo.
     *
     * @param addressIni
     */
    public void setAddressIni(java.lang.String addressIni) {
        this.addressIni = addressIni;
    }


    /**
     * Gets the addressEnd value for this ServiceInfo.
     *
     * @return addressEnd
     */
    public java.lang.String getAddressEnd() {
        return addressEnd;
    }


    /**
     * Sets the addressEnd value for this ServiceInfo.
     *
     * @param addressEnd
     */
    public void setAddressEnd(java.lang.String addressEnd) {
        this.addressEnd = addressEnd;
    }


    /**
     * Gets the addressCount value for this ServiceInfo.
     *
     * @return addressCount
     */
    public int getAddressCount() {
        return addressCount;
    }


    /**
     * Sets the addressCount value for this ServiceInfo.
     *
     * @param addressCount
     */
    public void setAddressCount(int addressCount) {
        this.addressCount = addressCount;
    }


    /**
     * Gets the subnetMask value for this ServiceInfo.
     *
     * @return subnetMask
     */
    public java.lang.String getSubnetMask() {
        return subnetMask;
    }


    /**
     * Sets the subnetMask value for this ServiceInfo.
     *
     * @param subnetMask
     */
    public void setSubnetMask(java.lang.String subnetMask) {
        this.subnetMask = subnetMask;
    }


    /**
     * Gets the defaultGateway value for this ServiceInfo.
     *
     * @return defaultGateway
     */
    public java.lang.String getDefaultGateway() {
        return defaultGateway;
    }


    /**
     * Sets the defaultGateway value for this ServiceInfo.
     *
     * @param defaultGateway
     */
    public void setDefaultGateway(java.lang.String defaultGateway) {
        this.defaultGateway = defaultGateway;
    }


    /**
     * Gets the nameServers value for this ServiceInfo.
     *
     * @return nameServers
     */
    public java.lang.String[] getNameServers() {
        return nameServers;
    }


    /**
     * Sets the nameServers value for this ServiceInfo.
     *
     * @param nameServers
     */
    public void setNameServers(java.lang.String[] nameServers) {
        this.nameServers = nameServers;
    }


    /**
     * Gets the timeServers value for this ServiceInfo.
     *
     * @return timeServers
     */
    public java.lang.String[] getTimeServers() {
        return timeServers;
    }


    /**
     * Sets the timeServers value for this ServiceInfo.
     *
     * @param timeServers
     */
    public void setTimeServers(java.lang.String[] timeServers) {
        this.timeServers = timeServers;
    }


    /**
     * Gets the mediums value for this ServiceInfo.
     *
     * @return mediums
     */
    public java.lang.String[] getMediums() {
        return mediums;
    }


    /**
     * Sets the mediums value for this ServiceInfo.
     *
     * @param mediums
     */
    public void setMediums(java.lang.String[] mediums) {
        this.mediums = mediums;
    }


    /**
     * Gets the networkDomain value for this ServiceInfo.
     *
     * @return networkDomain
     */
    public java.lang.String getNetworkDomain() {
        return networkDomain;
    }


    /**
     * Sets the networkDomain value for this ServiceInfo.
     *
     * @param networkDomain
     */
    public void setNetworkDomain(java.lang.String networkDomain) {
        this.networkDomain = networkDomain;
    }


    /**
     * Gets the description value for this ServiceInfo.
     *
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this ServiceInfo.
     *
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the userIPTotal value for this ServiceInfo.
     *
     * @return userIPTotal
     */
    public int getUserIPTotal() {
        return userIPTotal;
    }


    /**
     * Sets the userIPTotal value for this ServiceInfo.
     *
     * @param userIPTotal
     */
    public void setUserIPTotal(int userIPTotal) {
        this.userIPTotal = userIPTotal;
    }


    /**
     * Gets the userIPFree value for this ServiceInfo.
     *
     * @return userIPFree
     */
    public int getUserIPFree() {
        return userIPFree;
    }


    /**
     * Sets the userIPFree value for this ServiceInfo.
     *
     * @param userIPFree
     */
    public void setUserIPFree(int userIPFree) {
        this.userIPFree = userIPFree;
    }


    /**
     * Gets the IPv6Network value for this ServiceInfo.
     *
     * @return IPv6Network
     */
    public java.lang.String getIPv6Network() {
        return IPv6Network;
    }


    /**
     * Sets the IPv6Network value for this ServiceInfo.
     *
     * @param IPv6Network
     */
    public void setIPv6Network(java.lang.String IPv6Network) {
        this.IPv6Network = IPv6Network;
    }


    /**
     * Gets the IPv6NetMask value for this ServiceInfo.
     *
     * @return IPv6NetMask
     */
    public int getIPv6NetMask() {
        return IPv6NetMask;
    }


    /**
     * Sets the IPv6NetMask value for this ServiceInfo.
     *
     * @param IPv6NetMask
     */
    public void setIPv6NetMask(int IPv6NetMask) {
        this.IPv6NetMask = IPv6NetMask;
    }


    /**
     * Gets the IPv6DefaultGateway value for this ServiceInfo.
     *
     * @return IPv6DefaultGateway
     */
    public java.lang.String getIPv6DefaultGateway() {
        return IPv6DefaultGateway;
    }


    /**
     * Sets the IPv6DefaultGateway value for this ServiceInfo.
     *
     * @param IPv6DefaultGateway
     */
    public void setIPv6DefaultGateway(java.lang.String IPv6DefaultGateway) {
        this.IPv6DefaultGateway = IPv6DefaultGateway;
    }


    /**
     * Gets the IPv6NameServers value for this ServiceInfo.
     *
     * @return IPv6NameServers
     */
    public java.lang.String[] getIPv6NameServers() {
        return IPv6NameServers;
    }


    /**
     * Sets the IPv6NameServers value for this ServiceInfo.
     *
     * @param IPv6NameServers
     */
    public void setIPv6NameServers(java.lang.String[] IPv6NameServers) {
        this.IPv6NameServers = IPv6NameServers;
    }


    /**
     * Gets the IPv6TimeServers value for this ServiceInfo.
     *
     * @return IPv6TimeServers
     */
    public java.lang.String[] getIPv6TimeServers() {
        return IPv6TimeServers;
    }


    /**
     * Sets the IPv6TimeServers value for this ServiceInfo.
     *
     * @param IPv6TimeServers
     */
    public void setIPv6TimeServers(java.lang.String[] IPv6TimeServers) {
        this.IPv6TimeServers = IPv6TimeServers;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServiceInfo)) return false;
        ServiceInfo other = (ServiceInfo) obj;
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
                  ((this.primary==null && other.getPrimary()==null) ||
                   (this.primary!=null &&
                    this.primary.equals(other.getPrimary()))) &&
                  ((this.addressIni==null && other.getAddressIni()==null) ||
                   (this.addressIni!=null &&
                    this.addressIni.equals(other.getAddressIni()))) &&
                  ((this.addressEnd==null && other.getAddressEnd()==null) ||
                   (this.addressEnd!=null &&
                    this.addressEnd.equals(other.getAddressEnd()))) &&
                  this.addressCount == other.getAddressCount() &&
                  ((this.subnetMask==null && other.getSubnetMask()==null) ||
                   (this.subnetMask!=null &&
                    this.subnetMask.equals(other.getSubnetMask()))) &&
                  ((this.defaultGateway==null && other.getDefaultGateway()==null) ||
                   (this.defaultGateway!=null &&
                    this.defaultGateway.equals(other.getDefaultGateway()))) &&
                  ((this.nameServers==null && other.getNameServers()==null) ||
                   (this.nameServers!=null &&
                    java.util.Arrays.equals(this.nameServers, other.getNameServers()))) &&
                  ((this.timeServers==null && other.getTimeServers()==null) ||
                   (this.timeServers!=null &&
                    java.util.Arrays.equals(this.timeServers, other.getTimeServers()))) &&
                  ((this.mediums==null && other.getMediums()==null) ||
                   (this.mediums!=null &&
                    java.util.Arrays.equals(this.mediums, other.getMediums()))) &&
                  ((this.networkDomain==null && other.getNetworkDomain()==null) ||
                   (this.networkDomain!=null &&
                    this.networkDomain.equals(other.getNetworkDomain()))) &&
                  ((this.description==null && other.getDescription()==null) ||
                   (this.description!=null &&
                    this.description.equals(other.getDescription()))) &&
                  this.userIPTotal == other.getUserIPTotal() &&
                  this.userIPFree == other.getUserIPFree() &&
                  ((this.IPv6Network==null && other.getIPv6Network()==null) ||
                   (this.IPv6Network!=null &&
                    this.IPv6Network.equals(other.getIPv6Network()))) &&
                  this.IPv6NetMask == other.getIPv6NetMask() &&
                  ((this.IPv6DefaultGateway==null && other.getIPv6DefaultGateway()==null) ||
                   (this.IPv6DefaultGateway!=null &&
                    this.IPv6DefaultGateway.equals(other.getIPv6DefaultGateway()))) &&
                  ((this.IPv6NameServers==null && other.getIPv6NameServers()==null) ||
                   (this.IPv6NameServers!=null &&
                    java.util.Arrays.equals(this.IPv6NameServers, other.getIPv6NameServers()))) &&
                  ((this.IPv6TimeServers==null && other.getIPv6TimeServers()==null) ||
                   (this.IPv6TimeServers!=null &&
                    java.util.Arrays.equals(this.IPv6TimeServers, other.getIPv6TimeServers())));
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
        if (getPrimary() != null) {
            _hashCode += getPrimary().hashCode();
        }
        if (getAddressIni() != null) {
            _hashCode += getAddressIni().hashCode();
        }
        if (getAddressEnd() != null) {
            _hashCode += getAddressEnd().hashCode();
        }
        _hashCode += getAddressCount();
        if (getSubnetMask() != null) {
            _hashCode += getSubnetMask().hashCode();
        }
        if (getDefaultGateway() != null) {
            _hashCode += getDefaultGateway().hashCode();
        }
        if (getNameServers() != null) {
            for (int i=0;
                    i<java.lang.reflect.Array.getLength(getNameServers());
                    i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNameServers(), i);
                if (obj != null &&
                        !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTimeServers() != null) {
            for (int i=0;
                    i<java.lang.reflect.Array.getLength(getTimeServers());
                    i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTimeServers(), i);
                if (obj != null &&
                        !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMediums() != null) {
            for (int i=0;
                    i<java.lang.reflect.Array.getLength(getMediums());
                    i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMediums(), i);
                if (obj != null &&
                        !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNetworkDomain() != null) {
            _hashCode += getNetworkDomain().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        _hashCode += getUserIPTotal();
        _hashCode += getUserIPFree();
        if (getIPv6Network() != null) {
            _hashCode += getIPv6Network().hashCode();
        }
        _hashCode += getIPv6NetMask();
        if (getIPv6DefaultGateway() != null) {
            _hashCode += getIPv6DefaultGateway().hashCode();
        }
        if (getIPv6NameServers() != null) {
            for (int i=0;
                    i<java.lang.reflect.Array.getLength(getIPv6NameServers());
                    i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIPv6NameServers(), i);
                if (obj != null &&
                        !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIPv6TimeServers() != null) {
            for (int i=0;
                    i<java.lang.reflect.Array.getLength(getIPv6TimeServers());
                    i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIPv6TimeServers(), i);
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
        new org.apache.axis.description.TypeDesc(ServiceInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "ServiceInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primary");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Primary"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressIni");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "AddressIni"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressEnd");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "AddressEnd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressCount");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "AddressCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subnetMask");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "SubnetMask"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defaultGateway");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "DefaultGateway"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nameServers");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "NameServers"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeServers");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "TimeServers"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mediums");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Mediums"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("networkDomain");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "NetworkDomain"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userIPTotal");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "UserIPTotal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userIPFree");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "UserIPFree"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IPv6Network");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "IPv6Network"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IPv6NetMask");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "IPv6NetMask"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IPv6DefaultGateway");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "IPv6DefaultGateway"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IPv6NameServers");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "IPv6NameServers"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IPv6TimeServers");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "IPv6TimeServers"));
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
