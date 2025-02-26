package chapter4;

import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class Pro2CookieStore {

    public static void main(String[] args) {
        // Create a CookieManager with default CookiePolicy
        CookieManager cookieManager = new CookieManager();
        CookieStore cookieStore = cookieManager.getCookieStore();

        try {
            // Add cookies
            HttpCookie cookie1 = new HttpCookie("UserID", "bibek7");
            HttpCookie cookie2 = new HttpCookie("SessionID", "bibekSession7");
            cookie1.setPath("/");
            cookie2.setPath("/");
            cookie1.setDomain("localhost:3000");
            cookie2.setDomain("localhost:3000");

            cookieStore.add(new URI("http://localhost:3000"), cookie1);
            cookieStore.add(new URI("http://localhost:3000"), cookie2);

            // Get cookies for a specific URI
            List<HttpCookie> cookies = cookieStore.get(new URI("http://localhost:3000"));
            System.out.println("Cookies for http://localhost:3000:");
            for (HttpCookie cookie : cookies) {
                System.out.println(cookie);
            }

            // Get all cookies
            System.out.println("\nAll Cookies:");
            List<HttpCookie> allCookies = cookieStore.getCookies();
            for (HttpCookie cookie : allCookies) {
                System.out.println(cookie);
            }

            // Get all URIs
            System.out.println("\nAll URIs:");
            List<URI> uris = cookieStore.getURIs();
            for (URI uri : uris) {
                System.out.println(uri);
            }

            // Remove a specific cookie
            cookieStore.remove(new URI("http://localhost:3000"), cookie1);
            System.out.println("\nAfter removing UserID cookie:");
            cookies = cookieStore.get(new URI("http://localhost:3000"));
            for (HttpCookie cookie : cookies) {
                System.out.println(cookie);
            }

            // Remove all cookies
            cookieStore.removeAll();
            System.out.println("\nAfter removing all cookies:");
            allCookies = cookieStore.getCookies();
            if (allCookies.isEmpty()) {
                System.out.println("No cookies available.");
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}

/*
Output:
Cookies for http://localhost:3000:
UserID="bibek7";$Path="/";$Domain="localhost:3000"
SessionID="bibekSession7";$Path="/";$Domain="localhost:3000"

All Cookies:
UserID="bibek7";$Path="/";$Domain="localhost:3000"
SessionID="bibekSession7";$Path="/";$Domain="localhost:3000"

All URIs:
http://localhost

After removing UserID cookie:
SessionID="bibekSession7";$Path="/";$Domain="localhost:3000"

After removing all cookies:
No cookies available.
 */

