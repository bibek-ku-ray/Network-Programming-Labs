package chapter7.EchoClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) {
        int port = 12345; // Port to listen on
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Echo Server is listening on port " + port);
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    OutputStream output = clientSocket.getOutputStream();
                    PrintWriter writer = new PrintWriter(output, true);

                    String message;
                    while ((message = input.readLine()) != null) {
                        System.out.println("Received: " + message);
                        writer.println("Echo: " + message);
                        if (message.equalsIgnoreCase("exit")) {
                            break;
                        }
                    }
                } catch (IOException ex) {
                    System.out.println("Server exception: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

// output
/*
Echo Server is listening on port 12345
Received: hi there
 */

