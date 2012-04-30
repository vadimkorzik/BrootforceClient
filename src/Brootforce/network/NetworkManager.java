package Brootforce.network;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * User: Vadim | Date: 14.04.12 | Time: 22:43
 */
public class NetworkManager {

    public void connect() {
        byte buffer[] = new byte[2048];
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
            System.out.println(" >read: ");
            //}

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
