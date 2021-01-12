package uk.toadl3ss.Leaf.Utillites;

import java.util.Date;

public class Info {
    public static void printInfo(String version) {
        String Version = version;
        String JavaVersion = System.getProperty("java.version");
        int Cores = Runtime.getRuntime().availableProcessors();
        Date startTime = new Date();
        System.out.println("            Version:        " + Version);
        System.out.println("            JavaVersion:    " + JavaVersion);
        System.out.println("            Cores:          " + Cores);
        System.out.println("            StartTime:      " + startTime);
    }
}