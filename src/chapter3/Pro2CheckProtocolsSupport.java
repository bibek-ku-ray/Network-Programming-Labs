package chapter3;

import java.net.MalformedURLException;
import java.net.URL;

public class Pro2CheckProtocolsSupport {
    public static void main(String[] args) {
        String[] protocols = {
                "http", "https", "ftp", "telnet", "mailto", "gopher"
        };
        String host = "tu.edu.np";

        for (String protocol : protocols) {
            try {
                URL url = new URL(protocol, host, "");
                System.out.println(protocol + " is supported.");

            } catch (MalformedURLException e) {
                System.out.println(protocol + " is not supported.");
            }
        }

    }
}

/*
Output:
http is supported.
https is supported.
ftp is supported.
telnet is not supported.
mailto is supported.
gopher is not supported.
 */
