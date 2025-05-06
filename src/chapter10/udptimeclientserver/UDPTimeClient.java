package chapter10.udptimeclientserver;

import java.net.*;
import java.io.IOException;

public class UDPTimeClient {
    public static void main(String[] args) {
        final int PORT = 9876;
        final String SERVER_IP = "127.0.0.1"; // Use server's IP address

        DatagramSocket clientSocket = null;

        try {
            clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName(SERVER_IP);

            // Prepare request data (just a dummy request)
            String request = "TIME";
            byte[] sendData = request.getBytes();

            // Send request to server
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
                    serverAddress, PORT);
            clientSocket.send(sendPacket);

            // Receive response from server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            // Display server's response
            String timeFromServer = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Current time from server: " + timeFromServer);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close();
            }
        }
    }
}

// Output
// Current time from server: 2025-05-06 08:09:50