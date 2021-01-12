package uk.toadl3ss.Leaf.Utillites;

import static uk.toadl3ss.Leaf.Utillites.Ansi.*;

public class Vanity {
    public static void printVainity() {
        String vanity = "       g.     r_                 __    g__ _ _\n" +
                " g      /\\\\  r| |               / _|   g\\ \\ \\ \\\n" +
                "g      ( ( ) r| |     ___  __ _| |_     g\\ \\ \\ \\\n" +
                "g       \\\\/  r| |    / _ \\/ _` |  _|     g) ) ) )\n" +
                "g        '   r| |___|  __/ (_| | |      g/ / / /\n" +
                "g            r|______\\___|\\__,_|_|     g/_/_/_/ \n" +
                "g     d=======================================";
        String green = vanity.replace("g", ANSI_GREEN);
        String red = green.replace("r", ANSI_RED);
        String VanityFinish = red.replace("d", ANSI_RESET);
        System.out.println(VanityFinish);
    }
}