package chapter2;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Pro1FindIpType {
    public static void main(String[] args) throws UnknownHostException {
        String url = "fohss.tu.edu.np";
        InetAddress[] inetAddresses = InetAddress.getAllByName(url);

        for (InetAddress inetAddress : inetAddresses) {
            String ip = inetAddress.getHostAddress();
            System.out.println("IP Address: " + ip);
            System.out.println();

            if (inetAddress.getAddress().length == 4) {
                System.out.println("It is an IPv4 address.");
            } else if (inetAddress.getAddress().length == 16) {
                System.out.println("It is an IPv6 address.");
            } else {
                System.out.println("Unknown IP address format.");
            }
        }
    }
}

/*
Output:
IP Address: 202.70.90.4
It is an IPv4 address.
 */


