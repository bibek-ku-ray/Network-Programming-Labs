package chapter10.dgchatclientserver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ChatClient {
    private static final int SERVER_PORT = 9876;
    private static final String SERVER_HOST = "localhost";

    public static void main(String[] args) {
        try {
            // Open the DatagramChannel
            final DatagramChannel channel = DatagramChannel.open();
            channel.configureBlocking(false); // Non-blocking for receiver
            channel.bind(null); // Let the system pick a port

            final InetSocketAddress serverAddress =
                    new InetSocketAddress(SERVER_HOST, SERVER_PORT);

            // Start a thread to receive messages
            Thread receiverThread = new Thread(() -> {
                ByteBuffer buffer = ByteBuffer.allocate(1024);

                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        buffer.clear();
                        InetSocketAddress sender = (InetSocketAddress) channel.receive(buffer);

                        if (sender != null) {
                            buffer.flip();
                            byte[] data = new byte[buffer.remaining()];
                            buffer.get(data);
                            System.out.println(new String(data));
                        }

                        // Small delay to prevent CPU hogging in non-blocking mode
                        Thread.sleep(100);
                    }
                } catch (IOException | InterruptedException e) {
                    if (!Thread.currentThread().isInterrupted()) {
                        e.printStackTrace();
                    }
                }
            });
            receiverThread.setDaemon(true);
            receiverThread.start();

            // Send an initial message to register with the server
            String initialMessage = "has joined the chat!";
            ByteBuffer buffer = ByteBuffer.wrap(initialMessage.getBytes());
            channel.send(buffer, serverAddress);

            // Read and send user input
            System.out.println("Start chatting (type your messages and press Enter):");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String line;
            while ((line = reader.readLine()) != null) {
                if ("exit".equalsIgnoreCase(line)) {
                    break;
                }

                buffer = ByteBuffer.wrap(line.getBytes());
                channel.send(buffer, serverAddress);
            }

            // Close resources
            receiverThread.interrupt();
            channel.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// output
// Start chatting (type your messages and press Enter):
// hi hello