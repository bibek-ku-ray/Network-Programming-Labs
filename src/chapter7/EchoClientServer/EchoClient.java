package chapter7.EchoClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 12345; // Port to connect to
        try (Socket socket = new Socket(hostname, port)) {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String message;
            System.out.println("Type messages to send to the server");
            while ((message = userInput.readLine()) != null) {
                writer.println(message);
                String echo = input.readLine();
                System.out.println(echo);
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
            }
        } catch (IOException ex) {
            System.out.println("Client exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

// output
/*
Type messages to send to the server
hi there
Echo: hi there
 */

