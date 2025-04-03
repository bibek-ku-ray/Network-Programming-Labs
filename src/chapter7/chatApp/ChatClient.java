package chapter7.chatApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 12345;
        try (Socket socket = new Socket(hostname, port)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            OutputStream out = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out, true);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter your name: ");
            String clientName = userInput.readLine();
            writer.println(clientName);

            // Thread to read messages from the server
            new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (IOException ex) {
                    System.out.println("Client exception: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }).start();

            // Read messages from user and send to the server
            String message;
            System.out.println("Type messages to send to the chat (type 'exit' to quit):");
            while ((message = userInput.readLine()) != null) {
                writer.println(message);
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
Enter your name: Bibek
Type messages to send to the chat (type 'exit' to quit):
Bibek has joined the chat
hi there
\Spiderman has joined the chat
\Spiderman: pider here

Enter your name: Spiderman
Type messages to send to the chat (type 'exit' to quit):
\Spiderman has joined the chat
pider here
 */

