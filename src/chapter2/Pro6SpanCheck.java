package chapter2;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Pro6SpanCheck {

    public static final String BLACKHOLE = "sbl.spamhaus.org";
    public static void main(String[] args) throws UnknownHostException {
        for (String arg : args) {
            if (isSpammer(arg)) {
                System.out.println(arg + " is a known spammer.");
            } else {
                System.out.println(arg + " appears legitimate.");
            }
        }
    }
    private static boolean isSpammer(String arg) {
        try {
            InetAddress address = InetAddress.getByName(arg);
            byte[] quad = address.getAddress();
            String query = BLACKHOLE;
            for (byte octet : quad) {
                int unsignedByte = octet < 0 ? octet + 256 : octet;
                query = unsignedByte + "." + query;
            }
            InetAddress.getByName(query);
            return true;
        } catch (UnknownHostException e) {
            return false;
        }
    }
}

/*
* Way to run the program
* Run commands:
* 1. javac  Pro6SpanCheck.java
* 2. java  Pro6SpanCheck google.com
*
* Output:
* google.com appears legitimate.
*
* */
