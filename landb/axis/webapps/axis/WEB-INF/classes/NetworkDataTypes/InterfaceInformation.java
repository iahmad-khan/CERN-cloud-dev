/**
 * InterfaceInformation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetworkDataTypes;

public class InterfaceInformation  implements java.io.Serializable {
    private java.lang.Boolean connectedToSC;

    private java.lang.String name;

    private java.lang.String domain;

    private java.lang.String IPAddress;

    private java.lang.String serviceName;

    private java.lang.String securityClass;

    private boolean internetConnectivity;

    private java.lang.String subnetMask;

    private java.lang.String defaultGateway;

    private java.lang.String[] nameServers;

    private java.lang.String[] belongsToSets;

    private java.lang.String[] timeServers;

    private java.lang.String IPv6Address;

    private int IPv6NetMask;

    private java.lang.String[] IPv6NameServers;

    private java.lang.String[] IPv6TimeServers;

    private java.lang.String IPv6DefaultGateway;

    private java.lang.String[] IPAliases;

    private NetworkDataTypes.InterfaceCard boundInterfaceCard;

    private NetworkDataTypes.Outlet outlet;

    private java.lang.String rackName;

    private java.lang.String description;

    private java.lang.String networkDomainName;

    private java.lang.String medium;

    private NetworkDataTypes.ReverseTelnet reverseTelnet;

    public InterfaceInformation() {
    }

    public InterfaceInformation(
        java.lang.Boolean connectedToSC,
        java.lang.String name,
        java.lang.String domain,
        java.lang.String IPAddress,
        java.lang.String serviceName,
        java.lang.String securityClass,
        boolean internetConnectivity,
        java.lang.String subnetMask,
        java.lang.String defaultGateway,
        java.lang.String[] nameServers,
        java.lang.String[] belongsToSets,
        java.lang.String[] timeServers,
        java.lang.String IPv6Address,
        int IPv6NetMask,
        java.lang.String[] IPv6NameServers,
        java.lang.String[] IPv6TimeServers,
        java.lang.String IPv6DefaultGateway,
        java.lang.String[] IPAliases,
        NetworkDataTypes.InterfaceCard boundInterfaceCard,
        NetworkDataTypes.Outlet outlet,
        java.lang.String rackName,
        java.lang.String description,
        java.lang.String networkDomainName,
        java.lang.String medium,
        NetworkDataTypes.ReverseTelnet reverseTelnet) {
        this.connectedToSC = connectedToSC;
        this.name = name;
        this.domain = domain;
        this.IPAddress = IPAddress;
        this.serviceName = serviceName;
        this.securityClass = securityClass;
        this.internetConnectivity = internetConnectivity;
        this.subnetMask = subnetMask;
        this.defaultGateway = defaultGateway;
        this.nameServers = nameServers;
        this.belongsToSets = belongsToSets;
        this.timeServers = timeServers;
        this.IPv6Address = IPv6Address;
        this.IPv6NetMask = IPv6NetMask;
        this.IPv6NameServers = IPv6NameServers;
        this.IPv6TimeServers = IPv6TimeServers;
        this.IPv6DefaultGateway = IPv6DefaultGateway;
        this.IPAliases = IPAliases;
        this.boundInterfaceCard = boundInterfaceCard;
        this.outlet = outlet;
        this.rackName = rackName;
        this.description = description;
        this.networkDomainName = networkDomainName;
        this.medium = medium;
        this.reverseTelnet = reverseTelnet;
    }


    /**
     * Gets the connectedToSC value for this InterfaceInformation.
     *
     * @return connectedToSC
     */
    public java.lang.Boolean getConnectedToSC() {
        return connectedToSC;
    }


    /**
     * Sets the connectedToSC value for this InterfaceInformation.
     *
     * @param connectedToSC
     */
    public void setConnectedToSC(java.lang.Boolean connectedToSC) {
        this.connectedToSC = connectedToSC;
    }


    /**
     * Gets the name value for this InterfaceInformation.
     *
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this InterfaceInformation.
     *
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the domain value for this InterfaceInformation.
     *
     * @return domain
     */
    public java.lang.String getDomain() {
        return domain;
    }


    /**
     * Sets the domain value for this InterfaceInformation.
     *
     * @param domain
     */
    public void setDomain(java.lang.String domain) {
        this.domain = domain;
    }


    /**
     * Gets the IPAddress value for this InterfaceInformation.
     *
     * @return IPAddress
     */
    public java.lang.String getIPAddress() {
        return IPAddress;
    }


    /**
     * Sets the IPAddress value for this InterfaceInformation.
     *
     * @param IPAddress
     */
    public void setIPAddress(java.lang.String IPAddress) {
        this.IPAddress = IPAddress;
    }


    /**
     * Gets the serviceName value for this InterfaceInformation.
     *
     * @return serviceName
     */
    public java.lang.String getServiceName() {
        return serviceName;
    }


    /**
     * Sets the serviceName value for this InterfaceInformation.
     *
     * @param serviceName
     */
    public void setServiceName(java.lang.String serviceName) {
        this.serviceName = serviceName;
    }


    /**
     * Gets the securityClass value for this InterfaceInformation.
     *
     * @return securityClass
     */
    public java.lang.String getSecurityClass() {
        return securityClass;
    }


    /**
     * Sets the securityClass value for this InterfaceInformation.
     *
     * @param securityClass
     */
    public void setSecurityClass(java.lang.String securityClass) {
        this.securityClass = securityClass;
    }


    /**
     * Gets the internetConnectivity value for this InterfaceInformation.
     *
     * @return internetConnectivity
     */
    public boolean isInternetConnectivity() {
        return internetConnectivity;
    }


    /**
     * Sets the internetConnectivity value for this InterfaceInformation.
     *
     * @param internetConnectivity
     */
    public void setInternetConnectivity(boolean internetConnectivity) {
        this.internetConnectivity = internetConnectivity;
    }


    /**
     * Gets the subnetMask value for this InterfaceInformation.
     *
     * @return subnetMask
     */
    public java.lang.String getSubnetMask() {
        return subnetMask;
    }


    /**
     * Sets the subnetMask value for this InterfaceInformation.
     *
     * @param subnetMask
     */
    public void setSubnetMask(java.lang.String subnetMask) {
        this.subnetMask = subnetMask;
    }


    /**
     * Gets the defaultGateway value for this InterfaceInformation.
     *
     * @return defaultGateway
     */
    public java.lang.String getDefaultGateway() {
        return defaultGateway;
    }


    /**
     * Sets the defaultGateway value for this InterfaceInformation.
     *
     * @param defaultGateway
     */
    public void setDefaultGateway(java.lang.String defaultGateway) {
        this.defaultGateway = defaultGateway;
    }


    /**
     * Gets the nameServers value for this InterfaceInformation.
     *
     * @return nameServers
     */
    public java.lang.String[] getNameServers() {
        return nameServers;
    }


    /**
     * Sets the nameServers value for this InterfaceInformation.
     *
     * @param nameServers
     */
    public void setNameServers(java.lang.String[] nameServers) {
        this.nameServers = nameServers;
    }


    /**
     * Gets the belongsToSets value for this InterfaceInformation.
     *
     * @return belongsToSets
     */
    public java.lang.String[] getBelongsToSets() {
        return belongsToSets;
    }


    /**
     * Sets the belongsToSets value for this InterfaceInformation.
     *
     * @param belongsToSets
     */
    public void setBelongsToSets(java.lang.String[] belongsToSets) {
        this.belongsToSets = belongsToSets;
    }


    /**
     * Gets the timeServers value for this InterfaceInformation.
     *
     * @return timeServers
     */
    public java.lang.String[] getTimeServers() {
        return timeServers;
    }


    /**
     * Sets the timeServers value for this InterfaceInformation.
     *
     * @param timeServers
     */
    public void setTimeServers(java.lang.String[] timeServers) {
        this.timeServers = timeServers;
    }


    /**
     * Gets the IPv6Address value for this InterfaceInformation.
     *
     * @return IPv6Address
     */
    public java.lang.String getIPv6Address() {
        return IPv6Address;
    }


    /**
     * Sets the IPv6Address value for this InterfaceInformation.
     *
     * @param IPv6Address
     */
    public void setIPv6Address(java.lang.String IPv6Address) {
        this.IPv6Address = IPv6Address;
    }


    /**
     * Gets the IPv6NetMask value for this InterfaceInformation.
     *
     * @return IPv6NetMask
     */
    public int getIPv6NetMask() {
        return IPv6NetMask;
    }


    /**
     * Sets the IPv6NetMask value for this InterfaceInformation.
     *
     * @param IPv6NetMask
     */
    public void setIPv6NetMask(int IPv6NetMask) {
        this.IPv6NetMask = IPv6NetMask;
    }


    /**
     * Gets the IPv6NameServers value for this InterfaceInformation.
     *
     * @return IPv6NameServers
     */
    public java.lang.String[] getIPv6NameServers() {
        return IPv6NameServers;
    }


    /**
     * Sets the IPv6NameServers value for this InterfaceInformation.
     *
     * @param IPv6NameServers
     */
    public void setIPv6NameServers(java.lang.String[] IPv6NameServers) {
        this.IPv6NameServers = IPv6NameServers;
    }


    /**
     * Gets the IPv6TimeServers value for this InterfaceInformation.
     *
     * @return IPv6TimeServers
     */
    public java.lang.String[] getIPv6TimeServers() {
        return IPv6TimeServers;
    }


    /**
     * Sets the IPv6TimeServers value for this InterfaceInformation.
     *
     * @param IPv6TimeServers
     */
    public void setIPv6TimeServers(java.lang.String[] IPv6TimeServers) {
        this.IPv6TimeServers = IPv6TimeServers;
    }


    /**
     * Gets the IPv6DefaultGateway value for this InterfaceInformation.
     *
     * @return IPv6DefaultGateway
     */
    public java.lang.String getIPv6DefaultGateway() {
        return IPv6DefaultGateway;
    }


    /**
     * Sets the IPv6DefaultGateway value for this InterfaceInformation.
     *
     * @param IPv6DefaultGateway
     */
    public void setIPv6DefaultGateway(java.lang.String IPv6DefaultGateway) {
        this.IPv6DefaultGateway = IPv6DefaultGateway;
    }


    /**
     * Gets the IPAliases value for this InterfaceInformation.
     *
     * @return IPAliases
     */
    public java.lang.String[] getIPAliases() {
        return IPAliases;
    }


    /**
     * Sets the IPAliases value for this InterfaceInformation.
     *
     * @param IPAliases
     */
    public void setIPAliases(java.lang.String[] IPAliases) {
        this.IPAliases = IPAliases;
    }


    /**
     * Gets the boundInterfaceCard value for this InterfaceInformation.
     *
     * @return boundInterfaceCard
     */
    public NetworkDataTypes.InterfaceCard getBoundInterfaceCard() {
        return boundInterfaceCard;
    }


    /**
     * Sets the boundInterfaceCard value for this InterfaceInformation.
     *
     * @param boundInterfaceCard
     */
    public void setBoundInterfaceCard(NetworkDataTypes.InterfaceCard boundInterfaceCard) {
        this.boundInterfaceCard = boundInterfaceCard;
    }


    /**
     * Gets the outlet value for this InterfaceInformation.
     *
     * @return outlet
     */
    public NetworkDataTypes.Outlet getOutlet() {
        return outlet;
    }


    /**
     * Sets the outlet value for this InterfaceInformation.
     *
     * @param outlet
     */
    public void setOutlet(NetworkDataTypes.Outlet outlet) {
        this.outlet = outlet;
    }


    /**
     * Gets the rackName value for this InterfaceInformation.
     *
     * @return rackName
     */
    public java.lang.String getRackName() {
        return rackName;
    }


    /**
     * Sets the rackName value for this InterfaceInformation.
     *
     * @param rackName
     */
    public void setRackName(java.lang.String rackName) {
        this.rackName = rackName;
    }


    /**
     * Gets the description value for this InterfaceInformation.
     *
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this InterfaceInformation.
     *
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the networkDomainName value for this InterfaceInformation.
     *
     * @return networkDomainName
     */
    public java.lang.String getNetworkDomainName() {
        return networkDomainName;
    }


    /**
     * Sets the networkDomainName value for this InterfaceInformation.
     *
     * @param networkDomainName
     */
    public void setNetworkDomainName(java.lang.String networkDomainName) {
        this.networkDomainName = networkDomainName;
    }


    /**
     * Gets the medium value for this InterfaceInformation.
     *
     * @return medium
     */
    public java.lang.String getMedium() {
        return medium;
    }


    /**
     * Sets the medium value for this InterfaceInformation.
     *
     * @param medium
     */
    public void setMedium(java.lang.String medium) {
        this.medium = medium;
    }


    /**
     * Gets the reverseTelnet value for this InterfaceInformation.
     *
     * @return reverseTelnet
     */
    public NetworkDataTypes.ReverseTelnet getReverseTelnet() {
        return reverseTelnet;
    }


    /**
     * Sets the reverseTelnet value for this InterfaceInformation.
     *
     * @param reverseTelnet
     */
    public void setReverseTelnet(NetworkDataTypes.ReverseTelnet reverseTelnet) {
        this.reverseTelnet = reverseTelnet;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InterfaceInformation)) return false;
        InterfaceInformation other = (InterfaceInformation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
                  ((this.connectedToSC==null && other.getConnectedToSC()==null) ||
                   (this.connectedToSC!=null &&
                    this.connectedToSC.equals(other.getConnectedToSC()))) &&
                  ((this.name==null && other.getName()==null) ||
                   (this.name!=null &&
                    this.name.equals(other.getName()))) &&
                  ((this.domain==null && other.getDomain()==null) ||
                   (this.domain!=null &&
                    this.domain.equals(other.getDomain()))) &&
                  ((this.IPAddress==null && other.getIPAddress()==null) ||
                   (this.IPAddress!=null &&
                    this.IPAddress.equals(other.getIPAddress()))) &&
                  ((this.serviceName==null && other.getServiceName()==null) ||
                   (this.serviceName!=null &&
                    this.serviceName.equals(other.getServiceName()))) &&
                  ((this.securityClass==null && other.getSecurityClass()==null) ||
                   (this.securityClass!=null &&
                    this.securityClass.equals(other.getSecurityClass()))) &&
                  this.internetConnectivity == other.isInternetConnectivity() &&
                  ((this.subnetMask==null && other.getSubnetMask()==null) ||
                   (this.subnetMask!=null &&
                    this.subnetMask.equals(other.getSubnetMask()))) &&
                  ((this.defaultGateway==null && other.getDefaultGateway()==null) ||
                   (this.defaultGateway!=null &&
                    this.defaultGateway.equals(other.getDefaultGateway()))) &&
                  ((this.nameServers==null && other.getNameServers()==null) ||
                   (this.nameServers!=null &&
                    java.util.Arrays.equals(this.nameServers, other.getNameServers()))) &&
                  ((this.belongsToSets==null && other.getBelongsToSets()==null) ||
                   (this.belongsToSets!=null &&
                    java.util.Arrays.equals(this.belongsToSets, other.getBelongsToSets()))) &&
                  ((this.timeServers==null && other.getTimeServers()==null) ||
                   (this.timeServers!=null &&
                    java.util.Arrays.equals(this.timeServers, other.getTimeServers()))) &&
                  ((this.IPv6Address==null && other.getIPv6Address()==null) ||
                   (this.IPv6Address!=null &&
                    this.IPv6Address.equals(other.getIPv6Address()))) &&
                  this.IPv6NetMask == other.getIPv6NetMask() &&
                  ((this.IPv6NameServers==null && other.getIPv6NameServers()==null) ||
                   (this.IPv6NameServers!=null &&
                    java.util.Arrays.equals(this.IPv6NameServers, other.getIPv6NameServers()))) &&
                  ((this.IPv6TimeServers==null && other.getIPv6TimeServers()==null) ||
                   (this.IPv6TimeServers!=null &&
                    java.util.Arrays.equals(this.IPv6TimeServers, other.getIPv6TimeServers()))) &&
                  ((this.IPv6DefaultGateway==null && other.getIPv6DefaultGateway()==null) ||
                   (this.IPv6DefaultGateway!=null &&
                    this.IPv6DefaultGateway.equals(other.getIPv6DefaultGateway()))) &&
                  ((this.IPAliases==null && other.getIPAliases()==null) ||
                   (this.IPAliases!=null &&
                    java.util.Arrays.equals(this.IPAliases, other.getIPAliases()))) &&
                  ((this.boundInterfaceCard==null && other.getBoundInterfaceCard()==null) ||
                   (this.boundInterfaceCard!=null &&
                    this.boundInterfaceCard.equals(other.getBoundInterfaceCard()))) &&
                  ((this.outlet==null && other.getOutlet()==null) ||
                   (this.outlet!=null &&
                    this.outlet.equals(other.getOutlet()))) &&
                  ((this.rackName==null && other.getRackName()==null) ||
                   (this.rackName!=null &&
                    this.rackName.equals(other.getRackName()))) &&
                  ((this.description==null && other.getDescription()==null) ||
                   (this.description!=null &&
                    this.description.equals(other.getDescription()))) &&
                  ((this.networkDomainName==null && other.getNetworkDomainName()==null) ||
                   (this.networkDomainName!=null &&
                    this.networkDomainName.equals(other.getNetworkDomainName()))) &&
                  ((this.medium==null && other.getMedium()==null) ||
                   (this.medium!=null &&
                    this.medium.equals(other.getMedium()))) &&
                  ((this.reverseTelnet==null && other.getReverseTelnet()==null) ||
                   (this.reverseTelnet!=null &&
                    this.reverseTelnet.equals(other.getReverseTelnet())));
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
        if (getConnectedToSC() != null) {
            _hashCode += getConnectedToSC().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getDomain() != null) {
            _hashCode += getDomain().hashCode();
        }
        if (getIPAddress() != null) {
            _hashCode += getIPAddress().hashCode();
        }
        if (getServiceName() != null) {
            _hashCode += getServiceName().hashCode();
        }
        if (getSecurityClass() != null) {
            _hashCode += getSecurityClass().hashCode();
        }
        _hashCode += (isInternetConnectivity() ? Boolean.TRUE : Boolean.FALSE).hashCode();
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
        if (getBelongsToSets() != null) {
            for (int i=0;
                    i<java.lang.reflect.Array.getLength(getBelongsToSets());
                    i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getBelongsToSets(), i);
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
        if (getIPv6Address() != null) {
            _hashCode += getIPv6Address().hashCode();
        }
        _hashCode += getIPv6NetMask();
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
        if (getIPv6DefaultGateway() != null) {
            _hashCode += getIPv6DefaultGateway().hashCode();
        }
        if (getIPAliases() != null) {
            for (int i=0;
                    i<java.lang.reflect.Array.getLength(getIPAliases());
                    i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIPAliases(), i);
                if (obj != null &&
                        !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getBoundInterfaceCard() != null) {
            _hashCode += getBoundInterfaceCard().hashCode();
        }
        if (getOutlet() != null) {
            _hashCode += getOutlet().hashCode();
        }
        if (getRackName() != null) {
            _hashCode += getRackName().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getNetworkDomainName() != null) {
            _hashCode += getNetworkDomainName().hashCode();
        }
        if (getMedium() != null) {
            _hashCode += getMedium().hashCode();
        }
        if (getReverseTelnet() != null) {
            _hashCode += getReverseTelnet().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InterfaceInformation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "InterfaceInformation"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("connectedToSC");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "ConnectedToSC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("domain");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Domain"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IPAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "IPAddress"));
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
        elemField.setFieldName("securityClass");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "SecurityClass"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("internetConnectivity");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "InternetConnectivity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subnetMask");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "SubnetMask"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
        elemField.setFieldName("belongsToSets");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "BelongsToSets"));
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
        elemField.setFieldName("IPv6Address");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "IPv6Address"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IPv6DefaultGateway");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "IPv6DefaultGateway"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IPAliases");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "IPAliases"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("boundInterfaceCard");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "BoundInterfaceCard"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "InterfaceCard"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("outlet");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Outlet"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "Outlet"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rackName");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "RackName"));
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
        elemField.setFieldName("networkDomainName");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "NetworkDomainName"));
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
        elemField.setFieldName("reverseTelnet");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:NetworkDataTypes", "ReverseTelnet"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:NetworkDataTypes", "ReverseTelnet"));
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
