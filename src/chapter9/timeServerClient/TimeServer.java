package chapter9.timeServerClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Set;

public class TimeServer {
    private static final int PORT = 9090;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        try {
            // Create a selector
            Selector selector = Selector.open();

            // Open a server socket channel
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));

            // Register the server socket with the selector
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("Time Server started on port " + PORT);

            // Main server loop
            while (true) {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    keyIterator.remove();

                    if (!key.isValid()) continue;

                    if (key.isAcceptable()) {
                        handleAccept(key, selector);
                    } else if (key.isWritable()) {
                        handleWrite(key);
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

        System.out.println("Client connected: " + clientChannel.getRemoteAddress());

        // Prepare current time data
        String currentTime = LocalDateTime.now().format(formatter);
        ByteBuffer buffer = ByteBuffer.wrap(currentTime.getBytes());

        // Register for write operations
        clientChannel.register(selector, SelectionKey.OP_WRITE, buffer);
    }

    private static void handleWrite(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();

        try {
            clientChannel.write(buffer);

            if (!buffer.hasRemaining()) {
                System.out.println("Time sent to client: " + clientChannel.getRemoteAddress());
                clientChannel.close();
                key.cancel();
            }
        } catch (IOException e) {
            key.cancel();
            clientChannel.close();
            System.out.println("Client disconnected");
        }
    }
}

//  Output
/*
Time Server started on port 9090
Client connected: /127.0.0.1:62921
Time sent to client: /127.0.0.1:62921
 */