package chapter4;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.URI;

public class Pro3CookiePolicy {
    public static void main(String[] args) {
        CookiePolicy eduNpPolicy = new CookiePolicy() {
            @Override
            public boolean shouldAccept(URI uri, HttpCookie cookie) {
                String domain = cookie.getDomain();
                if (domain == null) {
                    return false;
                }
                return domain.endsWith(".edu.np");
            }
        };

        CookieManager cookieManager = new CookieManager(null, eduNpPolicy);
        CookieHandler.setDefault(cookieManager);

        try {
            URI testUri = new URI("https://deerwalk.edu.np");

            HttpCookie allowedCookie = new HttpCookie("session", "12345");
            allowedCookie.setDomain("tu.edu.np");

            HttpCookie blockedCookie = new HttpCookie("session", "67890");
            blockedCookie.setDomain("http://localhost:3000");

            System.out.println("Allowed cookie accepted? " + eduNpPolicy.shouldAccept(testUri, allowedCookie));
            System.out.println("Blocked cookie accepted? " + eduNpPolicy.shouldAccept(testUri, blockedCookie));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
Output:
Allowed cookie accepted? true
Blocked cookie accepted? false
 */
