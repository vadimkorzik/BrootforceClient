package Brootforce;

import Brootforce.network.NetworkManager;

/**
 * User: Vadim | Date: 14.04.12 | Time: 20:51
 */
public class Main {
    public static void main(String[] args) {
        NetworkManager nm = new NetworkManager();
        nm.connect();
    }
}
