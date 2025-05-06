package chapter10.dgtimeclientserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class TimeClient {
    private static final int PORT = 9876;
    private static final String SERVER_HOST = "localhost";

    public static void main(String[] args) {
        try {
            // Open the DatagramChannel
            DatagramChannel channel = DatagramChannel.open();

            // Create server address
            InetSocketAddress serverAddress = new InetSocketAddress(SERVER_HOST, PORT);

            // Create and prepare buffer for sending request
            ByteBuffer sendBuffer = ByteBuffer.allocate(1);
            sendBuffer.put((byte) 1); // Just a simple request byte
            sendBuffer.flip();

            // Send the request
            channel.send(sendBuffer, serverAddress);
            System.out.println("Sent request to server");

            // Create buffer for receiving response
            ByteBuffer receiveBuffer = ByteBuffer.allocate(256);

            // Receive the response
            channel.receive(receiveBuffer);
            receiveBuffer.flip();

            // Convert and display the time
            byte[] bytes = new byte[receiveBuffer.remaining()];
            receiveBuffer.get(bytes);
            String time = new String(bytes);

            System.out.println("Current time from server: " + time);

            // Close the channel
            channel.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Output
//Sent request to server
//Current time from server: 2025-05-06 08:34:24.068
