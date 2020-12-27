package com.company.util;

import com.api.model.Notification;
import com.api.model.Template;

import java.util.ArrayList;

public class NotificationUtil {

    private static final String NOTIFICATION_URL = "http://localhost:8080/notification/";


    public static ArrayList<Notification> getAllNotifications() {
        String jsonResponse = DataUtil.getJsonResponse(NOTIFICATION_URL);
        return DataUtil.getNotificationResults(jsonResponse);
    }

    public static Long createNotification(Notification notification) {
        return DataUtil.handleWriteRequest(notification.toString(), NOTIFICATION_URL, "POST");
    }

    public static Long deleteTemplate(Notification notification) {
        Long id = notification.getId();
        return DataUtil.handleWriteRequest(notification.toString(), NOTIFICATION_URL + id, "DELETE");
    }


}
