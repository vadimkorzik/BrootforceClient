package Bruteforce.core;

/**
 * User: Vadim | Date: 14.04.12 | Time: 21:52
 */
public class BruteforceManager {

    private static String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static char[] alphabetChars = alphabet.toCharArray();
    private static int alphabetLength = alphabetChars.length;

    private int countIntervals = 0;
    private int currentInterval;

    private String lastPassword = null;
    private String sha1Hash = null;

    private String password;

    public void setLastPassword(String lastPassword) {
        this.lastPassword = lastPassword;
    }

    public void setSha1Hash(String sha1Hash) {
        this.sha1Hash = sha1Hash;
    }

    public void setCountIntervals(int countIntervals) {
        this.countIntervals = countIntervals;
    }

    public String getPassword() {
        return password;
    }

    public void success(String password) {
        this.password = password;
    }

    public static char[] getAlphabetChars() {
        return alphabetChars;
    }
}
