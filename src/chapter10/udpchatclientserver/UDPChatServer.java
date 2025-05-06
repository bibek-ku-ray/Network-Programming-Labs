package chapter10.udpchatclientserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class UDPChatServer {
    private static final int PORT = 9876;
    private static List<ClientInfo> clients = new ArrayList<>();

    // Class to store client information
    private static class ClientInfo {
        private InetAddress address;
        private int port;

        public ClientInfo(InetAddress address, int port) {
            this.address = address;
            this.port = port;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof ClientInfo) {
                ClientInfo other = (ClientInfo) obj;
                return address.equals(other.address) && port == other.port;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        DatagramSocket serverSocket = null;

        try {
            serverSocket = new DatagramSocket(PORT);
            System.out.println("Chat Server running on port " + PORT);
            byte[] receiveData = new byte[1024];

            while (true) {
                // Receive message
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                // Get client info
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                ClientInfo sender = new ClientInfo(clientAddress, clientPort);

                // Add client if new
                if (!clients.contains(sender)) {
                    clients.add(sender);
                    System.out.println("New client connected: " + clientAddress + ":" + clientPort);
                }

                // Get received message
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received from " + clientAddress + ":" + clientPort + " - " + message);

                // Format message with sender's address
                String broadcastMessage = clientAddress.getHostAddress() + ":" + clientPort + " says: " + message;
                byte[] sendData = broadcastMessage.getBytes();

                // Broadcast message to all clients except sender
                for (ClientInfo client : clients) {
                    if (!client.equals(sender)) {
                        DatagramPacket sendPacket = new DatagramPacket(
                                sendData, sendData.length, client.address, client.port);
                        serverSocket.send(sendPacket);
                    }
                }
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
/*
Chat Server running on port 9876
New client connected: /127.0.0.1:52013
Received from /127.0.0.1:52013 - Hello, I've joined the chat!
Received from /127.0.0.1:52013 - hello cello

 */
