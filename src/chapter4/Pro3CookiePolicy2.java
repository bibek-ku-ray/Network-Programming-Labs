package chapter4;

import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pro3CookiePolicy2 {

    public static void main(String[] args) throws Exception {
        String uri = "https://deerwalk.edu.np";
        String uri1 = "http://localhost:3000/posts";

        CookiePolicy customPolicy = new CookiePolicy() {
            @Override
            public boolean shouldAccept(URI uri, HttpCookie cookie) {
                String domain = cookie.getDomain();
                return domain != null && domain.endsWith(".edu.np");
            }
        };

        CookieManager cookieManager = new CookieManager(null, customPolicy);
        CookieHandler.setDefault(cookieManager);

        // Handle cookies for the first URI
        URI url = new URI(uri);
        Map<String, List<String>> cookies = new HashMap<>();
        cookies.put("set-cookie", List.of("key=value"));
        cookieManager.put(url, cookies);

        // Handle cookies for the second URI
        URI url1 = new URI(uri1);
        Map<String, List<String>> cookies1 = new HashMap<>();
        cookies1.put("set-cookie", List.of("key1=value1"));
        cookieManager.put(url1, cookies1);

        CookieStore cookieStore = cookieManager.getCookieStore();
        List<HttpCookie> cookieList = cookieStore.getCookies();

        for (HttpCookie cookie : cookieList) {
            System.out.println("Domain name is: " + cookie.getDomain());
            System.out.println("Cookie name is: " + cookie.getName());
        }
    }
}
/*
Output:
Domain name is: deerwalk.edu.np
Cookie name is: key
 */