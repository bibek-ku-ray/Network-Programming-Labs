package chapter9.chatServerClientShorter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ChatClient {
    private static final String HOST = "localhost";
    private static final int PORT = 9090;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try (SocketChannel client = SocketChannel.open(new InetSocketAddress(HOST, PORT))) {
            Scanner scanner = new Scanner(System.in);
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

            while (true) {
                // Read server messages
                buffer.clear();
                int bytesRead = client.read(buffer);
                if (bytesRead > 0) {
                    buffer.flip();
                    System.out.print(StandardCharsets.UTF_8.decode(buffer));
                }

                if (scanner.hasNextLine()) {
                    String msg = scanner.nextLine();
                    if (msg.equalsIgnoreCase("/quit")) break;
                    client.write(ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8)));
                }
            }
        } catch (IOException e) {
            System.out.println("Disconnected.");
        }
    }
}

// output
// Enter your name: bibek here