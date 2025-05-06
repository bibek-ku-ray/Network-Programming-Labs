package chapter10.dgchatclientserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.HashSet;
import java.util.Set;

public class ChatServer {
    private static final int PORT = 9876;
    private static final Set<SocketAddress> clients = new HashSet<>();

    public static void main(String[] args) {
        try {
            // Open and configure the DatagramChannel
            DatagramChannel channel = DatagramChannel.open();
            channel.bind(new InetSocketAddress(PORT));
            channel.configureBlocking(true); // Using blocking mode for simplicity

            System.out.println("Chat Server running on port " + PORT);
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (true) {
                // Clear the buffer for a new receive operation
                buffer.clear();

                // Receive data and get client's address
                SocketAddress clientAddress = channel.receive(buffer);

                if (clientAddress != null) {
                    // Add client to the list if new
                    if (!clients.contains(clientAddress)) {
                        clients.add(clientAddress);
                        System.out.println("New client connected: " + clientAddress);
                    }

                    // Prepare message for broadcast
                    buffer.flip();
                    byte[] data = new byte[buffer.remaining()];
                    buffer.get(data);
                    String message = new String(data);

                    // Format message with sender information
                    String broadcastMsg = clientAddress + ": " + message;
                    System.out.println("Received: " + broadcastMsg);

                    // Broadcast to all clients except sender
                    ByteBuffer sendBuffer = ByteBuffer.wrap(broadcastMsg.getBytes());
                    for (SocketAddress client : clients) {
                        if (!client.equals(clientAddress)) {
                            sendBuffer.rewind();
                            channel.send(sendBuffer, client);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


// output
//Chat Server running on port 9876
//New client connected: /127.0.0.1:65280
//Received: /127.0.0.1:65280: has joined the chat!
//Received: /127.0.0.1:65280: hi hello