package com.zm;


import java.net.*;
import java.io.*;

public class HttpURLConnectionExample {

    private static HttpURLConnection con;

    public static void main(String[] args) throws Exception {

        URL url = new URL("https://www.baidu2.com");
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        System.out.println(content.toString());
    }
}
