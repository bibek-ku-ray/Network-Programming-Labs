package chapter3;

import java.net.MalformedURLException;
import java.net.URL;

public class Pro1SplitsURL {
    public static void main(String[] args) throws MalformedURLException {
        String url = " https://www.google.com/search?q=image&tbm=isch&ved=2ahUKEwj827nasvb3AhV-";

        URL url1 = new URL(url);

        System.out.println("Host: " + url1.getHost());
        System.out.println("Path: " + url1.getPath());
        System.out.println("Port: " + url1.getPort());
        System.out.println("Ref: " + url1.getRef());
        System.out.println("Query: " + url1.getQuery());
        System.out.println("Protocol: " + url1.getProtocol());
    }
}

/*
    Output
    Host: www.google.com
    Path: /search
    Port: -1
    Ref: null
    Query: q=image&tbm=isch&ved=2ahUKEwj827nasvb3AhV-
    Protocol: https
* */
