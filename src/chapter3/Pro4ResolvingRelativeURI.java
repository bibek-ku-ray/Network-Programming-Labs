package chapter3;

import java.net.MalformedURLException;
import java.net.URL;

public class Pro4ResolvingRelativeURI {
    public static void main(String[] args) throws MalformedURLException {
        String baseUrlStr = "https://deerwalk.edu.np/DWIT/admission.php";
        String relativeUrl = "admission.php";
        URL baseUrl = new URL(baseUrlStr);
        URL resolvedRelativeUrl = new URL(baseUrl, relativeUrl);
        System.out.println("Base Url: "+baseUrl+" and Resolved Url: "+resolvedRelativeUrl);


    }
}
/*
Output
Base Url: https://deerwalk.edu.np/DWIT/admission.php and Resolved Url: https://deerwalk.edu.np/DWIT/admission.php
 */
