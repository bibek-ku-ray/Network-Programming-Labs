package chapter4;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Pro1CheckAllowedMethod {

    public static void main(String[] args) {
        checkHttpMethods("https://deerwalk.edu.np/");
        checkHttpMethods("http://localhost:3000/health-check");
    }

    public static void checkHttpMethods(String urlString) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("OPTIONS");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String allowHeader = connection.getHeaderField("Allow");
                System.out.println("URL: " + urlString);
                if (allowHeader != null) {
                    System.out.println("Supported HTTP Methods: " + allowHeader);
                } else {
                    System.out.println("No 'Allow' header found in the response.");
                }
            } else {
                System.out.println("URL: " + urlString);
                System.out.println("Failed to retrieve methods. HTTP Response Code: " + responseCode);
            }
        } catch (IOException e) {
            System.err.println("Error checking URL: " + urlString);
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}

/*
Output:
URL: https://deerwalk.edu.np/
Supported HTTP Methods: GET,POST,OPTIONS,HEAD
URL: http://localhost:3000/health-check
Supported HTTP Methods: GET,HEAD

 */

