package uk.toadl3ss.Leaf.Utillites;

import uk.toadl3ss.Leaf.Main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    // Logging an error to the console
    public static void error(String text) {
        Main.logger.error(text);
    }

    // Logging info to the console
    public static void info(String text) {
        Main.logger.info(text);
    }

    // Logging warns to the console
    public static void warn(String text) {
        Main.logger.warn(text);
    }
}