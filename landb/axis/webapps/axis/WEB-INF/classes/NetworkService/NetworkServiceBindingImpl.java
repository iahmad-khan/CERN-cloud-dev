/**
 * NetworkServiceBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetworkService;

import java.util.HashMap;
import java.util.Vector;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.apache.log4j.Logger;

import NetworkDataTypes.*;

public class NetworkServiceBindingImpl implements NetworkService.NetworkServiceInterface {

    private static Logger logger = Logger.getLogger(NetworkServiceBindingImpl.class);

    private static Person defaultPerson = new Person("DefaultName", "DefaultFName", "DefaultDepartment", "DefaultGroup",
            "DefaultEmail@cern.ch", "DefaultPhone", (long)1);

    private static Location defaultLocation = new Location("DefaultBuilding", "DefaultFloor", "DefaultRoom");

    private static OperatingSystem defaultOS = new OperatingSystem("DefaultOS", "DefaultOSVersion");

    private static HashMap<String,NetworkDataTypes.DeviceInfo> devices = new HashMap<String,NetworkDataTypes.DeviceInfo>();

    private static HashMap<String,VMClusterInfo> clusters = new HashMap<String,VMClusterInfo>();

    private static HashMap<String,String> parents = new HashMap<String,String>();

    public NetworkServiceBindingImpl() {
	logger.info("initializing new networkservicebindingimpl");
        if (!devices.containsKey("compute")) {
            devices.put("compute",
                        new DeviceInfo("compute", defaultLocation, "defzone", "defstatus", "defmanufacturer",
                                       "defmodel", "deftype", "defdesc", "deftag", "defserial", defaultOS, "definv", null, null,
                                       defaultPerson, defaultPerson,
				       new InterfaceCard[]{new InterfaceCard("02-16-3e", "ETHERNET")},
				       new InterfaceInformation[]{
					       new InterfaceInformation(false, "eth0", "", "10.0.0.1", "CLUSTER1", "SC1",
							       true, "255.255.255.0", "10.0.0.1",
							       new String[]{"137.138.16.5", "137.138.17.5"},
							       null, new String[]{"137.138.16.69", "137.138.17.69"},
							       "2001:1458:301:33::100:67", 64,
								new String[]{"2001:1458:201:1000::5", "2001:1458:201:1100::5"},
								new String[]{"2001:1458:201:1040::69", "2001:1458:201:1140::69"},
								"::1", null, null, null, "RACK1", "DESC1", "", "MEDIUM", null)},
					       false, null, null, false));
             add2Cluster("CLUSTER1", "compute");
        }
    }

    private String dumpDevices() {
        String str = "";
        for (String s : devices.keySet()) {
            DeviceInfo d = devices.get(s);
            str += "\n" + d.getDeviceName() + " Location:" + d.getLocation().getFloor() + d.getLocation().getRoom() + d.getLocation().getBuilding()
                   + " Zone:" + d.getZone() + " Status:" + d.getStatus()
                   + " Manufacturer:" + d.getManufacturer() + " Model:" + d.getModel() + " Type:" + d.getGenericType()
                   + " Description:" + d.getDescription() + " Tag:" + d.getTag() + " SN:" + d.getSerialNumber()
                   + " OS:" + d.getOperatingSystem() + " Inv: " + d.getInventoryNumber()
                   + " Responsible:" + d.getResponsiblePerson().getName() + " User:" + d.getUserPerson().getName()
                   + " IPv6:" + d.getIPv6Ready();
        }
        return str;
    }

    private DeviceBasicInfo deviceInfo2BasicInfo(DeviceInfo dInfo) {
        return new DeviceBasicInfo(
                   dInfo.getDeviceName(), dInfo.getLocation(), dInfo.getZone(), dInfo.getStatus(), dInfo.getManufacturer(),
                   dInfo.getModel(), dInfo.getGenericType(), dInfo.getDescription(), dInfo.getTag(), dInfo.getSerialNumber(),
                   dInfo.getOperatingSystem(), dInfo.getInventoryNumber(), dInfo.getStartDate(), dInfo.getEndDate(),
                   dInfo.getResponsiblePerson(), dInfo.getUserPerson(), dInfo.isHCPResponse(), dInfo.getLastChangeDate(),
                   dInfo.getIPv6Ready());
    }


    private DeviceInfo deviceInput2Info(DeviceInput input) {

        PersonInput ri = input.getResponsiblePerson();
        Person responsible = null;
        if (ri != null) {
            responsible = new Person(ri.getName(), ri.getFirstName(), ri.getDepartment(), ri.getGroup(), null, null, (long)1);
        }
        PersonInput ui = input.getUserPerson();
        Person user = null;
        if (ui != null) {
            user = new Person(ui.getName(), ui.getFirstName(), ui.getDepartment(), ui.getGroup(), null, null, (long)1);
        }
        if (input != null)
            return new DeviceInfo(
                       input.getDeviceName(), input.getLocation(), input.getZone(), "status", input.getManufacturer(),
                       input.getModel(), "type", input.getDescription(), input.getTag(), input.getSerialNumber(),
                       input.getOperatingSystem(), input.getInventoryNumber(), null, null, responsible,
                       responsible, null, null, false, null, null, false);
        return null;
    }

    private void createCluster(String cluster) {
        clusters.put(cluster,
                     new VMClusterInfo(clusters.size(), cluster, cluster + " description",
                                       defaultPerson, new String[] {}));
    }

    private void add2Cluster(String cluster, String device) {

        // create cluster if it does not exist
        if (!clusters.containsKey(cluster)) {
            createCluster(cluster);
        }
        logger.info("adding " + device + " to cluster " + cluster);
        String[] oldList = clusters.get(cluster).getServices();
        String[] newList = new String[oldList.length+1];
        for (int i=0; i<oldList.length; i++)
            newList[i] = oldList[i];
        newList[oldList.length] = device;

        clusters.get(cluster).setServices(newList);

    }

    private void addDevice(String cluster, NetworkDataTypes.DeviceInput VMDevice, NetworkDataTypes.InterfaceCard interfaceCard, String parent) {

        parents.put(VMDevice.getDeviceName(), parent);

	int ip = devices.size()+1;
	DeviceInfo newDevice = deviceInput2Info(VMDevice);
	newDevice.setNetworkInterfaceCards(new InterfaceCard[]{interfaceCard});
	newDevice.setInterfaces(new InterfaceInformation[]{
					       new InterfaceInformation(false, "eth0", "", "10.0.0." + ip, "CLUSTER1", "SC1",
							       true, "255.255.255.0", "10.0.0.1",
							       new String[]{"137.138.16.5", "137.138.17.5"},
							       null, new String[]{"137.138.16.69", "137.138.17.69"},
							       "2001:1458:301:33::100:67", 64,
								new String[]{"2001:1458:201:1000::5", "2001:1458:201:1100::5"},
								new String[]{"2001:1458:201:1040::69", "2001:1458:201:1140::69"},
								"::1", null, null, null, "RACK1", "DESC1", "", "MEDIUM", null)});
        devices.put(VMDevice.getDeviceName(), newDevice);

        add2Cluster(cluster, VMDevice.getDeviceName());

        logger.info("created " + VMDevice.getDeviceName() + " with ip " + ip + " :: totals are now: cluster (" + clusters.get(cluster).getServices().length + ") .. global (" + devices.size() + ")");
    }

    public java.lang.String getAuthToken(java.lang.String login, java.lang.String password, java.lang.String type) throws java.rmi.RemoteException {
	logger.info("getting new authtoken :: " + login + " :: " + password + " :: " + type);
        return "123456";
    }

    public java.lang.String[] searchDevice(NetworkDataTypes.Auth auth, NetworkDataTypes.DeviceSearch deviceSearch) throws java.rmi.RemoteException {
        Vector<String> result = new Vector<String>();
        String ip = deviceSearch.getIPAddress();
	logger.info("searching for device :: " + ip);
        for (DeviceInfo d : devices.values()) {
            for (InterfaceInformation iface : d.getInterfaces()) {
                if (iface.getIPAddress().equals(ip))
                    result.add(d.getDeviceName());
            }
        }
        return result.toArray(new String[0]);
    }

    public NetworkDataTypes.DeviceBasicInfo getDeviceBasicInfo(java.lang.String deviceName) throws java.rmi.RemoteException {
	logger.info("getting device basic info " + deviceName);
        DeviceInfo d = devices.get(deviceName);
        if (d != null)
            return deviceInfo2BasicInfo(d);
        return null;
    }

    public NetworkDataTypes.DeviceInfo getDeviceInfo(NetworkDataTypes.Auth auth, java.lang.String deviceName) throws java.rmi.RemoteException {
        logger.info("getting device info :: " + deviceName);
        DeviceInfo result = devices.get(deviceName);
        logger.info("result " + result);
        return result;
    }

    public NetworkDataTypes.DeviceInfo[] getDeviceInfoArray(NetworkDataTypes.Auth auth, java.lang.String[] deviceNameList) throws java.rmi.RemoteException {
        return null;
    }

    public NetworkDataTypes.DeviceInfo getDeviceInfoFromNameMAC(java.lang.String deviceName, java.lang.String MAC) throws java.rmi.RemoteException {
        return null;
    }

    public NetworkDataTypes.DeviceInfo getMyDeviceInfo() throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String[] getLastChangedDevices(NetworkDataTypes.Auth auth, int minutes) throws java.rmi.RemoteException {
        return null;
    }

    public boolean bulkInsert(NetworkDataTypes.Auth auth, NetworkDataTypes.DeviceInput device, NetworkDataTypes.InterfaceCard[] cards, NetworkDataTypes.BulkInterface[] interfaces) throws java.rmi.RemoteException {
        return false;
    }

    public boolean bulkInsertAuto(NetworkDataTypes.DeviceInput device, NetworkDataTypes.InterfaceCard[] cards, NetworkDataTypes.BulkInterfaceAuto[] interfaces) throws java.rmi.RemoteException {
        return false;
    }

    public boolean bulkRemove(NetworkDataTypes.Auth auth, java.lang.String deviceName) throws java.rmi.RemoteException {
        return false;
    }

    public boolean deviceInsert(NetworkDataTypes.Auth auth, NetworkDataTypes.DeviceInput device) throws java.rmi.RemoteException {
        return false;
    }

    public boolean deviceAddCard(NetworkDataTypes.Auth auth, java.lang.String deviceName, NetworkDataTypes.InterfaceCard interfaceCard) throws java.rmi.RemoteException {
        return false;
    }

    public boolean deviceAddBulkInterface(NetworkDataTypes.Auth auth, java.lang.String deviceName, NetworkDataTypes.BulkInterface bulkInterface) throws java.rmi.RemoteException {
        return false;
    }

    public boolean deviceRemove(NetworkDataTypes.Auth auth, java.lang.String deviceName) throws java.rmi.RemoteException {
        return false;
    }

    public boolean deviceRemoveCard(NetworkDataTypes.Auth auth, java.lang.String deviceName, java.lang.String hardwareAddress) throws java.rmi.RemoteException {
        return false;
    }

    public boolean deviceRemoveBulkInterface(NetworkDataTypes.Auth auth, java.lang.String deviceName, java.lang.String interfaceName) throws java.rmi.RemoteException {
        return false;
    }

    public boolean deviceMoveBulkInterface(NetworkDataTypes.Auth auth, java.lang.String deviceName, java.lang.String interfaceName, NetworkDataTypes.BulkInterface bulkInterface, NetworkDataTypes.BulkMoveOptions bulkMoveOptions) throws java.rmi.RemoteException {
        return false;
    }

    public boolean deviceUpdate(NetworkDataTypes.Auth auth, java.lang.String deviceName, NetworkDataTypes.DeviceInput deviceInput) throws java.rmi.RemoteException {
        return false;
    }

    public boolean deviceGlobalRename(NetworkDataTypes.Auth auth, java.lang.String deviceName, java.lang.String newDeviceName) throws java.rmi.RemoteException {
        return false;
    }

    public boolean setHCPResponse(NetworkDataTypes.Auth auth, java.lang.String[] deviceList, boolean HCPFlag) throws java.rmi.RemoteException {
        return false;
    }

    public boolean deviceUpdateIPv6Ready(NetworkDataTypes.Auth auth, java.lang.String deviceName, boolean IPv6Ready) throws java.rmi.RemoteException {
	logger.info("setting device ipv6ready :: " + deviceName + " :: " + IPv6Ready);
        DeviceInfo d = devices.get(deviceName);
        if (d != null) {
            d.setIPv6Ready(IPv6Ready);
            return true;
        }
        return false;
    }

    public boolean deviceSetBOOTPInfo(NetworkDataTypes.Auth auth, java.lang.String device, java.lang.String server, java.lang.String imagePath) throws java.rmi.RemoteException {
        return false;
    }

    public boolean deviceRemoveBOOTPInfo(NetworkDataTypes.Auth auth, java.lang.String device) throws java.rmi.RemoteException {
        return false;
    }

    public NetworkDataTypes.BOOTPInfo getBOOTPInfo(NetworkDataTypes.Auth auth, java.lang.String device) throws java.rmi.RemoteException {
        return null;
    }

    public NetworkDataTypes.BulkInterface getBulkInterfaceInfo(NetworkDataTypes.Auth auth, java.lang.String interfaceName) throws java.rmi.RemoteException {
        return null;
    }

    public boolean setInsertAddress(NetworkDataTypes.Auth auth, java.lang.String set, java.lang.String address) throws java.rmi.RemoteException {
        return false;
    }

    public boolean setInsertService(NetworkDataTypes.Auth auth, java.lang.String set, java.lang.String service) throws java.rmi.RemoteException {
        return false;
    }

    public boolean setDeleteAddress(NetworkDataTypes.Auth auth, java.lang.String set, java.lang.String address) throws java.rmi.RemoteException {
        return false;
    }

    public boolean setDeleteService(NetworkDataTypes.Auth auth, java.lang.String set, java.lang.String service) throws java.rmi.RemoteException {
        return false;
    }

    public NetworkDataTypes.SetInfo getSetInfo(NetworkDataTypes.Auth auth, java.lang.String setName) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String getSetNameFromID(NetworkDataTypes.Auth auth, long setID) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String[] getSetAllInterfaces(NetworkDataTypes.Auth auth, java.lang.String setName) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String[] getSetInterfacesTrusting(NetworkDataTypes.Auth auth, java.lang.String setName) throws java.rmi.RemoteException {
        return null;
    }

    public NetworkDataTypes.InetInfo[] getHCPInfoArray(NetworkDataTypes.Auth auth, java.lang.String[] hosts) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String[] getDevicesFromService(NetworkDataTypes.Auth auth, java.lang.String service) throws java.rmi.RemoteException {
	logger.info("getting devices from service :: " + service);
        return clusters.get(service).getServices();
    }

    public java.lang.String[] getSwitchesFromService(NetworkDataTypes.Auth auth, java.lang.String service) throws java.rmi.RemoteException {
        return null;
    }

    public NetworkDataTypes.SwitchPort[] getSwitchInfo(NetworkDataTypes.Auth auth, java.lang.String switchName) throws java.rmi.RemoteException {
        return null;
    }

    public NetworkDataTypes.Connection[] getConnectionsFromDevice(NetworkDataTypes.Auth auth, java.lang.String deviceName) throws java.rmi.RemoteException {
        return null;
    }

    public NetworkDataTypes.OutletLocation getOutletLocationFromSwitchPort(NetworkDataTypes.Auth auth, java.lang.String switchName, java.lang.String portName) throws java.rmi.RemoteException {
        return null;
    }

    public NetworkDataTypes.ObservedSwitchConnection[] getCurrentConnection(java.lang.String ip, java.lang.String[] hardwareAddressList) throws java.rmi.RemoteException {
        return null;
    }

    public NetworkDataTypes.ObservedSwitchConnection[] getMyCurrentConnection(java.lang.String[] hardwareAddressList) throws java.rmi.RemoteException {
        return null;
    }

    public boolean enableFanOutFromSwitchPort(NetworkDataTypes.Auth auth, java.lang.String switchName, java.lang.String portName) throws java.rmi.RemoteException {
        return false;
    }

    public boolean setUnsetBindedInterface(NetworkDataTypes.Auth auth, java.lang.String interfaceName, java.lang.String hardwareAddress) throws java.rmi.RemoteException {
        return false;
    }

    public boolean interfaceAddAlias(NetworkDataTypes.Auth auth, java.lang.String interfaceName, java.lang.String alias) throws java.rmi.RemoteException {
	logger.info("adding interface alias :: " + interfaceName + " :: " + alias);
        // TODO: implement
        return false;
    }

    public boolean interfaceRemoveAlias(NetworkDataTypes.Auth auth, java.lang.String interfaceName, java.lang.String alias) throws java.rmi.RemoteException {
	logger.info("removing interface alias :: " + interfaceName + " :: " + alias);
        //TODO: implement
        return false;
    }

    public boolean interfaceMoveAlias(NetworkDataTypes.Auth auth, java.lang.String interfaceName, java.lang.String alias, java.lang.String newInterfaceName) throws java.rmi.RemoteException {
        return false;
    }

    public boolean interfaceRename(NetworkDataTypes.Auth auth, java.lang.String interfaceName, java.lang.String newInterfaceName) throws java.rmi.RemoteException {
        return false;
    }

    public boolean interfaceMove(NetworkDataTypes.Auth auth, java.lang.String interfaceName, java.lang.String newDeviceName) throws java.rmi.RemoteException {
        return false;
    }

    public java.lang.String[] searchSet(NetworkDataTypes.Auth auth, java.lang.String setPattern) throws java.rmi.RemoteException {
        return null;
    }

    public boolean setInsert(NetworkDataTypes.Auth auth, NetworkDataTypes.SetInput set) throws java.rmi.RemoteException {
        return false;
    }

    public boolean setRemove(NetworkDataTypes.Auth auth, java.lang.String setName) throws java.rmi.RemoteException {
        return false;
    }

    public boolean setSwitchPortTypeStatus(NetworkDataTypes.Auth auth, java.lang.String switchName, java.lang.String portName, NetworkDataTypes.SwitchPortTypeStatus switchPortTypeStatus) throws java.rmi.RemoteException {
        return false;
    }

    public boolean setSwitchPortService(NetworkDataTypes.Auth auth, java.lang.String switchName, java.lang.String portName, java.lang.String service) throws java.rmi.RemoteException {
        return false;
    }

    public NetworkDataTypes.SwitchPortTypeStatus getSwitchPortTypeStatus(NetworkDataTypes.Auth auth, java.lang.String switchName, java.lang.String portName) throws java.rmi.RemoteException {
        return null;
    }

    public NetworkDataTypes.NetNameTuple[] searchNetNameTable(NetworkDataTypes.Auth auth, java.lang.String netName) throws java.rmi.RemoteException {
        return null;
    }

    public boolean deviceAddSecondaryInterface(NetworkDataTypes.Auth auth, java.lang.String deviceName, java.lang.String baseInterfaceName, NetworkDataTypes.LogicalInterfaceInput logicalInterface) throws java.rmi.RemoteException {
        return false;
    }

    public boolean deviceAddIPMIInterface(NetworkDataTypes.Auth auth, java.lang.String deviceName, java.lang.String baseInterfaceName, NetworkDataTypes.InterfaceCard interfaceCard, NetworkDataTypes.IPMIOptions IPMIOptions) throws java.rmi.RemoteException {
        return false;
    }

    public boolean deviceAddLogicalInterface(NetworkDataTypes.Auth auth, java.lang.String deviceName, NetworkDataTypes.LogicalInterfaceInput logicalInterface) throws java.rmi.RemoteException {
        return false;
    }

    public boolean deviceRemoveLogicalInterface(NetworkDataTypes.Auth auth, java.lang.String deviceName, java.lang.String interfaceName) throws java.rmi.RemoteException {
        return false;
    }

    public boolean interfaceUpdateDescription(NetworkDataTypes.Auth auth, java.lang.String interfaceName, java.lang.String description) throws java.rmi.RemoteException {
        return false;
    }

    public boolean serviceUpdateDescription(NetworkDataTypes.Auth auth, java.lang.String serviceName, java.lang.String description) throws java.rmi.RemoteException {
        return false;
    }

    public boolean deviceSyncIPMIInterface(NetworkDataTypes.Auth auth, java.lang.String deviceName, java.lang.String interfaceName, java.lang.String IPMIInterfaceName, NetworkDataTypes.IPMIOptions IPMIOptions) throws java.rmi.RemoteException {
        return false;
    }

    public NetworkDataTypes.ServiceInfo getServiceInfo(NetworkDataTypes.Auth auth, java.lang.String serviceName) throws java.rmi.RemoteException {
	logger.info("fetching service info for '" + serviceName + "'");
	ServiceInfo srvInfo = new ServiceInfo("SRV1", "CLUSTER1", "10.0.0.2", "10.0.0.255", 100, "255.255.0.0", "10.0.0.1",
			new String[]{"137.138.16.5", "137.138.17.5"},
			new String[]{"137.138.16.69", "137.138.17.69"},
			null, "", null,
			100, 100, "2001:1458:301:33::100:67", 64, "2001:1458:301:33::1",
			new String[]{"2001:1458:201:1000::5", "2001:1458:201:1100::5"},
			new String[]{"2001:1458:201:1040::69", "2001:1458:201:1140::69"});
	return srvInfo;
    }

    public boolean vmCreate(NetworkDataTypes.Auth auth, NetworkDataTypes.DeviceInput VMDevice, NetworkDataTypes.InterfaceCard interfaceCard, java.lang.String VMClusterName, java.lang.String VMParent, NetworkDataTypes.VMOptions VMOptions) throws java.rmi.RemoteException {
	logger.info("creating vm :: " + VMDevice.getDeviceName() + " :: cluster: " + VMClusterName + " :: parent: " + VMParent);


        addDevice(VMClusterName, VMDevice, interfaceCard, VMParent);

        return true;
    }

    public boolean vmMigrate(NetworkDataTypes.Auth auth, java.lang.String VMName, java.lang.String newParent) throws java.rmi.RemoteException {
	logger.info("migrating vm :: " + VMName + " :: newparent: " + newParent);
        return false;
    }

    public boolean vmMove(NetworkDataTypes.Auth auth, java.lang.String VMName, java.lang.String VMClusterName, java.lang.String VMParent, NetworkDataTypes.VMOptions VMOptions) throws java.rmi.RemoteException {
	logger.info("moving vm :: " + VMName + " :: cluster: " + VMClusterName + " :: parent: " + VMParent);
        return false;
    }

    public boolean vmUpdate(NetworkDataTypes.Auth auth, java.lang.String deviceName, NetworkDataTypes.DeviceInput deviceInput) throws java.rmi.RemoteException {
	logger.info("updating vm :: " + deviceName );
        devices.remove(deviceName);
        devices.put(deviceInput.getDeviceName(), deviceInput2Info(deviceInput));
        logger.info("Updated " + deviceName);
        return true;
    }

    public boolean vmDestroy(NetworkDataTypes.Auth auth, java.lang.String VMName) throws java.rmi.RemoteException {
        return false;
    }

    public NetworkDataTypes.VMClusterInfo vmClusterGetInfo(NetworkDataTypes.Auth auth, java.lang.String VMClusterName) throws java.rmi.RemoteException {
        return clusters.get(VMClusterName);
    }

    public java.lang.String[] vmClusterGetDevices(NetworkDataTypes.Auth auth, java.lang.String VMClusterName) throws java.rmi.RemoteException {
        return clusters.get(VMClusterName).getServices();
    }

    public NetworkDataTypes.VMInfo vmGetInfo(NetworkDataTypes.Auth auth, java.lang.String VMName) throws java.rmi.RemoteException {
        return null;
    }

    public java.lang.String[] vmGetClusterMembership(NetworkDataTypes.Auth auth, java.lang.String deviceName) throws java.rmi.RemoteException {
	logger.info("getting vm cluster membership :: " + deviceName);
        //TODO:implement
        return null;
    }

    public java.lang.String[] vmSearchCluster(NetworkDataTypes.Auth auth, NetworkDataTypes.VMClusterSearch VMClusterSearch) throws java.rmi.RemoteException {
        return null;
    }

    public boolean vmSetUnsetManagedFlag(NetworkDataTypes.Auth auth, java.lang.String deviceName, boolean flag) throws java.rmi.RemoteException {
        return true;
    }

    public boolean vmNetReset(NetworkDataTypes.Auth auth, java.lang.String VMName) throws java.rmi.RemoteException {
	logger.info("resetting vm network :: " + VMName);
        DeviceInfo d = devices.get(VMName);
        if (d != null) {
            d.setNetworkInterfaceCards(new InterfaceCard[] {});
            d.setInterfaces(new InterfaceInformation[] {});
            return true;
        }
        return false;
    }

    public boolean dnsZoneUpdate(NetworkDataTypes.Auth auth, java.lang.String zone, NetworkDataTypes.DnsZoneOptions dnsZoneOptions) throws java.rmi.RemoteException {
        return false;
    }

    public boolean svcPrincipalAdd(NetworkDataTypes.Auth auth, java.lang.String deviceName, java.lang.String serviceName, NetworkDataTypes.PersonInput person) throws java.rmi.RemoteException {
        return false;
    }

    public boolean svcPrincipalRemove(NetworkDataTypes.Auth auth, java.lang.String deviceName, java.lang.String serviceName) throws java.rmi.RemoteException {
        return false;
    }

    public NetworkDataTypes.DNSDelegatedEntry[] dnsDelegatedSearch(NetworkDataTypes.Auth auth, java.lang.String search) throws java.rmi.RemoteException {
        return null;
    }

    public NetworkDataTypes.DNSDelegatedEntry dnsDelegatedGetByNameView(NetworkDataTypes.Auth auth, java.lang.String domain, java.lang.String view) throws java.rmi.RemoteException {
        return null;
    }

    public boolean dnsDelegatedAdd(NetworkDataTypes.Auth auth, NetworkDataTypes.DNSDelegatedInput DNSDelegatedInput) throws java.rmi.RemoteException {
        return false;
    }

    public NetworkDataTypes.DNSDelegatedKey[] dnsDelegatedListKeys(NetworkDataTypes.Auth auth) throws java.rmi.RemoteException {
        return null;
    }

    public boolean dnsDelegatedRemove(NetworkDataTypes.Auth auth, java.lang.String domain, java.lang.String view) throws java.rmi.RemoteException {
        return false;
    }

    public boolean dnsDelegatedAliasAdd(NetworkDataTypes.Auth auth, java.lang.String domain, java.lang.String view, java.lang.String alias) throws java.rmi.RemoteException {
        return false;
    }

    public boolean dnsDelegatedAliasRemove(NetworkDataTypes.Auth auth, java.lang.String domain, java.lang.String view, java.lang.String alias) throws java.rmi.RemoteException {
        return false;
    }

}
