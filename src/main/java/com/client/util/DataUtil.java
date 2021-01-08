package com.client.util;

import com.api.enums.NotificationType;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public abstract class DataUtil {

    private DataUtil() {}

    private static final String URL = "http://localhost:8080/notifications/";
    private static final String SEND_DIRECTORY = "send/";

    private static boolean handleRequest(String urlString, String requestMethod) {
        try {
            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(requestMethod);
            connection.connect();
            return connection.getResponseCode() == 200;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static boolean sendNextNotification(NotificationType type) {
       return handleRequest(URL + SEND_DIRECTORY + type.name(), "POST");
    }
}
