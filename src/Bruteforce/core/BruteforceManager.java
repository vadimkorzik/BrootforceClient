package Bruteforce.core;

import Bruteforce.Main;

/**
 * User: Vadim | Date: 14.04.12 | Time: 21:52
 */
public class BruteforceManager {

    private static String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static char[] alphabetChars = alphabet.toCharArray();
    private static int alphabetLength = alphabetChars.length;

    private ScaleOfNotation scaleOfNotation = new ScaleOfNotation(alphabetChars);

    private int countIntervals = 0;
    private int currentInterval;

    private String lastPassword = null;
    private String sha1Hash = null;

    private String password;

    private long lastPasswordToTenRadix;

    public BruteforceManager(int countIntervals, String lastPassword, String sha1Hash) {
        this.countIntervals = countIntervals;
        this.lastPassword = lastPassword;
        this.sha1Hash = sha1Hash;
        lastPasswordToTenRadix = scaleOfNotation.toTenRadix(lastPassword) / countIntervals;
    }

    public String getPassword() {
        return password;
    }

    public void success(String password) {
        this.password = password;
        Main.logger.message("Success brute: " + password);
    }

    public static char[] getAlphabetChars() {
        return alphabetChars;
    }

    public boolean brute(int currentInterval) {
        String startString = scaleOfNotation.fromTenRadix(lastPasswordToTenRadix * currentInterval);
        String finishString = scaleOfNotation.fromTenRadix(lastPasswordToTenRadix * (currentInterval + 1));
        Main.logger.message("Brute passwords from " + startString + " to " + finishString + " beginning");
        while (!startString.equals(finishString)) {
            if (Hash.getHash(startString).equals(sha1Hash)) {
                this.success(startString);
                return true;
            }
            startString = scaleOfNotation.incString(startString);
        }
        Main.logger.message("Password not found");
        return false;
    }
}
