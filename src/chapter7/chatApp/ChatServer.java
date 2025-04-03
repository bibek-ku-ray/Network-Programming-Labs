package chapter7.chatApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatServer {
    private static final int PORT = 12345;
    private static Set<ClientHandler> clients = new HashSet<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Chat Server is listening on port " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected");
                ClientHandler client = new ClientHandler(clientSocket);
                clients.add(client);
                new Thread(client).start();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void broadcastMessage(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    public static void removeClient(ClientHandler client) {
        clients.remove(client);
    }

    private static class ClientHandler implements Runnable {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private String clientName;

        public ClientHandler(Socket socket) {
            this.socket = socket;
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException ex) {
                System.out.println("ClientHandler exception: " + ex.getMessage());
                ex.printStackTrace();
                closeConnections();
            }
        }

        @Override
        public void run() {
            try {
                clientName = in.readLine();
                System.out.println(clientName + " has joined the chat");
                broadcastMessage(clientName + " has joined the chat", null);
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Received from " + clientName + ": " + message);
                    broadcastMessage(clientName + ": " + message, this);
                }
            } catch (IOException ex) {
                System.out.println("ClientHandler exception: " + ex.getMessage());
                ex.printStackTrace();
            } finally {
                closeConnections();
                broadcastMessage(clientName + " has left the chat", null);
                removeClient(this);
            }
        }

        public void sendMessage(String message) {
            out.println(message);
        }

        private void closeConnections() {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ex) {
                System.out.println("ClientHandler exception: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}

// Output
/*
Chat Server is listening on port 12345
New client connected
Bibek has joined the chat
Received from Bibek: hi there
New client connected
\Spiderman has joined the chat
Received from \Spiderman: pider here
 */
