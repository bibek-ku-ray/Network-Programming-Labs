package chapter6;

/*
 * WAP to create a client application that reads content from server using socket.
 * WAP to create a client application that writes content to the server using socket. [use time.nist.gov at port 13]
 * WAP to find out which of the ports from 7000 to 9000 are hosting servers on localhost.
 * WAP to implement a WHOIS client as a java swing application.
 */
import java.io.*;
import java.net.*;


public class Pro1SocketToReadContentFromServer {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int port = 3000;
        try (
                Socket socket = new Socket(serverAddress, port);

                OutputStream outputStream = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(outputStream, true);
                InputStream inputStream = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        ) {
            String httpRequest = "GET /users HTTP/1.1\r\nHost: localhost\r\n\r\n";
            writer.println(httpRequest);

            String serverMessage;
            System.out.println("Response from server:");
            while ((serverMessage = reader.readLine()) != null) {
                System.out.println(serverMessage);
            }

        } catch (IOException e) {
            System.err.println("Error connecting to the server: " + e.getMessage());
        }
    }
}

// Output
/*
 * Response from server:
 * HTTP/1.1 200 OK
 * X-Powered-By: Express
 * Content-Type: application/json; charset=utf-8
 * Content-Length: 29
 * ETag: W/"1d-i5BgKlr5AR31NquXlfw0zwrh8dQ"
 * Date: Thu, 03 Apr 2025 03:35:48 GMT
 * Connection: keep-alive
 * Keep-Alive: timeout=5
 *
 * [{"id":1,"name":"John Cena"}]
 */
