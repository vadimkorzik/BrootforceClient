package Bruteforce.network;

import Bruteforce.Main;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * User: Vadim | Date: 02.05.12 | Time: 22:20
 */
public class Commands {

    // All codes -> [0]

    // Codes: client -> server
    public static final byte REQUEST_FOR_AUTHORIZATION = 1;
    public static final byte REQUEST_FOR_COUNTINTERVALS = 2;
    public static final byte REQUEST_FOR_LASTPASSWORD = 4;
    public static final byte REQUEST_FOR_INTERVAL = 8;
    public static final byte MESSAGE_OF_SUCCESSFUL_BRUTE = 16;
    public static final byte REQUEST_FOR_SHA1HASH = 32;

    // Codes: server -> client
    public static final byte MESSAGE_OF_STOP = 64;

    /**
     * Copy String to byte[] from 2 index <b>(not 0)</b> .
     * Length of str <= 127 !!
     *
     * @param str   String to send
     * @param bytes buffer to send
     */
    public static void stringToByteArray(String str, byte[] bytes) {
        bytes[1] = (byte) str.length();
        int i = 2;
        try {
            for (byte b : str.getBytes()) {
                bytes[i] = b;
                i++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Main.logger.error("stringToByteArray(): " + "ArrayIndexOutOfBoundsException");
        }
    }

    /**
     * Copy String from ByteBuffer from 2 index <b>(not 0)</b> .
     *
     * @param bytes buffer from server
     * @return Decoded String from byte[]
     */
    public static String stringFromByteArray(byte[] bytes) {
        byte length = bytes[1];
        try {
            return new String(Arrays.copyOfRange(bytes, 2, length + 2));
        } catch (ArrayIndexOutOfBoundsException e) {
            Main.logger.error("stringFromByteArray(): " + "ArrayIndexOutOfBoundsException");
            return null;
        }
    }
}
