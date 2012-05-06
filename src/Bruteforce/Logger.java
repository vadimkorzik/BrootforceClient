package Bruteforce;

/**
 * User: Vadim | Date: 06.05.12 | Time: 22:23
 */

public class Logger {
    public static void log(String msg) {
        System.out.println("> " + msg);
    }

    private String getCurrentTime() {
        return new java.text.SimpleDateFormat("dd.MM.yyyy '-' HH:mm:ss").format(java.util.Calendar.getInstance().getTime());
    }

    private void simpleMessage(String type, String msg) {
        System.out.println("[" + this.getCurrentTime() + "] " + type + msg);
    }

    public void message(String msg) {
        simpleMessage("", msg);
    }

    public void warning(String msg) {
        simpleMessage("[WARNING] ", msg);
    }

    public void error(String msg) {
        simpleMessage("[ERROR] ", msg);
    }
}
