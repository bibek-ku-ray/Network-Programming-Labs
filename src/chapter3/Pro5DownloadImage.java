package chapter3;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Pro5DownloadImage {
    public static void main(String[] args) throws IOException {

            URL imageUrl = new URL("https://www.deerwalk.edu.np/images/deg-logo.png");

            // Open a connection to the URL
            URLConnection connection = imageUrl.openConnection();

            // Get the input stream from the connection
            InputStream inputStream = connection.getInputStream();

            // Create an output stream to save the image to a file
            FileOutputStream outputStream = new FileOutputStream("deg-logo.png");

            // Buffer to hold data while reading from the input stream
            byte[] buffer = new byte[4096];
            int bytesRead;

            // Read the input stream and write to the output stream
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // Close the streams
            inputStream.close();
            outputStream.close();

            System.out.println("Image downloaded successfully!");


    }
}

/*
Output:
Image downloaded successfully!
 */
