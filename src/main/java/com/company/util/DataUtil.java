package com.company.util;

import com.api.model.Template;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;


public class DataUtil {

    private DataUtil(){}

    private static final String URL = "http://localhost:8080/";
    private static final String DIRECTORY = "templates/";


   public static ArrayList<Template> viewTemplates() {
        String jsonResponse = getJsonResponse();
        return getSearchResults(jsonResponse);
    }

    public static void updateTemplate(Template template) {
        int id = template.getId();

        HttpURLConnection connection = getHttpURLConnection(URL + DIRECTORY + id, "PUT");

        if (connection != null) {

            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            connection.setDoOutput(true);
            OutputStreamWriter out;

            try {
                out = new OutputStreamWriter(connection.getOutputStream());
                out.write(template.toString());
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public static void deleteTemplate(Template template) {
        int id = template.getId();

        URL url = createUrl(URL + DIRECTORY + id);
        HttpURLConnection connection;

        if (url == null){
            System.out.println("Couldn't find element with id:" + id);
            return;
        }

        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty(
                    "Content-Type", "application/x-www-form-urlencoded" );
            connection.setRequestMethod("DELETE");
            connection.connect();
        } catch (IOException e) {
            System.out.println("Couldn't find element with id:" + id);
        }

    }

    public static void createTemplate(Template template) {

        HttpURLConnection connection = getHttpURLConnection(URL + DIRECTORY, "PUT");

        if (connection != null) {

            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            connection.setDoOutput(true);
            OutputStreamWriter out;

            try {
                out = new OutputStreamWriter(connection.getOutputStream());
                out.write(template.toString());
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<Template> getSearchResults(String jsonResponse) {

       ArrayList<Template> results = new ArrayList<>();

        if (jsonResponse.isEmpty())
            return null;

        try {
            JSONArray jsonArray = new JSONArray(jsonResponse);
            int length = jsonArray.length();

            for (int i = 0; i < length; i++) {
                JSONObject result = jsonArray.getJSONObject(i);

                int id = result.getInt("id");
                String content = result.getString("content");
                int numberOfUnknowns = result.getInt("numberOfUnknowns");
                int templateType = result.getInt("templateType");
                boolean language = result.getBoolean("language");

                Template template = new Template(content, numberOfUnknowns, templateType, language);
                template.setId(id);

                results.add(template);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return results;
    }

    private static String getJsonResponse() {

        HttpURLConnection urlConnection;

        try {
            urlConnection = getHttpURLConnection("http://localhost:8080/templates/", "GET");

            if (urlConnection != null && urlConnection.getResponseCode() == 200)
                return readFromStream(urlConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    private static HttpURLConnection getHttpURLConnection(String urlString, String requestMethod) {
        URL url = createUrl(urlString);
        if (url == null)
            return null;

        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(requestMethod);
            connection.setConnectTimeout(5000);
            connection.connect();

            if (connection.getResponseCode() == 200)
                return connection;

        } catch (ProtocolException e) {
            System.out.println("Protocol Exception");

        } catch (IOException e) {
            System.out.println("HttpURLConnection IOException Exception");
        }
        return null;

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
