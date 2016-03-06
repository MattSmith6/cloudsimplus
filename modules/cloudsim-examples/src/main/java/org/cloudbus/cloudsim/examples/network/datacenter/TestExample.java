package org.cloudbus.cloudsim.examples.network.datacenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.resources.FileStorage;
import org.cloudbus.cloudsim.VmSchedulerTimeShared;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.util.TableBuilderHelper;
import org.cloudbus.cloudsim.util.TextTableBuilder;
import org.cloudbus.cloudsim.network.datacenter.EdgeSwitch;
import org.cloudbus.cloudsim.network.datacenter.NetDatacenterBroker;
import org.cloudbus.cloudsim.network.datacenter.NetworkConstants;
import org.cloudbus.cloudsim.network.datacenter.NetworkDatacenter;
import org.cloudbus.cloudsim.network.datacenter.NetworkHost;
import org.cloudbus.cloudsim.network.datacenter.NetworkVm;
import org.cloudbus.cloudsim.network.datacenter.NetworkVmAllocationPolicy;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.ResourceProvisionerSimple;
import org.cloudbus.cloudsim.resources.Bandwidth;
import org.cloudbus.cloudsim.resources.Ram;

public class TestExample {

    /** The vmlist. */
    private static List<NetworkVm> vmlist;

    /**
     * Creates main() to run this example.
     * 
     * @param args
     *            the args
     */
    public static void main(String[] args) {
            Log.printFormattedLine("Starting %s...", TestExample.class.getSimpleName());
            try {
                    int num_user = 1; // number of cloud users
                    Calendar calendar = Calendar.getInstance();
                    boolean trace_flag = false; // mean trace events

                    // Initialize the CloudSim library
                    CloudSim.init(num_user, calendar, trace_flag);

                    // Second step: Create Datacenters
                    // Datacenters are the resource providers in CloudSim. We need at
                    // list one of them to run a CloudSim simulation
                    NetworkDatacenter datacenter0 = createDatacenter("Datacenter_0");

                    // Third step: Create Broker
                    NetDatacenterBroker broker = createBroker();
                    broker.setLinkDC(datacenter0);
                    // broker.setLinkDC(datacenter0);
                    // Fifth step: Create one Cloudlet

                    vmlist = new ArrayList<NetworkVm>();

                    // submit vm list to the broker

                    broker.submitVmList(vmlist);

                    // Sixth step: Starts the simulation
                    CloudSim.startSimulation();

                    CloudSim.stopSimulation();

                    // Final step: Print results when simulation is over
                    List<Cloudlet> newList = broker.getCloudletReceivedList();
                    TableBuilderHelper.print(new TextTableBuilder(), newList);
                    Log.printFormattedLine("%s finished!", TestExample.class.getSimpleName());
                    System.out.println("numberofcloudlet " + newList.size() + " Cached "
                                    + NetDatacenterBroker.cachedcloudlet + " Data transfered "
                                    + NetworkConstants.totaldatatransfer);

                    Log.printLine("TestExample finished!");
            } catch (Exception e) {
                    e.printStackTrace();
                    Log.printLine("Unwanted errors happen");
            }
    }

    /**
     * Creates the datacenter.
     * 
     * @param name
     *            the name
     * 
     * @return the datacenter
     */
    private static NetworkDatacenter createDatacenter(String name) {

            // Here are the steps needed to create a PowerDatacenter:
            // 1. We need to create a list to store
            // our machine

            List<NetworkHost> hostList = new ArrayList<NetworkHost>();

            // 2. A Machine contains one or more PEs or CPUs/Cores.
            // In this example, it will have only one core.
            // List<Pe> peList = new ArrayList<Pe>();

            int mips = 1;

            // 3. Create PEs and add these into a list.
            // peList.add(new Pe(0, new PeProvisionerSimple(mips))); // need to
            // store Pe id and MIPS Rating

            // 4. Create Host with its id and list of PEs and add them to the list
            // of machines
            int ram = 2048; // host memory (MB)
            long storage = 1000000; // host storage
            long bw = 10000;
            for (int i = 0; i < NetworkConstants.EdgeSwitchPort * NetworkConstants.AggSwitchPort
                            * NetworkConstants.RootSwitchPort; i++) {
                    // 2. A Machine contains one or more PEs or CPUs/Cores.
                    // In this example, it will have only one core.
                    // 3. Create PEs and add these into an object of PowerPeList.
                    List<Pe> peList = new ArrayList<>();
                    peList.add(new Pe(0, new PeProvisionerSimple(mips))); // need to
                    // store
                    // PowerPe
                    // id and
                    // MIPS
                    // Rating
                    peList.add(new Pe(1, new PeProvisionerSimple(mips))); // need to
                    // store
                    // PowerPe
                    // id and
                    // MIPS
                    // Rating
                    peList.add(new Pe(2, new PeProvisionerSimple(mips))); // need to
                    // store
                    // PowerPe
                    // id and
                    // MIPS
                    // Rating
                    peList.add(new Pe(3, new PeProvisionerSimple(mips))); // need to
                    // store
                    // PowerPe
                    // id and
                    // MIPS
                    // Rating
                    peList.add(new Pe(4, new PeProvisionerSimple(mips))); // need to
                    // store
                    // PowerPe
                    // id and
                    // MIPS
                    // Rating
                    peList.add(new Pe(5, new PeProvisionerSimple(mips))); // need to
                    // store
                    // PowerPe
                    // id and
                    // MIPS
                    // Rating
                    peList.add(new Pe(6, new PeProvisionerSimple(mips))); // need to
                    // store
                    // PowerPe
                    // id and
                    // MIPS
                    // Rating
                    peList.add(new Pe(7, new PeProvisionerSimple(mips))); // need to
                    // store
                    // PowerPe
                    // id and
                    // MIPS
                    // Rating

                    // 4. Create PowerHost with its id and list of PEs and add them to
                    // the list of machines
                    hostList.add(new NetworkHost(
                                    i,
                                    new ResourceProvisionerSimple(new Ram(ram)),
                                    new ResourceProvisionerSimple(new Bandwidth(bw)),
                                    storage,
                                    peList,
                                    new VmSchedulerTimeShared(peList))); // This is our machine
            }

            // 5. Create a DatacenterCharacteristics object that stores the
            // properties of a data center: architecture, OS, list of
            // Machines, allocation policy: time- or space-shared, time zone
            // and its price (G$/Pe time unit).
            String arch = "x86"; // system architecture
            String os = "Linux"; // operating system
            String vmm = "Xen";
            double time_zone = 10.0; // time zone this resource located
            double cost = 3.0; // the cost of using processing in this resource
            double costPerMem = 0.05; // the cost of using memory in this resource
            double costPerStorage = 0.001; // the cost of using storage in this
            // resource
            double costPerBw = 0.0; // the cost of using bw in this resource
            LinkedList<FileStorage> storageList = new LinkedList<>(); // we are
            // not
            // adding
            // SAN
            // devices by now

            DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
                            arch,
                            os,
                            vmm,
                            hostList,
                            time_zone,
                            cost,
                            costPerMem,
                            costPerStorage,
                            costPerBw);

            // 6. Finally, we need to create a NetworkDatacenter object.
            NetworkDatacenter datacenter = null;
            try {
                    datacenter = new NetworkDatacenter(
                                    name,
                                    characteristics,
                                    new NetworkVmAllocationPolicy(hostList),
                                    storageList,
                                    0);

            } catch (Exception e) {
                    e.printStackTrace();
            }
            // Create Internal Datacenter network
            CreateNetwork(2, datacenter);
            return datacenter;
    }

    // We strongly encourage users to develop their own broker policies, to
    // submit vms and cloudlets according
    // to the specific rules of the simulated scenario
    /**
     * Creates the broker.
     * 
     * @return the datacenter broker
     */
    private static NetDatacenterBroker createBroker() {
            NetDatacenterBroker broker = null;
            try {
                    broker = new NetDatacenterBroker("Broker");
            } catch (Exception e) {
                    e.printStackTrace();
                    return null;
            }
            return broker;
    }

    static void CreateNetwork(int numhost, NetworkDatacenter dc) {

            // Edge Switch
            EdgeSwitch edgeswitch[] = new EdgeSwitch[1];

            for (int i = 0; i < 1; i++) {
                    edgeswitch[i] = new EdgeSwitch("Edge" + i, NetworkConstants.EDGE_LEVEL, dc);
                    // edgeswitch[i].uplinkswitches.add(null);
                    dc.Switchlist.put(edgeswitch[i].getId(), edgeswitch[i]);
                    // aggswitch[(int)
                    // (i/Constants.AggSwitchPort)].downlinkswitches.add(edgeswitch[i]);
            }

            for (Host hs : dc.getHostList()) {
                    NetworkHost hs1 = (NetworkHost) hs;
                    hs1.bandwidth = NetworkConstants.BandWidthEdgeHost;
                    int switchnum = (int) (hs.getId() / NetworkConstants.EdgeSwitchPort);
                    edgeswitch[switchnum].hostlist.put(hs.getId(), hs1);
                    dc.HostToSwitchid.put(hs.getId(), edgeswitch[switchnum].getId());
                    hs1.sw = edgeswitch[switchnum];
                    List<NetworkHost> hslist = hs1.sw.fintimelistHost.get(0D);
                    if (hslist == null) {
                            hslist = new ArrayList<NetworkHost>();
                            hs1.sw.fintimelistHost.put(0D, hslist);
                    }
                    hslist.add(hs1);

            }

    }
}
