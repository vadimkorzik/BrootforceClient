package Bruteforce;

import Bruteforce.core.Hash;
import Bruteforce.network.NetworkManager;

import java.security.NoSuchAlgorithmException;

/**
 * User: Vadim | Date: 14.04.12 | Time: 20:51
 */
public class Main {
    public static void main(String[] args) throws Exception {
        NetworkManager nm = new NetworkManager();
        nm.connect();
    }
}
