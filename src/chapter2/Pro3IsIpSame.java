package chapter2;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Pro3IsIpSame {
    public static void main(String[] args) throws UnknownHostException {
        String url = "localhost";
        byte[] address = {127,0,0,1};

        InetAddress ip1 = InetAddress.getByName(url);
        InetAddress ip2 = InetAddress.getByAddress(address);

        System.out.println("ip1 == ip2: "+ ip1.equals(ip2));

    }
}

/*
Output:
ip1 == ip2: true
 */
