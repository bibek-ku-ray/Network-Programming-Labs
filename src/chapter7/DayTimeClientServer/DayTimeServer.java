package chapter7.DayTimeClientServer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DayTimeServer {
    public static void main(String[] args) {
        int port = 13; // Default port for DayTime Protocol
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("DayTime Server is listening on port " + port);
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    OutputStream output = clientSocket.getOutputStream();
                    PrintWriter writer = new PrintWriter(output, true);
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String dateTime = now.format(formatter);
                    writer.println(dateTime);
                    System.out.println("Sent date and time to client: " + dateTime);
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
DayTime Server is listening on port 13
Sent date and time to client: 2025-04-03 10:04:17
 */

