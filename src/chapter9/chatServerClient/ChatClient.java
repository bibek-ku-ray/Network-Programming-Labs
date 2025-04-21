package chapter9.chatServerClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 9090;
    private static final int BUFFER_SIZE = 1024;

    private static SocketChannel socketChannel;
    private static ByteBuffer readBuffer = ByteBuffer.allocate(BUFFER_SIZE);
    private static boolean isRunning = true;

    public static void main(String[] args) {
        try {
            // Open socket channel and selector
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            Selector selector = Selector.open();

            // Connect to the server
            socketChannel.connect(new InetSocketAddress(SERVER_HOST, SERVER_PORT));
            socketChannel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ);

            System.out.println("Connecting to chat server...");

            // Create thread for user input
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(() -> handleUserInput());

            // Main client event loop
            while (isRunning) {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    keyIterator.remove();

                    if (!key.isValid()) continue;

                    if (key.isConnectable()) {
                        handleConnect(key);
                    } else if (key.isReadable()) {
                        handleRead(key);
                    }
                }
            }

            executor.shutdown();
            socketChannel.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleConnect(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();

        // Complete connection
        if (channel.isConnectionPending()) {
            channel.finishConnect();
        }

        System.out.println("Connected to the chat server");
    }

    private static void handleRead(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        readBuffer.clear();

        int bytesRead = channel.read(readBuffer);

        if (bytesRead == -1) {
            System.out.println("Server disconnected");
            isRunning = false;
            key.cancel();
            channel.close();
            return;
        }

        if (bytesRead > 0) {
            readBuffer.flip();
            String message = StandardCharsets.UTF_8.decode(readBuffer).toString();
            System.out.print(message);
        }
    }

    private static void handleUserInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (isRunning) {
                String message = scanner.nextLine();

                if (message.equalsIgnoreCase("/quit")) {
                    isRunning = false;
                    System.out.println("Disconnecting from server...");
                    break;
                }

                // Send message to server
                ByteBuffer buffer = ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8));
                socketChannel.write(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// output
/*
Connecting to chat server...
Connected to the chat server
Welcome to the chat server! You are connected as Guest-/127.0.0.1:62978
Type your name to register: Bibek
You are now registered as: Bibek
 */
