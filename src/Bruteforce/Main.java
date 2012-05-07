package Bruteforce;

import Bruteforce.core.BruteforceManager;
import Bruteforce.core.Hash;
import Bruteforce.core.ScaleOfNotation;
import Bruteforce.network.NetworkManager;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * User: Vadim | Date: 14.04.12 | Time: 20:51
 */
public class Main {

    public static final Logger logger = new Logger();

    public static void main(String[] args) throws Exception {
        BruteforceManager bm = new BruteforceManager();
        NetworkManager nm = new NetworkManager(bm);
        nm.start();
        nm.connect();
        nm.getSha1Hash();
    }
}
