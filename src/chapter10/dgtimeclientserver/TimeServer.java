package chapter10.dgtimeclientserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeServer {
    private static final int PORT = 9876;

    public static void main(String[] args) {
        try {
            // Open and configure the DatagramChannel
            DatagramChannel channel = DatagramChannel.open();
            channel.socket().bind(new InetSocketAddress(PORT));
            System.out.println("Time Server running on port " + PORT);

            // Create a buffer for receiving data
            ByteBuffer buffer = ByteBuffer.allocate(256);

            while (true) {
                // Clear the buffer for a new receive operation
                buffer.clear();

                // Receive data and get client's address
                SocketAddress clientAddress = channel.receive(buffer);

                if (clientAddress != null) {
                    System.out.println("Request received from " + clientAddress);

                    // Get current time and format it
                    String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                            .format(new Date());

                    // Prepare buffer for sending
                    buffer.clear();
                    buffer.put(currentTime.getBytes());
                    buffer.flip();

                    // Send the time to client
                    channel.send(buffer, clientAddress);
                    System.out.println("Sent time to client: " + currentTime);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Output
/*
Time Server running on port 9876
Request received from /127.0.0.1:61683
Sent time to client: 2025-05-06 08:34:24.068

 */
