package chapter4;

import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Pro2CookieStore2 implements CookieStore {
    private final List<HttpCookie> cookies = new ArrayList<>();
    private final List<URI> uris = new ArrayList<>();

    @Override
    public void add(URI uri, HttpCookie cookie) {
        cookies.add(cookie);
        if (!uris.contains(uri)) {
            uris.add(uri);
        }
    }

    @Override
    public List<HttpCookie> get(URI uri) {
        List<HttpCookie> result = new ArrayList<>();
        for (HttpCookie cookie : cookies) {
            if (cookie.getDomain() != null && cookie.getDomain().equalsIgnoreCase(uri.getHost())) {
                result.add(cookie);
            }
        }
        return result;
    }

    @Override
    public List<HttpCookie> getCookies() {
        return new ArrayList<>(cookies);
    }

    @Override
    public List<URI> getURIs() {
        return new ArrayList<>(uris);
    }

    @Override
    public boolean remove(URI uri, HttpCookie cookie) {
        return cookies.remove(cookie);
    }

    @Override
    public boolean removeAll() {
        cookies.clear();
        uris.clear();
        return true;
    }

    public static void main(String[] args) {
        try {
            CookieStore cookieStore = new Pro2CookieStore2();
            HttpCookie cookie = new HttpCookie("userId", "u1");
            HttpCookie cookie1 = new HttpCookie("name", "user1");
            URI uri = URI.create("http://localhost:3000/posts");

            cookieStore.add(uri, cookie);
            cookieStore.add(uri, cookie1);

            System.out.println("Cookies for URI: " + cookieStore.get(uri));
            System.out.println("All Cookies: " + cookieStore.getCookies());
            System.out.println("All URIs: " + cookieStore.getURIs());

            cookieStore.remove(uri, cookie);

            System.out.println("All Cookies after removal: " + cookieStore.getCookies());
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}

/*
Output:
Cookies for URI: []
All Cookies: [userId="u1", name="user1"]
All URIs: [http://localhost:3000/posts]
All Cookies after removal: [name="user1"]
 */
