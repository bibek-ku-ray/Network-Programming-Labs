package chapter2;

import java.io.IOException;
import java.net.InetAddress;

public class Pro5IsHostReachable {
    public static void main(String[] args) throws IOException {
        String host = "localhost";

        InetAddress inetAddress = InetAddress.getByName(host);
        boolean isReachable = inetAddress.isReachable(5000);

        if (isReachable) {
            System.out.println("Host is reachable.");
        } else {
            System.out.println("Host is not reachable.");

        }
    }
}

/*
Output:
Host is reachable.
 */
