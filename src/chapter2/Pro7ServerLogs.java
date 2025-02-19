package chapter2;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Pro7ServerLogs {
    public static void main(String[] args) {
        String logFilePath = "D:\\College\\SEM-VI\\NP\\Chapter-2\\src\\myLogs.log";

        try (FileInputStream file = new FileInputStream(logFilePath);
             Reader reader = new InputStreamReader(file);
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            for (String entry = bufferedReader.readLine(); entry != null; entry = bufferedReader.readLine()) {
                // Separate out the IP address
                int index = entry.indexOf(' '); // position of the first space
                if (index == -1) {
                    continue; // Skip invalid log entries
                }

                String[] logArray = entry.split(" ");
                String ipAddress = logArray[0];
                String date = logArray[3].replace("[", "");
                String method = logArray[5].replace("\"", "");
                String path = logArray[6];

                System.out.println("IP: "+ipAddress+"\nDate: "+date+"\nMethod: "+method+"\nPath: "+path);

                String ip = entry.substring(0, index);
                String theRest = entry.substring(index); // read remaining part
                System.out.println("---------------------------------");
                //System.out.println(Arrays.toString(logArray));

                // Ask DNS for the hostname and print it out
                try {
                    InetAddress address = InetAddress.getByName(ip);
                    System.out.println(address.getHostName() + theRest);
                } catch (UnknownHostException ex) {
                    System.err.println(entry);
                }
            }
        } catch (IOException ex) {
            System.out.println("Exception: " + ex);
        }
    }
}


/*
Output: -> Write any 2 to 3 output not all
IP: 54.36.149.41
Date: 22/Jan/2019:03:56:14
Method: GET
Path: /filter/27|13%20%D9%85%DA%AF%D8%A7%D9%BE%DB%8C%DA%A9%D8%B3%D9%84,27|%DA%A9%D9%85%D8%AA%D8%B1%20%D8%A7%D8%B2%205%20%D9%85%DA%AF%D8%A7%D9%BE%DB%8C%DA%A9%D8%B3%D9%84,p53
---------------------------------
hydrogen297-ext2.ahrefs.net - - [22/Jan/2019:03:56:14 +0330] "GET /filter/27|13%20%D9%85%DA%AF%D8%A7%D9%BE%DB%8C%DA%A9%D8%B3%D9%84,27|%DA%A9%D9%85%D8%AA%D8%B1%20%D8%A7%D8%B2%205%20%D9%85%DA%AF%D8%A7%D9%BE%DB%8C%DA%A9%D8%B3%D9%84,p53 HTTP/1.1"  200 30577 "-" "Mozilla/5.0 (compatible; AhrefsBot/6.1; +http://ahrefs.com/robot/)&quot; "-"
IP: 31.56.96.51
Date: 22/Jan/2019:03:56:16
Method: GET
Path: /image/60844/productModel/200x200
---------------------------------
31.56.96.51 - - [22/Jan/2019:03:56:16 +0330] "GET /image/60844/productModel/200x200 HTTP/1.1" 200 5667 "https://www.zanbil.ir/m/filter/b113&quot; "Mozilla/5.0 (Linux; Android 6.0; ALE-L21 Build/HuaweiALE-L21) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.158 Mobile Safari/537.36" "-"
IP: 31.56.96.51
Date: 22/Jan/2019:03:56:16
Method: GET
Path: /image/61474/productModel/200x200
---------------------------------
31.56.96.51 - - [22/Jan/2019:03:56:16 +0330] "GET /image/61474/productModel/200x200 HTTP/1.1" 200 5379 "https://www.zanbil.ir/m/filter/b113&quot; "Mozilla/5.0 (Linux; Android 6.0; ALE-L21 Build/HuaweiALE-L21) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.158 Mobile Safari/537.36" "-"
IP: 40.77.167.129
Date: 22/Jan/2019:03:56:17
Method: GET
Path: /image/14925/productModel/100x100
---------------------------------
msnbot-40-77-167-129.search.msn.com - - [22/Jan/2019:03:56:17 +0330] "GET /image/14925/productModel/100x100 HTTP/1.1" 200 1696 "-" "Mozilla/5.0 (compatible; bingbot/2.0; +http://www.bing.com/bingbot.htm)&quot; "-"
IP: 91.99.72.15
Date: 22/Jan/2019:03:56:17
Method: GET
Path: /product/31893/62100/%D8%B3%D8%B4%D9%88%D8%A7%D8%B1-%D8%AE%D8%A7%D9%86%DA%AF%DB%8C-%D9%BE%D8%B1%D9%86%D8%B3%D9%84%DB%8C-%D9%85%D8%AF%D9%84-PR257AT
---------------------------------
static.15.72.99.91.clients.your-server.de - - [22/Jan/2019:03:56:17 +0330] "GET /product/31893/62100/%D8%B3%D8%B4%D9%88%D8%A7%D8%B1-%D8%AE%D8%A7%D9%86%DA%AF%DB%8C-%D9%BE%D8%B1%D9%86%D8%B3%D9%84%DB%8C-%D9%85%D8%AF%D9%84-PR257AT HTTP/1.1" 200 41483 "-" "Mozilla/5.0 (Windows NT 6.2; Win64; x64; rv:16.0)Gecko/16.0 Firefox/16.0" "-"
IP: 40.77.167.129
Date: 22/Jan/2019:03:56:17
Method: GET
Path: /image/23488/productModel/150x150
---------------------------------
msnbot-40-77-167-129.search.msn.com - - [22/Jan/2019:03:56:17 +0330] "GET /image/23488/productModel/150x150 HTTP/1.1" 200 2654 "-" "Mozilla/5.0 (compatible; bingbot/2.0; +http://www.bing.com/bingbot.htm)&quot; "-"
IP: 40.77.167.129
Date: 22/Jan/2019:03:56:18
Method: GET
Path: /image/45437/productModel/150x150
---------------------------------
msnbot-40-77-167-129.search.msn.com - - [22/Jan/2019:03:56:18 +0330] "GET /image/45437/productModel/150x150 HTTP/1.1" 200 3688 "-" "Mozilla/5.0 (compatible; bingbot/2.0; +http://www.bing.com/bingbot.htm)&quot; "-"
IP: 40.77.167.129
Date: 22/Jan/2019:03:56:18
Method: GET
Path: /image/576/article/100x100
---------------------------------
msnbot-40-77-167-129.search.msn.com - - [22/Jan/2019:03:56:18 +0330] "GET /image/576/article/100x100 HTTP/1.1" 200 14776 "-" "Mozilla/5.0 (compatible; bingbot/2.0; +http://www.bing.com/bingbot.htm)&quot; "-"

 */