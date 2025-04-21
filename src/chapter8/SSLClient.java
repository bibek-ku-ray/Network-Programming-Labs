//package chapter8;
//
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.security.KeyStore;
//
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.SSLSocket;
//import javax.net.ssl.SSLSocketFactory;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.TrustManagerFactory;
//
//public class SSLClient {
//
//    private static final String HOST = "localhost";
//    private static final int PORT = 8888;
//    private static final String TRUSTSTORE_PATH = "D:\\College\\SEM-VI\\NP\\NP_Labs\\clientkeystore";
//    private static final String TRUSTSTORE_PASSWORD = "bibek123";
//
//    public static void main(String[] args) {
//        try {
//            // Load the truststore
//            char[] truststorePassword = TRUSTSTORE_PASSWORD.toCharArray();
//            KeyStore truststore = KeyStore.getInstance(KeyStore.getDefaultType());
//            FileInputStream fis = new FileInputStream(TRUSTSTORE_PATH);
//            truststore.load(fis, truststorePassword);
//
//            // Create the trust manager
//            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//            trustManagerFactory.init(truststore);
//            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
//
//            // Create the SSL context
//            SSLContext sslContext = SSLContext.getInstance("TLS");
//            sslContext.init(null, trustManagers, null);
//
//            // Create the SSL socket factory
//            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
//
//            // Create the client socket
//            System.out.println("Connecting to server...");
//            SSLSocket clientSocket = (SSLSocket) sslSocketFactory.createSocket(HOST, PORT);
//            System.out.println("Connected to server.");
//
//            // Send request to server
//            String request = "Hello from client!";
//            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
//            writer.println(request);
//            System.out.println("Sent request: " + request);
//
//            // Read response from server
//            try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
//                String response = reader.readLine();
//                System.out.println("Received response: " + response);
//            }
//
//                // Close the client socket
//            clientSocket.close();
//        } catch (Exception e) {
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
import java.security.SecureRandom;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

public class SSLClient {

    private static final String HOST = "localhost";
    private static final int PORT = 8888;
    private static final String TRUSTSTORE_PATH = "D:\\College\\SEM-VI\\NP\\NP_Labs\\clientkeystore";
    private static final String TRUSTSTORE_PASSWORD = "bibek123";

    public static void main(String[] args) {
        SSLSocket clientSocket = null;

        try {
            // For testing only - create a trust manager that doesn't validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() { return null; }
                    public void checkClientTrusted(X509Certificate[] certs, String authType) { }
                    public void checkServerTrusted(X509Certificate[] certs, String authType) { }
                }
            };

            // Create SSL context with our trust-all manager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());

            // Create the socket factory and socket
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            // Connect to server
            System.out.println("Connecting to server...");
            clientSocket = (SSLSocket) sslSocketFactory.createSocket(HOST, PORT);

            // Explicitly start handshake
            clientSocket.startHandshake();
            System.out.println("Connected to server with successful SSL handshake.");

            // Now we can communicate
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Send a request
            String request = "Hello from client!";
            writer.println(request);
            System.out.println("Sent request: " + request);

            // Read response
            String response = reader.readLine();
            System.out.println("Received response: " + response);

            // Clean up
            writer.close();
            reader.close();
            clientSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// output
/*\
Connecting to server...
Connected to server with successful SSL handshake.
Sent request: Hello from client!
Received response: Hello from server!

 */