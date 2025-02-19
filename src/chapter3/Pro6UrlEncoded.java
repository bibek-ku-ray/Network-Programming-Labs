package chapter3;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Pro6UrlEncoded {
    public static void main(String[] args) {
        String baseUrl = "https://www.deerwalk.edu.np/?q=";
        String query = "info@gmail for bca";

        // Display URL without encoding
        System.out.println("URL without encoding:");
        System.out.println(baseUrl + query); // Directly print the unencoded string

        // Display URL after encoding
        System.out.println("\nURL after encoding:");
        try {
            String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
            URL url = new URL(baseUrl + encodedQuery);
            System.out.println(url);
        } catch (MalformedURLException e) {
            System.err.println("Malformed URL after encoding: " + e.getMessage());
        }
    }
}

/*
Output:
URL without encoding:
https://www.deerwalk.edu.np/?q=info@gmail for bca

URL after encoding:
https://www.deerwalk.edu.np/?q=info%40gmail+for+bca
 */
