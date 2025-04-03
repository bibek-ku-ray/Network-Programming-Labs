package chapter7.DayTimeClientServer;

/*
Implement a DayTime client-server application
Implement a echo client - server application where the client sends some text and server relays back the same content.
Implement a chat client - server applications.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class DayTimeClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 13; // Default port for DayTime Protocol
        try (Socket socket = new Socket(hostname, port)) {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String dateTime = input.readLine();
            System.out.println("Received date and time from server: " + dateTime);
        } catch (IOException ex) {
            System.out.println("Client exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

//output
// Received date and time from server: 2025-04-03 10:04:17
