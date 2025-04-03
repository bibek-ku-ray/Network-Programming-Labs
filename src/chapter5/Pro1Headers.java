package chapter5;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Pro1Headers {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://deerwalk.edu.np");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Get all headers
            Map<String, List<String>> headers = connection.getHeaderFields();
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                String headerName = entry.getKey();
                List<String> headerValues = entry.getValue();
                System.out.println(headerName + ": " + String.join(", ", headerValues));
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
Output:
null: HTTP/1.1 200 OK
Transfer-Encoding: chunked
Server: cloudflare
CF-RAY: 918dccfa384c790b-KTM
Connection: keep-alive
Last-Modified: Thu, 02 Jan 2025 08:30:17 GMT
cf-cache-status: DYNAMIC
Date: Fri, 28 Feb 2025 04:51:39 GMT
NEL: {"success_fraction":0,"report_to":"cf-nel","max_age":604800}
Report-To: {"endpoints":[{"url":"https:\/\/a.nel.cloudflare.com\/report\/v4?Pro1DayTime=vT%2BsakLRBF8kdxgWh8upToWdgmPZLSSBR57%2FTekZlFlFn5QHMoPsuRKZsIfl1LHy89jREXcsn12RIyX45vGKNwy54ldvxnZw57aaLYP08llPWArsVOjSSE9HVNKEawHMQ3w%3D"}],"group":"cf-nel","max_age":604800}
Vary: Accept-Encoding
server-timing: cfL4;desc="?proto=TCP&rtt=9865&min_rtt=5029&rtt_var=10815&sent=7&recv=7&lost=0&retrans=0&sent_bytes=3120&recv_bytes=689&delivery_rate=580632&cwnd=252&unsent_bytes=0&cid=a88df0d4322ece3c&ts=839&x=0"
alt-svc: h3=":443"; ma=86400
Content-Type: text/html
 */

