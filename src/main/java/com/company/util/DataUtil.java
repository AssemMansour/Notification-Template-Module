package com.company.util;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class DataUtil {
    static String getJsonResponse(String urlString) {

        URL url = createUrl(urlString);
        if (url == null)
            return null;

        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.connect();

            if (connection.getResponseCode() == 200)
                return readFromStream(connection.getInputStream());

        } catch (ProtocolException e) {
            System.out.println("Protocol Exception");

        } catch (IOException e) {
            System.out.println("HttpURLConnection IOException Exception");
        }

        return "";
    }

    static Long handleWriteRequest(String request, String urlString, String requestMethod) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true);
            connection.setRequestMethod(requestMethod);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8);
            writer.write(request);
            writer.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            reader.close();
            connection.disconnect();
            JSONObject object = new JSONObject(jsonString.toString());

            if (requestMethod.equals("DELETE"))
                return 0L;
            else
                return object.getLong("id");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static URL createUrl(String urlString) {

        URL url;

        try {
            url = new URL(urlString);
            return url;
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL");

        }

        return null;
    }

    private static String readFromStream(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();

        try {
            String line;
            while ((line = reader.readLine()) != null)
                builder.append(line);
            inputStream.close();

        } catch (IOException e) {
            System.out.println("readFromStream IOException");
            return "";
        }
        return builder.toString();
    }


}
