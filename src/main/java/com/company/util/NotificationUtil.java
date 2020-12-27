package com.company.util;

import com.api.model.Notification;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NotificationUtil {

    private static final String NOTIFICATION_URL = "http://localhost:8080/notification/";


    public static ArrayList<Notification> getAllNotifications() {
        String jsonResponse = DataUtil.getJsonResponse(NOTIFICATION_URL);
        return getNotificationResults(jsonResponse);
    }

    public static Long createNotification(Notification notification) {
        return DataUtil.handleWriteRequest(notification.toString(), NOTIFICATION_URL, "POST");
    }

    public static Long deleteNotification(Notification notification) {
        Long id = notification.getId();
        return DataUtil.handleWriteRequest(notification.toString(), NOTIFICATION_URL + id, "DELETE");
    }


    public static ArrayList<Notification> getNotificationResults(String jsonResponse) {

        ArrayList<Notification> results = new ArrayList<>();

        if (jsonResponse.isEmpty())
            return null;

        try {
            JSONArray jsonArray = new JSONArray(jsonResponse);
            int length = jsonArray.length();

            for (int i = 0; i < length; i++) {
                JSONObject result = jsonArray.getJSONObject(i);

                Long id = result.getLong("id");
                String content = result.getString("content");
                String sender = result.getString("sender");
                String receiver = result.getString("receiver");

                Notification notification  = new Notification(content, sender, receiver);
                notification.setId(id);

                results.add(notification);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return results;
    }
}
