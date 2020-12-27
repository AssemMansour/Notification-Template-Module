package com.company.util;

import com.api.model.Template;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;


public class DataUtil {

    private DataUtil(){}

    private static String url = "http://localhost:8080/";


   public static ArrayList<Template> viewTemplates() {
        String jsonResponse = getJsonResponse(url, "GET");
        return getSearchResults(jsonResponse);

    }

    public static void updateTemplate() {}

    public static void deleteTemplate() {}

    public static void createTemplate(Template template) {}

    public static ArrayList<Template> getSearchResults(String jsonResponse) {

        if (jsonResponse.isEmpty())
            return null;

        return null;
    }


    private static String getJsonResponse(String urlString, String requestMethod) {

        URL url = createUrl(urlString);
        if (url == null)
            return "";

        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(requestMethod);
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

    private static URL createUrl(String urlString) {

        URL url = null;

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
