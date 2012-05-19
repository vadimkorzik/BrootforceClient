package Bruteforce.core;

import java.util.Arrays;

/**
 * User: Vadim | Date: 07.05.12 | Time: 19:23
 */
public class ScaleOfNotation {

    private int radix;

    private char[] alphabetChars;

    /**
     * @param alphabetChars alphabet
     */
    public ScaleOfNotation(char[] alphabetChars) {
        this.alphabetChars = alphabetChars;
        radix = alphabetChars.length;
    }

    /**
     * @param str Letters from
     * @return number in the decimal system
     * @see BruteforceManager.alphabet
     */
    public long toTenRadix(String str) {
        long result = 0;
        int index = str.length();
        index--;
        for (char c : str.toCharArray()) {
            result += Arrays.binarySearch(this.alphabetChars, c) * Math.pow(radix, index);
            index--;
        }
        return result;
    }

    /**
     * @param num number in the decimal system
     * @return number int the radix system
     * @see BruteforceManager.alphabet
     */
    public String fromTenRadix(long num) {
        if (num == 0)
            return "0";
        StringBuffer sb = new StringBuffer();
        int ost;
        while (num != 0) {
            ost = (int) (num % radix);
            sb.insert(0, alphabetChars[ost]);
            num = (num - ost) / radix;
        }
        return sb.toString();
    }

    /**
     * @param chars word
     * @param pos   position letter int word
     */
    private void addTo(char[] chars, int pos) {
        chars[pos] = alphabetChars[Arrays.binarySearch(alphabetChars, chars[pos]) + 1];
    }

    /**
     * @param str current word
     * @return next word
     */
    public String incString(String str) {
        char[] chars = str.toCharArray();
        if (chars[chars.length - 1] != alphabetChars[alphabetChars.length - 1]) {
            addTo(chars, chars.length - 1);
            return new String(chars);
        } else {
            int index = chars.length - 1;
            while (index >= 0 && chars[index] == alphabetChars[alphabetChars.length - 1]) {
                chars[index] = alphabetChars[0];
                index--;
            }
            if (index >= 0) {
                addTo(chars, index);
                return new String(chars);
            } else {
                chars = new char[chars.length + 1];
                Arrays.fill(chars, 0, chars.length, alphabetChars[0]);
                return new String(chars);
            }
        }

    }
}
