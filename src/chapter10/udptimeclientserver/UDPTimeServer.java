package chapter10.udptimeclientserver;

import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;

public class UDPTimeServer {
    public static void main(String[] args) {
        final int PORT = 9876;
        DatagramSocket serverSocket = null;

        try {
            serverSocket = new DatagramSocket(PORT);
            System.out.println("Time Server running on port " + PORT);
            byte[] receiveData = new byte[1024];

            while (true) {
                // Receive request from client
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                // Get client address and port
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // Get current time and format it
                String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                byte[] sendData = currentTime.getBytes();

                // Send time back to client
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
                        clientAddress, clientPort);
                serverSocket.send(sendPacket);
                System.out.println("Sent time to client: " + clientAddress);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        }
    }
}

// Output
// Time Server running on port 9876
// Sent time to client: /127.0.0.1