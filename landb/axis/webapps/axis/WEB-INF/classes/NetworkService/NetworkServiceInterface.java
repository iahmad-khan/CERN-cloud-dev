/**
 * NetworkServiceInterface.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetworkService;

public interface NetworkServiceInterface extends java.rmi.Remote {
    public java.lang.String getAuthToken(java.lang.String login, java.lang.String password, java.lang.String type) throws java.rmi.RemoteException;
    public java.lang.String[] searchDevice(NetworkDataTypes.Auth auth, NetworkDataTypes.DeviceSearch deviceSearch) throws java.rmi.RemoteException;
    public NetworkDataTypes.DeviceBasicInfo getDeviceBasicInfo(java.lang.String deviceName) throws java.rmi.RemoteException;
    public NetworkDataTypes.DeviceInfo getDeviceInfo(NetworkDataTypes.Auth auth, java.lang.String deviceName) throws java.rmi.RemoteException;
    public NetworkDataTypes.DeviceInfo[] getDeviceInfoArray(NetworkDataTypes.Auth auth, java.lang.String[] deviceNameList) throws java.rmi.RemoteException;
    public NetworkDataTypes.DeviceInfo getDeviceInfoFromNameMAC(java.lang.String deviceName, java.lang.String MAC) throws java.rmi.RemoteException;
    public NetworkDataTypes.DeviceInfo getMyDeviceInfo() throws java.rmi.RemoteException;
    public java.lang.String[] getLastChangedDevices(NetworkDataTypes.Auth auth, int minutes) throws java.rmi.RemoteException;
    public boolean bulkInsert(NetworkDataTypes.Auth auth, NetworkDataTypes.DeviceInput device, NetworkDataTypes.InterfaceCard[] cards, NetworkDataTypes.BulkInterface[] interfaces) throws java.rmi.RemoteException;
    public boolean bulkInsertAuto(NetworkDataTypes.DeviceInput device, NetworkDataTypes.InterfaceCard[] cards, NetworkDataTypes.BulkInterfaceAuto[] interfaces) throws java.rmi.RemoteException;
    public boolean bulkRemove(NetworkDataTypes.Auth auth, java.lang.String deviceName) throws java.rmi.RemoteException;
    public boolean deviceInsert(NetworkDataTypes.Auth auth, NetworkDataTypes.DeviceInput device) throws java.rmi.RemoteException;
    public boolean deviceAddCard(NetworkDataTypes.Auth auth, java.lang.String deviceName, NetworkDataTypes.InterfaceCard interfaceCard) throws java.rmi.RemoteException;
    public boolean deviceAddBulkInterface(NetworkDataTypes.Auth auth, java.lang.String deviceName, NetworkDataTypes.BulkInterface bulkInterface) throws java.rmi.RemoteException;
    public boolean deviceRemove(NetworkDataTypes.Auth auth, java.lang.String deviceName) throws java.rmi.RemoteException;
    public boolean deviceRemoveCard(NetworkDataTypes.Auth auth, java.lang.String deviceName, java.lang.String hardwareAddress) throws java.rmi.RemoteException;
    public boolean deviceRemoveBulkInterface(NetworkDataTypes.Auth auth, java.lang.String deviceName, java.lang.String interfaceName) throws java.rmi.RemoteException;
    public boolean deviceMoveBulkInterface(NetworkDataTypes.Auth auth, java.lang.String deviceName, java.lang.String interfaceName, NetworkDataTypes.BulkInterface bulkInterface, NetworkDataTypes.BulkMoveOptions bulkMoveOptions) throws java.rmi.RemoteException;
    public boolean deviceUpdate(NetworkDataTypes.Auth auth, java.lang.String deviceName, NetworkDataTypes.DeviceInput deviceInput) throws java.rmi.RemoteException;
    public boolean deviceGlobalRename(NetworkDataTypes.Auth auth, java.lang.String deviceName, java.lang.String newDeviceName) throws java.rmi.RemoteException;
    public boolean setHCPResponse(NetworkDataTypes.Auth auth, java.lang.String[] deviceList, boolean HCPFlag) throws java.rmi.RemoteException;
    public boolean deviceUpdateIPv6Ready(NetworkDataTypes.Auth auth, java.lang.String deviceName, boolean IPv6Ready) throws java.rmi.RemoteException;
    public boolean deviceSetBOOTPInfo(NetworkDataTypes.Auth auth, java.lang.String device, java.lang.String server, java.lang.String imagePath) throws java.rmi.RemoteException;
    public boolean deviceRemoveBOOTPInfo(NetworkDataTypes.Auth auth, java.lang.String device) throws java.rmi.RemoteException;
    public NetworkDataTypes.BOOTPInfo getBOOTPInfo(NetworkDataTypes.Auth auth, java.lang.String device) throws java.rmi.RemoteException;
    public NetworkDataTypes.BulkInterface getBulkInterfaceInfo(NetworkDataTypes.Auth auth, java.lang.String interfaceName) throws java.rmi.RemoteException;
    public boolean setInsertAddress(NetworkDataTypes.Auth auth, java.lang.String set, java.lang.String address) throws java.rmi.RemoteException;
    public boolean setInsertService(NetworkDataTypes.Auth auth, java.lang.String set, java.lang.String service) throws java.rmi.RemoteException;
    public boolean setDeleteAddress(NetworkDataTypes.Auth auth, java.lang.String set, java.lang.String address) throws java.rmi.RemoteException;
    public boolean setDeleteService(NetworkDataTypes.Auth auth, java.lang.String set, java.lang.String service) throws java.rmi.RemoteException;
    public NetworkDataTypes.SetInfo getSetInfo(NetworkDataTypes.Auth auth, java.lang.String setName) throws java.rmi.RemoteException;
    public java.lang.String getSetNameFromID(NetworkDataTypes.Auth auth, long setID) throws java.rmi.RemoteException;
    public java.lang.String[] getSetAllInterfaces(NetworkDataTypes.Auth auth, java.lang.String setName) throws java.rmi.RemoteException;
    public java.lang.String[] getSetInterfacesTrusting(NetworkDataTypes.Auth auth, java.lang.String setName) throws java.rmi.RemoteException;
    public NetworkDataTypes.InetInfo[] getHCPInfoArray(NetworkDataTypes.Auth auth, java.lang.String[] hosts) throws java.rmi.RemoteException;
    public java.lang.String[] getDevicesFromService(NetworkDataTypes.Auth auth, java.lang.String service) throws java.rmi.RemoteException;
    public java.lang.String[] getSwitchesFromService(NetworkDataTypes.Auth auth, java.lang.String service) throws java.rmi.RemoteException;
    public NetworkDataTypes.SwitchPort[] getSwitchInfo(NetworkDataTypes.Auth auth, java.lang.String switchName) throws java.rmi.RemoteException;
    public NetworkDataTypes.Connection[] getConnectionsFromDevice(NetworkDataTypes.Auth auth, java.lang.String deviceName) throws java.rmi.RemoteException;
    public NetworkDataTypes.OutletLocation getOutletLocationFromSwitchPort(NetworkDataTypes.Auth auth, java.lang.String switchName, java.lang.String portName) throws java.rmi.RemoteException;
    public NetworkDataTypes.ObservedSwitchConnection[] getCurrentConnection(java.lang.String ip, java.lang.String[] hardwareAddressList) throws java.rmi.RemoteException;
    public NetworkDataTypes.ObservedSwitchConnection[] getMyCurrentConnection(java.lang.String[] hardwareAddressList) throws java.rmi.RemoteException;
    public boolean enableFanOutFromSwitchPort(NetworkDataTypes.Auth auth, java.lang.String switchName, java.lang.String portName) throws java.rmi.RemoteException;
    public boolean setUnsetBindedInterface(NetworkDataTypes.Auth auth, java.lang.String interfaceName, java.lang.String hardwareAddress) throws java.rmi.RemoteException;
    public boolean interfaceAddAlias(NetworkDataTypes.Auth auth, java.lang.String interfaceName, java.lang.String alias) throws java.rmi.RemoteException;
    public boolean interfaceRemoveAlias(NetworkDataTypes.Auth auth, java.lang.String interfaceName, java.lang.String alias) throws java.rmi.RemoteException;
    public boolean interfaceMoveAlias(NetworkDataTypes.Auth auth, java.lang.String interfaceName, java.lang.String alias, java.lang.String newInterfaceName) throws java.rmi.RemoteException;
    public boolean interfaceRename(NetworkDataTypes.Auth auth, java.lang.String interfaceName, java.lang.String newInterfaceName) throws java.rmi.RemoteException;
    public boolean interfaceMove(NetworkDataTypes.Auth auth, java.lang.String interfaceName, java.lang.String newDeviceName) throws java.rmi.RemoteException;
    public java.lang.String[] searchSet(NetworkDataTypes.Auth auth, java.lang.String setPattern) throws java.rmi.RemoteException;
    public boolean setInsert(NetworkDataTypes.Auth auth, NetworkDataTypes.SetInput set) throws java.rmi.RemoteException;
    public boolean setRemove(NetworkDataTypes.Auth auth, java.lang.String setName) throws java.rmi.RemoteException;
    public boolean setSwitchPortTypeStatus(NetworkDataTypes.Auth auth, java.lang.String switchName, java.lang.String portName, NetworkDataTypes.SwitchPortTypeStatus switchPortTypeStatus) throws java.rmi.RemoteException;
    public boolean setSwitchPortService(NetworkDataTypes.Auth auth, java.lang.String switchName, java.lang.String portName, java.lang.String service) throws java.rmi.RemoteException;
    public NetworkDataTypes.SwitchPortTypeStatus getSwitchPortTypeStatus(NetworkDataTypes.Auth auth, java.lang.String switchName, java.lang.String portName) throws java.rmi.RemoteException;
    public NetworkDataTypes.NetNameTuple[] searchNetNameTable(NetworkDataTypes.Auth auth, java.lang.String netName) throws java.rmi.RemoteException;
    public boolean deviceAddSecondaryInterface(NetworkDataTypes.Auth auth, java.lang.String deviceName, java.lang.String baseInterfaceName, NetworkDataTypes.LogicalInterfaceInput logicalInterface) throws java.rmi.RemoteException;
    public boolean deviceAddIPMIInterface(NetworkDataTypes.Auth auth, java.lang.String deviceName, java.lang.String baseInterfaceName, NetworkDataTypes.InterfaceCard interfaceCard, NetworkDataTypes.IPMIOptions IPMIOptions) throws java.rmi.RemoteException;
    public boolean deviceAddLogicalInterface(NetworkDataTypes.Auth auth, java.lang.String deviceName, NetworkDataTypes.LogicalInterfaceInput logicalInterface) throws java.rmi.RemoteException;
    public boolean deviceRemoveLogicalInterface(NetworkDataTypes.Auth auth, java.lang.String deviceName, java.lang.String interfaceName) throws java.rmi.RemoteException;
    public boolean interfaceUpdateDescription(NetworkDataTypes.Auth auth, java.lang.String interfaceName, java.lang.String description) throws java.rmi.RemoteException;
    public boolean serviceUpdateDescription(NetworkDataTypes.Auth auth, java.lang.String serviceName, java.lang.String description) throws java.rmi.RemoteException;
    public boolean deviceSyncIPMIInterface(NetworkDataTypes.Auth auth, java.lang.String deviceName, java.lang.String interfaceName, java.lang.String IPMIInterfaceName, NetworkDataTypes.IPMIOptions IPMIOptions) throws java.rmi.RemoteException;
    public NetworkDataTypes.ServiceInfo getServiceInfo(NetworkDataTypes.Auth auth, java.lang.String serviceName) throws java.rmi.RemoteException;
    public boolean vmCreate(NetworkDataTypes.Auth auth, NetworkDataTypes.DeviceInput VMDevice, NetworkDataTypes.InterfaceCard interfaceCard, java.lang.String VMClusterName, java.lang.String VMParent, NetworkDataTypes.VMOptions VMOptions) throws java.rmi.RemoteException;
    public boolean vmMigrate(NetworkDataTypes.Auth auth, java.lang.String VMName, java.lang.String newParent) throws java.rmi.RemoteException;
    public boolean vmMove(NetworkDataTypes.Auth auth, java.lang.String VMName, java.lang.String VMClusterName, java.lang.String VMParent, NetworkDataTypes.VMOptions VMOptions) throws java.rmi.RemoteException;
    public boolean vmUpdate(NetworkDataTypes.Auth auth, java.lang.String deviceName, NetworkDataTypes.DeviceInput deviceInput) throws java.rmi.RemoteException;
    public boolean vmDestroy(NetworkDataTypes.Auth auth, java.lang.String VMName) throws java.rmi.RemoteException;
    public NetworkDataTypes.VMClusterInfo vmClusterGetInfo(NetworkDataTypes.Auth auth, java.lang.String VMClusterName) throws java.rmi.RemoteException;
    public java.lang.String[] vmClusterGetDevices(NetworkDataTypes.Auth auth, java.lang.String VMClusterName) throws java.rmi.RemoteException;
    public NetworkDataTypes.VMInfo vmGetInfo(NetworkDataTypes.Auth auth, java.lang.String VMName) throws java.rmi.RemoteException;
    public java.lang.String[] vmGetClusterMembership(NetworkDataTypes.Auth auth, java.lang.String deviceName) throws java.rmi.RemoteException;
    public java.lang.String[] vmSearchCluster(NetworkDataTypes.Auth auth, NetworkDataTypes.VMClusterSearch VMClusterSearch) throws java.rmi.RemoteException;
    public boolean vmSetUnsetManagedFlag(NetworkDataTypes.Auth auth, java.lang.String deviceName, boolean flag) throws java.rmi.RemoteException;
    public boolean vmNetReset(NetworkDataTypes.Auth auth, java.lang.String VMName) throws java.rmi.RemoteException;
    public boolean dnsZoneUpdate(NetworkDataTypes.Auth auth, java.lang.String zone, NetworkDataTypes.DnsZoneOptions dnsZoneOptions) throws java.rmi.RemoteException;
    public boolean svcPrincipalAdd(NetworkDataTypes.Auth auth, java.lang.String deviceName, java.lang.String serviceName, NetworkDataTypes.PersonInput person) throws java.rmi.RemoteException;
    public boolean svcPrincipalRemove(NetworkDataTypes.Auth auth, java.lang.String deviceName, java.lang.String serviceName) throws java.rmi.RemoteException;
    public NetworkDataTypes.DNSDelegatedEntry[] dnsDelegatedSearch(NetworkDataTypes.Auth auth, java.lang.String search) throws java.rmi.RemoteException;
    public NetworkDataTypes.DNSDelegatedEntry dnsDelegatedGetByNameView(NetworkDataTypes.Auth auth, java.lang.String domain, java.lang.String view) throws java.rmi.RemoteException;
    public boolean dnsDelegatedAdd(NetworkDataTypes.Auth auth, NetworkDataTypes.DNSDelegatedInput DNSDelegatedInput) throws java.rmi.RemoteException;
    public NetworkDataTypes.DNSDelegatedKey[] dnsDelegatedListKeys(NetworkDataTypes.Auth auth) throws java.rmi.RemoteException;
    public boolean dnsDelegatedRemove(NetworkDataTypes.Auth auth, java.lang.String domain, java.lang.String view) throws java.rmi.RemoteException;
    public boolean dnsDelegatedAliasAdd(NetworkDataTypes.Auth auth, java.lang.String domain, java.lang.String view, java.lang.String alias) throws java.rmi.RemoteException;
    public boolean dnsDelegatedAliasRemove(NetworkDataTypes.Auth auth, java.lang.String domain, java.lang.String view, java.lang.String alias) throws java.rmi.RemoteException;
}
