package chapter9.chatServerClientShorter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ChatServer {
    private static final int PORT = 9090;
    private static final int BUFFER_SIZE = 1024;

    private static final Map<SocketChannel, String> clients = new HashMap<>();
    private static final ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

    public static void main(String[] args) {
        try (ServerSocketChannel serverChannel = ServerSocketChannel.open()) {
            serverChannel.bind(new InetSocketAddress(PORT));
            System.out.println("Chat server started on port " + PORT);

            while (true) {
                SocketChannel client = serverChannel.accept();
                if (client != null) {
                    handleClient(client);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(SocketChannel client) throws IOException {
        client.write(ByteBuffer.wrap("Enter your name: ".getBytes()));
        String name = readMessage(client).trim();
        clients.put(client, name);
        System.out.println(name + " connected.");

        broadcast(client, "*** " + name + " joined the chat ***");

        while (true) {
            String msg = readMessage(client);
            if (msg == null) {
                disconnect(client);
                break;
            }
            broadcast(client, name + ": " + msg);
        }
    }

    private static String readMessage(SocketChannel client) throws IOException {
        buffer.clear();
        int bytesRead = client.read(buffer);
        if (bytesRead <= 0) return null;
        buffer.flip();
        return StandardCharsets.UTF_8.decode(buffer).toString();
    }

    private static void broadcast(SocketChannel sender, String message) throws IOException {
        for (SocketChannel client : clients.keySet()) {
            if (client != sender && client.isOpen()) {
                client.write(ByteBuffer.wrap((message + "\n").getBytes()));
            }
        }
    }

    private static void disconnect(SocketChannel client) throws IOException {
        String name = clients.get(client);
        clients.remove(client);
        client.close();
        System.out.println(name + " disconnected.");
        broadcast(client, "*** " + name + " left the chat ***");
    }
}

// output
// Chat server started on port 9090
// bibek here connected.

