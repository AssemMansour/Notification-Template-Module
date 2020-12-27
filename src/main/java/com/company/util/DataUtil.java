package com.company.util;

import com.api.model.Template;
import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class DataUtil {

    private DataUtil() {
    }

    private static final String SERVICE_URL = "http://localhost:8080/templates/";


    public static ArrayList<Template> getAllTemplates() {
        String jsonResponse = getJsonResponse();
        return getSearchResults(jsonResponse);
    }

    public static Long createTemplate(Template template) {
        return handleWriteRequest(template, SERVICE_URL, "POST");
    }

    public static Long updateTemplate(Template template) {
        Long id = template.getId();
        return handleWriteRequest(template, SERVICE_URL + id, "PUT");
    }

    public static Long deleteTemplate(Template template) {
        Long id = template.getId();
        return handleWriteRequest(template, SERVICE_URL + id, "DELETE");
    }


    private static ArrayList<Template> getSearchResults(String jsonResponse) {

        ArrayList<Template> results = new ArrayList<>();

        if (jsonResponse.isEmpty())
            return null;

        try {
            JSONArray jsonArray = new JSONArray(jsonResponse);
            int length = jsonArray.length();

            for (int i = 0; i < length; i++) {
                JSONObject result = jsonArray.getJSONObject(i);

                Long id = result.getLong("id");
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

        URL url = createUrl(SERVICE_URL);
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

    private static Long handleWriteRequest(Template template, String urlString, String requestMethod) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true);
            connection.setRequestMethod(requestMethod);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8);
            writer.write(template.toString());
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

}
