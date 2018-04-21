/*
 * Title:        CloudSim Toolkit
 * Description:  CloudSim (Cloud Simulation) Toolkit for Modeling and Simulation of Clouds
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2009-2012, The University of Melbourne, Australia
 */
package org.cloudbus.cloudsim.allocationpolicies.migration;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

import org.cloudbus.cloudsim.allocationpolicies.VmAllocationPolicy;
import org.cloudbus.cloudsim.hosts.Host;
import org.cloudbus.cloudsim.vms.Vm;
import org.cloudbus.cloudsim.selectionpolicies.power.PowerVmSelectionPolicy;

/**
 * A {@link VmAllocationPolicy} that uses a Static CPU utilization Threshold (THR) to
 * detect host {@link #getUnderUtilizationThreshold() under} and
 * {@link #getOverUtilizationThreshold(Host)} over} utilization.
 *
 * <p>It's a <b>Worst Fit policy</b> which selects the Host having the least used amount of CPU
 * MIPS to place a given VM, <b>disregarding energy consumption</b>.</p>
 *
 * @author Anton Beloglazov
 * @author Manoel Campos da Silva Filho
 * @since CloudSim Plus 1.0
 */
public class VmAllocationPolicyMigrationWorstFitStaticThreshold extends VmAllocationPolicyMigrationStaticThreshold {
    public VmAllocationPolicyMigrationWorstFitStaticThreshold(
        final PowerVmSelectionPolicy vmSelectionPolicy,
        final double overUtilizationThreshold)
    {
        super(vmSelectionPolicy, overUtilizationThreshold);
    }

    /**
     * Gets the Host having the most available MIPS capacity (min used MIPS).
     *
     * <p>This method is ignoring the additional filtering performed by the super class.
     * This way, Host selection is performed ignoring energy consumption.
     * However, all the basic filters defined in the super class are ensured, since
     * this method is called just after they are applied.
     * </p>
     *
     * @param vm {@inheritDoc}
     * @param hostStream {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    protected Optional<Host> findHostForVmInternal(final Vm vm, final Stream<Host> hostStream) {
        /*It's ignoring the super class to intentionally avoid the additional filtering performed there
        * and to apply a different method to select the Host to place the VM.*/
        return hostStream.min(Comparator.comparingDouble(Host::getUtilizationOfCpuMips));
    }
}
