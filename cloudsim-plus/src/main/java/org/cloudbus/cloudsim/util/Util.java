/*
 * CloudSim Plus: A modern, highly-extensible and easier-to-use Framework for
 * Modeling and Simulation of Cloud Computing Infrastructures and Services.
 * http://cloudsimplus.org
 *
 *     Copyright (C) 2015-2021 Universidade da Beira Interior (UBI, Portugal) and
 *     the Instituto Federal de Educação Ciência e Tecnologia do Tocantins (IFTO, Brazil).
 *
 *     This file is part of CloudSim Plus.
 *
 *     CloudSim Plus is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     CloudSim Plus is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with CloudSim Plus. If not, see <http://www.gnu.org/licenses/>.
 */
package org.cloudbus.cloudsim.util;

import org.apache.commons.lang3.StringUtils;

import static org.cloudbus.cloudsim.util.MathUtil.percent;

/**
 * A class with general purpose utilities.
 * @author Manoel Campos da Silva Filho
 * @since CloudSim Plus 6.0.0
 */
public final class Util {
    /** A private default constructor to avoid class instantiation. */
    private Util(){/**/}

    /**
     * Makes the current thread to sleep for a given amount ot milliseconds.
     * @param millis the time to sleep in milliseconds
     */
    public static void sleep(final long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
        }
    }

    /**
     * Prints a progress bar at the command line for any general process
     * represented by several tasks (steps). The bar is updated at the current line.
     * If nothing is printed between updates, the bar is shown at the same place.
     * You can use it like the sample below:
     * <pre>
     * <code>
     *
     * final int total = 100;
     * for (int i = 0; i <= total; i++) {
     *     Util.sleep(120); //simulates some task (use your own code here)
     *     Util.printProgress(i, total);
     * }
     * </code>
     * </pre>
     * @param current the index of the current finished task (step)
     * @param total the total number of tasks (steps)
     * @see #printProgress(int, int, boolean)
     */
    public static void printProgress(final int current, final int total){
        printProgress(current, total, false);
    }

    /**
     * Prints a progress bar at the command line for any general process
     * represented by several tasks (steps).
     * You can use it like the sample below:
     * <pre>
     * <code>
     *
     * final int total = 100;
     * for (int i = 0; i <= total; i++) {
     *     Util.sleep(120); //simulates some task (use your own code here)
     *     Util.printProgress(i, total);
     * }
     * </code>
     * </pre>
     * @param current the index of the current finished task (step)
     * @param total the total number of tasks (steps)
     * @param progressBarInNewLine indicates if the progress bar will be printed in a new line or not
     * @see #printProgress(int, int)
     */
    public static void printProgress(final int current, final int total, final boolean progressBarInNewLine){
        final String progress = StringUtils.repeat('#', current);

        final String end = progressBarInNewLine ? "%n" : "\r";
        final String format = "%120s[%-"+total+"s] %3.0f%% (%d/%d)" + end;
        System.out.printf(format, " ", progress, percent(current, total), current, total);
    }
}
