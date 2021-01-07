package com.api.util;

import com.api.model.Notification;
import com.api.model.Template;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NotificationUtil {
    private static final String NOTIFICATION_URL = "http://localhost:8080/notifications/";

    public static void createNotification(Notification notification) {
        DataUtil.handleWriteRequest(notification.toString(), NOTIFICATION_URL, "POST");
    }

    public static void deleteNextNotification() {
        DataUtil.handleWriteRequest("", NOTIFICATION_URL, "DELETE");
    }

    public static ArrayList<Notification> getAllNotifications() {
        String jsonResponse = DataUtil.getJsonResponse(NOTIFICATION_URL);
        return getNotificationResults(jsonResponse);
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
                int notificationType = result.getInt("notificationType");

                Notification notification  = new Notification(content, sender, receiver, notificationType);
                notification.setId(id);

                results.add(notification);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return results;
    }
    public static String processTemp(Template template, ArrayList<String> toReplace) {
        String toBeSent = template.getContent();
        toBeSent = toBeSent.replaceAll("\\{.*?\\}", "#PH");
        for (int i = 0; i < template.getNumberOfUnknowns(); i++) {
            toBeSent = toBeSent.replaceFirst("#PH", toReplace.get(i));
        }
        return toBeSent;
    }

    public static void sendNextNotifications() {
        // Do Sending Action
        NotificationUtil.deleteNextNotification();
    }

}
