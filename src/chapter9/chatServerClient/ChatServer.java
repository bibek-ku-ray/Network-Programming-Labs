package chapter9.chatServerClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ChatServer {
    private static final int PORT = 9090;
    private static final int BUFFER_SIZE = 1024;

    // Map to store client channels and their names
    private static final Map<SocketChannel, String> clients = new HashMap<>();
    private static final ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

    public static void main(String[] args) {
        try {
            // Create selector and server channel
            Selector selector = Selector.open();
            ServerSocketChannel serverChannel = ServerSocketChannel.open();

            // Configure non-blocking mode
            serverChannel.configureBlocking(false);
            serverChannel.socket().bind(new InetSocketAddress(PORT));

            // Register server channel for accept operations
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("Chat Server started on port " + PORT);

            // Main server loop
            while (true) {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    keyIterator.remove();

                    if (!key.isValid()) continue;

                    // Handle events
                    if (key.isAcceptable()) {
                        handleAccept(key, selector);
                    } else if (key.isReadable()) {
                        handleRead(key, selector);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleAccept(SelectionKey key, Selector selector) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel = serverChannel.accept();
        clientChannel.configureBlocking(false);

        // Register the client for reading
        clientChannel.register(selector, SelectionKey.OP_READ);

        // Add to client list with temporary name
        String clientAddress = clientChannel.getRemoteAddress().toString();
        clients.put(clientChannel, "Guest-" + clientAddress);

        System.out.println("New client connected: " + clientAddress);

        // Send welcome message to the new client
        String welcomeMsg = "Welcome to the chat server! You are connected as Guest-" + clientAddress +
                           "\nType your name to register: ";
        clientChannel.write(ByteBuffer.wrap(welcomeMsg.getBytes(StandardCharsets.UTF_8)));

        // Notify other clients
        broadcastMessage(clientChannel, "*** New user Guest-" + clientAddress + " joined the chat ***");
    }

    private static void handleRead(SelectionKey key, Selector selector) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        buffer.clear();

        try {
            int bytesRead = clientChannel.read(buffer);

            if (bytesRead == -1) {
                // Client disconnected
                handleDisconnect(clientChannel);
                return;
            }

            if (bytesRead > 0) {
                buffer.flip();
                String message = StandardCharsets.UTF_8.decode(buffer).toString().trim();

                // Process the message
                processMessage(clientChannel, message);
            }
        } catch (IOException e) {
            // Handle disconnection
            handleDisconnect(clientChannel);
        }
    }

    private static void processMessage(SocketChannel sender, String message) throws IOException {
        String senderName = clients.get(sender);

        // Check if this is a name registration message
        if (senderName.startsWith("Guest-")) {
            // Register the user with their chosen name
            String newName = message;

            // Notify about name change
            broadcastMessage(sender, "*** " + senderName + " is now known as " + newName + " ***");

            // Update client name
            clients.put(sender, newName);

            // Confirm to the client
            String confirmMsg = "You are now registered as: " + newName + "\n";
            sender.write(ByteBuffer.wrap(confirmMsg.getBytes(StandardCharsets.UTF_8)));

        } else {
            // Normal chat message - broadcast to all clients
            broadcastMessage(sender, senderName + ": " + message);
        }
    }

    private static void broadcastMessage(SocketChannel sender, String message) throws IOException {
        ByteBuffer msgBuffer = ByteBuffer.wrap((message + "\n").getBytes(StandardCharsets.UTF_8));

        for (SocketChannel client : clients.keySet()) {
            if (client != sender) {
                // Create a duplicate of the buffer for each client
                msgBuffer.rewind();
                client.write(msgBuffer);
            }
        }
    }

    private static void handleDisconnect(SocketChannel clientChannel) throws IOException {
        String clientName = clients.get(clientChannel);
        System.out.println("Client disconnected: " + clientName);

        // Broadcast disconnect message
        if (clientName != null) {
            broadcastMessage(clientChannel, "*** " + clientName + " has left the chat ***");
        }

        // Remove client
        clients.remove(clientChannel);
        clientChannel.close();
    }
}

// output
/*
Chat Server started on port 9090
New client connected: /127.0.0.1:62978
 */
