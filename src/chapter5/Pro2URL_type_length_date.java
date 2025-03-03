package chapter5;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class Pro2URL_type_length_date {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://deerwalk.edu.np");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Get content type
            String contentType = connection.getContentType();
            System.out.println("Content Type: " + contentType);

            // Get content length
            int contentLength = connection.getContentLength();
            System.out.println("Content Length: " + contentLength);

            // Get last modified date
            long lastModified = connection.getLastModified();
            System.out.println("Last Modified: " + new Date(lastModified));

            // Get access date (current date)
            Date accessDate = new Date();
            System.out.println("Access Date: " + accessDate);

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
Output:
Content Type: text/html
Content Length: -1
Last Modified: Thu Jan 02 14:15:17 NPT 2025
Access Date: Fri Feb 28 10:40:57 NPT 2025
 */