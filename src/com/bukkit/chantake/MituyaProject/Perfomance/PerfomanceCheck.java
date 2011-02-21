package com.bukkit.chantake.MituyaProject.Perfomance;

/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class PerfomanceCheck {

    private static class AnalyzerThread extends Thread {

        private AtomicInteger cpuUsage = new AtomicInteger(Float.floatToIntBits(-1F));
        private CountDownLatch startupLatch = new CountDownLatch(1);
        private long prevCpuTime = 0L;
        private long prevUpTime = 0L;

        @Override
        public void run() {
            setName("CpuUsageAnalyzer");
            try {
                checkCompatibility();
            } catch (UnsupportedOperationException e) {
                startupLatch.countDown();
                throw e;
            }
            while (true) {
                updateCpuUsage();
                sleep1Sec();
            }
        }

        private void checkCompatibility() {
            OperatingSystemMXBean osmx = ManagementFactory.getOperatingSystemMXBean();
            if (osmx == null) {
                throw new UnsupportedOperationException("Failed to get OperatingSystemMXBean");
            }
            Class[] interfaces = osmx.getClass().getInterfaces();
            boolean hasInterface = false;
            if (interfaces != null) {
                for (Class i : interfaces) {
                    if ("com.sun.management.OperatingSystemMXBean".equals(i.getName()) || "com.sun.management.UnixOperatingSystemMXBean".equals(i.getName())) {
                        hasInterface = true;
                        break;
                    }
                }
            }
            if (!hasInterface) {
                throw new UnsupportedOperationException("Incompatible OperatingSystemMXBean class: " + osmx.getClass().getName());
            }
            RuntimeMXBean rtmx = ManagementFactory.getRuntimeMXBean();
            if (rtmx == null) {
                throw new UnsupportedOperationException("Failed to get RuntimeMXBean");
            }
        }

        private long getCpuTime() {
            OperatingSystemMXBean osmx = ManagementFactory.getOperatingSystemMXBean();
            try {
                Method getProcessCpuTime = osmx.getClass().getDeclaredMethod("getProcessCpuTime");
                getProcessCpuTime.setAccessible(true);
                Object o = getProcessCpuTime.invoke(osmx);
                if (o instanceof Long) {
                    return (Long) o;
                }
            } catch (Throwable e) {
            }
            return 0L;
        }

        private float getCpuUsage() {
            try {
                startupLatch.await(1500L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
            }
            return Float.intBitsToFloat(cpuUsage.get());
        }

        private int getCpuUsageint() {
            try {
                startupLatch.await(1500L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
            }
            return cpuUsage.get();
        }

        private long getUpTime() {
            RuntimeMXBean rtmx = ManagementFactory.getRuntimeMXBean();
            return rtmx.getUptime();
        }

        private void sleep1Sec() {
            long after1Sec = System.currentTimeMillis() + 500L;
            while (System.currentTimeMillis() < after1Sec) {
                try {
                    Thread.sleep(50L);
                } catch (InterruptedException e) {
                }
            }
        }

        private void updateCpuUsage() {
            long cpuTime = getCpuTime();
            long upTime = getUpTime();
            long elapsedCpu = cpuTime - prevCpuTime;
            long elapsedTime = upTime - prevUpTime;
            int numProcessors = Runtime.getRuntime().availableProcessors();
            float percentile = Math.min(100F, elapsedCpu / (elapsedTime * 10000F * numProcessors));
            prevCpuTime = cpuTime;
            prevUpTime = upTime;
            cpuUsage.set(Float.floatToIntBits(percentile));
            if (startupLatch.getCount() > 0) {
                startupLatch.countDown();
            }
        }
    }
    private static final PerfomanceCheck singleton = new PerfomanceCheck();
    private AnalyzerThread analyzer = null;

    private PerfomanceCheck() {
        analyzer = new AnalyzerThread();
        analyzer.setDaemon(true);
        try {
            analyzer.start();
        } catch (UnsupportedOperationException e) {
            System.err.println("CpuUsageAnalyzer does not support this platform.");
        }
    }

    public static String getMemoryInfo() {
        DecimalFormat f1 = new DecimalFormat("#,###KB");
        DecimalFormat f2 = new DecimalFormat("##.#");
        long free = Runtime.getRuntime().freeMemory() / 1024;
        long total = Runtime.getRuntime().totalMemory() / 1024;
        long max = Runtime.getRuntime().maxMemory() / 1024;
        long used = total - free;
        double ratio = (used * 100 / (double) total);
        String info =
                "memory info : total=" + f1.format(total) + ","
                + "use now=" + f1.format(used) + " (" + f2.format(ratio) + "%),"
                + "can use max memory=" + f1.format(max);
        return info;
    }

    public static float getCpuUsage() {
        return singleton.analyzer.getCpuUsage();
    }

    public static int getCpuUsageint() {
        return singleton.analyzer.getCpuUsageint();
    }
}
