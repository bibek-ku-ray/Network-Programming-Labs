package chapter5;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.security.Permission;

public class Pro3SecurityPermission {
    public static void main(String[] args) {
        String[] urls = {
                "https://deerwalk.edu.np/DWIT/admission.php",
                "https://deerwalk.edu.np/"
        };

        for (String urlString : urls) {
            try {
                URL url = new URL(urlString);
                URLConnection connection = url.openConnection();
                connection.connect();

                Permission permission = connection.getPermission();
                System.out.println("URL: " + urlString);
                System.out.println("Permission: " + permission);
                System.out.println();

                connection.getInputStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

/*
Output:
URL: https://deerwalk.edu.np/DWIT/admission.php
Permission: ("java.net.SocketPermission" "deerwalk.edu.np:80" "connect,resolve")

URL: https://deerwalk.edu.np/
Permission: ("java.net.SocketPermission" "deerwalk.edu.np:80" "connect,resolve")
 */