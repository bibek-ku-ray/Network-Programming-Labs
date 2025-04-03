package chapter6;

import java.io.IOException;
import java.net.Socket;

public class Pro3ReadPort {
    public static void main(String[] args) {
        int startPort = 7000;
        int endPort = 9000;

        System.out.println("Scanning ports on localhost...");

        for (int port = startPort; port <= endPort; port++) {
            try (
                    Socket socket = new Socket("localhost", port);
            ) {
                System.out.println("Port " + port + " is open.");
            } catch (IOException e) {
            }
        }

        System.out.println("Port scan complete.");
    }
}

//output
/*  Scanning ports on localhost...
    Port 7680 is open.
    Port 8828 is open.
    Port scan complete.
*/

