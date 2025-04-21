package chapter9.timeServerClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class TimeClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 9090;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try {
            // Open a socket channel
            SocketChannel socketChannel = SocketChannel.open();
            System.out.println("Connecting to server...");

            // Connect to the server
            socketChannel.connect(new InetSocketAddress(SERVER_HOST, SERVER_PORT));

            // Wait for connection to complete
            while (!socketChannel.finishConnect()) {
                Thread.sleep(100);
            }

            System.out.println("Connected to server");

            // Create buffer for reading data
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

            // Read data from server
            int bytesRead = socketChannel.read(buffer);

            if (bytesRead > 0) {
                buffer.flip();
                String time = StandardCharsets.UTF_8.decode(buffer).toString();
                System.out.println("Current time from server: " + time);
            } else {
                System.out.println("No data received from server");
            }

            // Close the channel
            socketChannel.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// output
/*
Connecting to server...
Connected to server
Current time from server: 2025-04-21 10:02:51
 */