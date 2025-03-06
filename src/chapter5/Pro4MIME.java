package chapter5;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Pro4MIME {
    public static void main(String[] args) throws IOException {

        // Guess MIME type of a webpage
        URL url = new URL("https://deerwalk.edu.np");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        String webpageMimeType = connection.getContentType();
        System.out.println("Webpage MIME Type: " + webpageMimeType);
        connection.disconnect();

        // Guess MIME type of a PDF file
        File pdfFile = new File("assignment.pdf");
        String pdfMimeType = URLConnection.guessContentTypeFromName(pdfFile.getName());
        System.out.println("PDF MIME Type: " + pdfMimeType);

        // Guess MIME type of a PNG file
        File pngFile = new File("wallpaper.png");
        String pngMimeType = URLConnection.guessContentTypeFromName(pngFile.getName());
        System.out.println("PNG MIME Type: " + pngMimeType);
    }
}

/*
Output:
Webpage MIME Type: text/html
PDF MIME Type: application/pdf
PNG MIME Type: image/png
 */