package chapter6;

import java.io.*;
import java.net.*;

public class Pro2WriteServer {
    public static void main(String[] args) {
        String serverAddress = "time.nist.gov";
        int port = 13;

        try (
                Socket socket = new Socket(serverAddress, port);

                InputStream inputStream = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        ) {
            String serverMessage;
            System.out.println("Response from server:");
            while ((serverMessage = reader.readLine()) != null) {
                System.out.println(serverMessage);
            }

        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + serverAddress);
        } catch (IOException e) {
            System.err.println("Error connecting to the server: " + e.getMessage());
        }
    }
}

// output
/**
 *
 Response from server:

 60768 25-04-03 03:47:25 50 0 0 339.6 UTC(NIST) *
 */
