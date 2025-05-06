package chapter10.udpchatclientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPChatClient {
    private static final int SERVER_PORT = 9876;
    private static final String SERVER_IP = "127.0.0.1"; // Change to server IP if not local

    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName(SERVER_IP);
            final DatagramSocket finalSocket = socket;

            // Start a thread to receive messages
            Thread receiverThread = new Thread(() -> {
                try {
                    byte[] receiveData = new byte[1024];
                    while (true) {
                        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                        finalSocket.receive(receivePacket);
                        String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    if (!finalSocket.isClosed()) {
                        e.printStackTrace();
                    }
                }
            });
            receiverThread.start();

            // Send an initial message to register with the server
            String initialMessage = "Hello, I've joined the chat!";
            byte[] initialData = initialMessage.getBytes();
            DatagramPacket initialPacket = new DatagramPacket(
                    initialData, initialData.length, serverAddress, SERVER_PORT);
            socket.send(initialPacket);

            // Read and send user input
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Start chatting (type messages and press Enter):");

            String line;
            while ((line = reader.readLine()) != null) {
                if ("exit".equalsIgnoreCase(line)) {
                    break;
                }

                byte[] sendData = line.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(
                        sendData, sendData.length, serverAddress, SERVER_PORT);
                socket.send(sendPacket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}

// Output
/*
    Start chatting (type messages and press Enter):
    hello cello
 */
