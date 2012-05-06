package Bruteforce.core;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * User: Vadim | Date: 06.05.12 | Time: 21:07
 */
public class Hash {

    public static String getHash(String str) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        return byteArrayToHexString(digest.digest(str.getBytes()));
    }

    private static String byteArrayToHexString(byte[] bytes) throws Exception {
        String result = "";
        for (int i = 0; i < bytes.length; i++) {
            result += Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }
}
