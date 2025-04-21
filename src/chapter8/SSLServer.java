//package chapter8;
//
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.security.KeyStore;
//
//import javax.net.ssl.KeyManager;
//import javax.net.ssl.KeyManagerFactory;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.SSLServerSocket;
//import javax.net.ssl.SSLServerSocketFactory;
//import javax.net.ssl.SSLSocket;
//
//public class SSLServer {
//
//    private static final int PORT = 8888;
//    private static final String KEYSTORE_PATH = "D:\\College\\SEM-VI\\NP\\NP_Labs\\server.keystore";
//    private static final String KEYSTORE_PASSWORD = "bibek123";
//
//    public static void main(String[] args) {
//        try {
//            // Load the keystore
//            char[] keystorePassword = KEYSTORE_PASSWORD.toCharArray();
//            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
//            FileInputStream fis = new FileInputStream(KEYSTORE_PATH);
//            keystore.load(fis, keystorePassword);
//
//            // Create the key manager
//            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
//            keyManagerFactory.init(keystore, keystorePassword);
//            KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();
//
//            // Create the SSL context
//            SSLContext sslContext = SSLContext.getInstance("TLS");
//            sslContext.init(keyManagers, null, null);
//
//            // Create the SSL server socket factory
//            SSLServerSocketFactory sslServerSocketFactory = sslContext.getServerSocketFactory();
//
//            // Create the server socket
//            SSLServerSocket serverSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(PORT);
//
//            System.out.println("Server started. Listening on port " + PORT);
//
//            while (true) {
//                // Accept client connection
//                SSLSocket clientSocket = (SSLSocket) serverSocket.accept();
//
//                // Handle client request
//                handleClientRequest(clientSocket);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void handleClientRequest(SSLSocket clientSocket) {
//        try {
//            // Read client request
//            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//            String request = reader.readLine();
//
//            System.out.println("Received request: " + request);
//
//            // Process the request
//            String response = "Hello from server!";
//
//            // Send response to client
//            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
//            writer.println(response);
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//                System.err.println("Thread interrupted: " + e.getMessage());
//            }
//
//            // Close the client socket
//            clientSocket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
package chapter8;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.KeyStore;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class SSLServer {

    private static final int PORT = 8888;
    private static final String KEYSTORE_PATH = "D:\\College\\SEM-VI\\NP\\NP_Labs\\server.keystore";
    private static final String KEYSTORE_PASSWORD = "bibek123";

    public static void main(String[] args) {
        try {
            // Load the keystore
            char[] keystorePassword = KEYSTORE_PASSWORD.toCharArray();
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            try (FileInputStream fis = new FileInputStream(KEYSTORE_PATH)) {
                keystore.load(fis, keystorePassword);
            }

            // Create the key manager
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keystore, keystorePassword);
            KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();

            // Create the SSL context
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(keyManagers, null, null);

            // Create the SSL server socket factory
            SSLServerSocketFactory sslServerSocketFactory = sslContext.getServerSocketFactory();

            // Create the server socket
            SSLServerSocket serverSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(PORT);
            serverSocket.setNeedClientAuth(false); // Don't require client authentication
            serverSocket.setEnableSessionCreation(true);

            System.out.println("Server started. Listening on port " + PORT);

            while (true) {
                // Accept client connection
                SSLSocket clientSocket = (SSLSocket) serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Handle client request in a separate thread
                new Thread(() -> handleClientRequest(clientSocket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleClientRequest(SSLSocket clientSocket) {
        try {
            // Ensure handshake is complete
            clientSocket.startHandshake();
            System.out.println("SSL handshake successful with client: " + clientSocket.getInetAddress());

            // Set up I/O
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            // Read the request
            String request = reader.readLine();
            System.out.println("Received request: " + request);

            // Send response
            String response = "Hello from server!";
            writer.println(response);
            writer.flush();
            System.out.println("Sent response: " + response);

            // Clean up
            Thread.sleep(100); // Short delay to ensure data is sent
            reader.close();
            writer.close();
            clientSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// output
/*
Server started. Listening on port 8888
Client connected: /127.0.0.1
SSL handshake successful with client: /127.0.0.1
Received request: Hello from client!
Sent response: Hello from server!
 */