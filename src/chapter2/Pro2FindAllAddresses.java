package chapter2;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Pro2FindAllAddresses {
    public static void main(String[] args) throws UnknownHostException {
        String domain = "dns.google.com";

        InetAddress[] inetAddresses = InetAddress.getAllByName(domain);

        for(InetAddress inetAddress: inetAddresses){
            String hostname = inetAddress.getHostName();
            String canonicalName = inetAddress.getCanonicalHostName();
            String hostAddress = inetAddress.getHostAddress();

            System.out.println("Host Name: "+hostname);
            System.out.println("Canonical Name: " + canonicalName);
            System.out.println("host Address: " + hostAddress);
            System.out.println("-----------------");
        }
    }
}

/*
Output: -> Write any 2 to 3 output not all
Host Name: dns.google.com
Canonical Name: dns.google
host Address: 8.8.8.8
-----------------
Host Name: dns.google.com
Canonical Name: dns.google
host Address: 8.8.4.4
-----------------
Host Name: dns.google.com
Canonical Name: dns.google
host Address: 2001:4860:4860:0:0:0:0:8844
-----------------
Host Name: dns.google.com
Canonical Name: dns.google
host Address: 2001:4860:4860:0:0:0:0:8888
-----------------
 */
