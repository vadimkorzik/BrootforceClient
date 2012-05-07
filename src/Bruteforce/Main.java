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
        NetworkManager nm = new NetworkManager();
        nm.start();
        if (!nm.connect()) {
            System.exit(0);
        }
        BruteforceManager bruteforceManager = new BruteforceManager(nm.getCountIntervals(), nm.getLastPassword(), nm.getSha1Hash());
        nm.setBruteforceManager(bruteforceManager);
        int interval = 0;
        while ((interval = nm.getInterval()) != -1) {
            if (bruteforceManager.brute(interval)) {
                nm.successBrute();
            }
        }
    }
}
