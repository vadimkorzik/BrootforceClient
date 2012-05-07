package Bruteforce.network;

import Bruteforce.Main;
import Bruteforce.core.BruteforceManager;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

/**
 * User: Vadim | Date: 14.04.12 | Time: 22:43
 */
public class NetworkManager extends Thread {

    public static final int SERVER_PORT = 19191;
    public static final String SERVER_IP = "127.0.0.1";
    public static final int BUFFER_SIZE = 256;

    private Socket socket;

    private InputStream in;
    private OutputStream out;

    byte[] buffer = new byte[BUFFER_SIZE];
    BruteforceManager bruteforceManager;

    public NetworkManager(BruteforceManager bruteforceManager) {
        this.bruteforceManager = bruteforceManager;
    }

    public boolean connect() {
        Main.logger.message("Connecting to " + SERVER_IP + ":" + SERVER_PORT + "...");
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
        } catch (UnknownHostException e) {
            Main.logger.error("connect(): " + "UnknownHostException: " + SERVER_IP + ":" + SERVER_PORT);
            return false;
        } catch (IOException e) {
            Main.logger.error("connect(): " + "IOException");
            return false;
        }

        try {
            in = socket.getInputStream();
        } catch (IOException e) {
            Main.logger.error("connect(): " + "IOException: " + "can't get input stream");
            return false;
        }

        try {
            out = socket.getOutputStream();
        } catch (IOException e) {
            Main.logger.error("connect(): " + "IOException: " + "can't get output stream");
            return false;
        }
        Main.logger.message("Successfully connect to " + SERVER_IP + ":" + SERVER_PORT);
        return true;
        /* byte buffer[] = new byte[2048];
     try {
         Socket socket = new Socket("127.0.0.1", 19191);

         InputStream in = socket.getInputStream();
         OutputStream out = socket.getOutputStream();

         //while (true) {
         buffer[0] = 35;
         buffer[1] = 81;
         buffer[2] = 127;
         out.write(buffer, 0, 3);

         in.read(buffer, 0, 3);
         System.out.println(" >read: " + buffer[0] + " " + buffer[1] + " " + buffer[2]);
         //}

     } catch (Exception e) {
         e.printStackTrace();
     }   */
    }

    public void disconnect() {
        if (socket.isConnected()) {
            try {
                socket.close();
                Main.logger.message("disconnect");
            } catch (IOException e) {
                Main.logger.warning("disconnect(): " + "IOException");
            }
        }
    }

    public int getCountIntervals() {
        buffer[0] = Commands.REQUEST_FOR_COUNTINTERVALS;
        try {
            out.write(buffer);
        } catch (IOException e) {
            Main.logger.error("getCountIntervals(): " + "IOException: " + "can't write to out");
            return -1;
        }

        try {
            in.read(buffer);
        } catch (IOException e) {
            Main.logger.error("getCountIntervals(): " + "IOException: " + "can't read from in");
            return -1;
        }

        ByteBuffer tempBuffer = ByteBuffer.wrap(buffer);
        int result = tempBuffer.getInt(1);
        Main.logger.message("Got the CountIntervals from the server: " + result);
        return result;
    }

    public String getLastPassword() {
        buffer[0] = Commands.REQUEST_FOR_LASTPASSWORD;
        try {
            out.write(buffer);
        } catch (IOException e) {
            Main.logger.error("getLastPassword(): " + "IOException: " + "can't write to out");
            return null;
        }

        try {
            in.read(buffer);
        } catch (IOException e) {
            Main.logger.error("getLastPassword(): " + "IOException: " + "can't read from in");
            return null;
        }
        String result = Commands.stringFromByteArray(buffer);
        Main.logger.message("Got the LastPassword from the server: " + result);
        return result;
    }

    public String getSha1Hash() {
        buffer[0] = Commands.REQUEST_FOR_SHA1HASH;
        try {
            out.write(buffer);
        } catch (IOException e) {
            Main.logger.error("getSha1Hash(): " + "IOException: " + "can't write to out");
            return null;
        }

        try {
            in.read(buffer);
        } catch (IOException e) {
            Main.logger.error("getSha1Hash(): " + "IOException: " + "can't read from in");
            return null;
        }
        String result = Commands.stringFromByteArray(buffer);
        Main.logger.message("Got the Hsa1Hash from the server: " + result);
        return result;
    }

    /**
     * @return current interval for bruteforce
     *         -1, if stop
     */
    public int getInterval() {
        buffer[0] = Commands.REQUEST_FOR_INTERVAL;
        try {
            out.write(buffer);
        } catch (IOException e) {
            Main.logger.error("getInterval(): " + "IOException: " + "can't write to out");
            return -1;
        }

        try {
            in.read(buffer);
        } catch (IOException e) {
            Main.logger.error("getInterval(): " + "IOException: " + "can't read from in");
            return -1;
        }

        ByteBuffer tempBuffer = ByteBuffer.wrap(buffer);
        int interval = tempBuffer.getInt(1);
        if (buffer[0] == 1) {
            Main.logger.message("Got the current Interval from the server: -1 - " + "bruteforce finished");
            return -1;
        } else {
            Main.logger.message("Got the current Interval from the server: " + interval);
            return interval;
        }
    }

    public void successBrute() {
        buffer[0] = Commands.MESSAGE_OF_SUCCESSFUL_BRUTE;
        Commands.stringToByteArray(bruteforceManager.getPassword(), buffer);
        try {
            out.write(buffer);
        } catch (IOException e) {
            Main.logger.error("successBrute(): " + "IOException: " + "can't write to out");
        }
    }

    @Override
    public void run() {
        super.run();
    }
}
