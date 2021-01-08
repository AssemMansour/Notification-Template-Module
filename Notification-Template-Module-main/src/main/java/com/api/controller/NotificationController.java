package com.api.controller;

import com.api.enums.NotificationType;
import com.api.enums.Status;
import com.api.exception.NotificationNotFoundException;
import com.api.exception.TemplateNotFoundException;
import com.api.model.EmailNotification;
import com.api.model.Notification;
import com.api.model.SmsNotification;
import com.api.model.Template;
import com.api.repo.EmailRepository;
import com.api.repo.NotificationRepository;
import com.api.repo.SmsRepository;
import com.api.repo.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController

public class NotificationController {
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    TemplateRepository templateRepository;
    @Autowired
    SmsRepository smsRepository;
    @Autowired
    EmailRepository emailRepository;
    /* Notification Requests */

    @PostMapping("/notifications")
    public Notification create(@RequestBody Notification notification) throws TemplateNotFoundException {

        Long id = notification.getTemplateId();
        Template template = templateRepository.findById(id)
                .orElseThrow(() -> new TemplateNotFoundException(id));

        notification.setTemplate(template);
        notification.prepareContent();
        return notificationRepository.save(notification);
    }

    @GetMapping("/notifications")
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @PostMapping("/notifications/send/{type}")
    public Notification sendNextNotification(@PathVariable(value = "type") NotificationType type) throws NotificationNotFoundException {

        Notification notification = notificationRepository.getNext();
        if (notification == null) throw new NotificationNotFoundException(0L);

        notificationRepository.delete(notification);

        if (type == NotificationType.SMS)
            return smsRepository.save(new SmsNotification(notification));

        return emailRepository.save(new EmailNotification(notification));
    }


    @DeleteMapping("/notifications")
    public ResponseEntity<Notification> clearNotificationList() {
        smsRepository.deleteAll();
        emailRepository.deleteAll();
        return ResponseEntity.ok().build();
    }

    /* Sms Notification Requests */

    @GetMapping("/sms_notifications")
    public @ResponseBody List<SmsNotification> getSmsNotifications() {
        return smsRepository.findAll();
    }

    @PutMapping("/sms_notifications/{id}/{status}")
    public SmsNotification setSmsStatus(@PathVariable(value = "id") Long id, @PathVariable(value = "status") Status status)
            throws NotificationNotFoundException {

        SmsNotification notification = smsRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException(id));

        if (status != null)
            notification.setStatus(status);
        return smsRepository.save(notification);
    }

    /* Email Notification Requests */

    @GetMapping("/email_notifications")
    public @ResponseBody List<EmailNotification> getEmailNotifications() {
        return emailRepository.findAll();
    }

    @PutMapping("/email_notifications/{id}/{status}")
    public EmailNotification setEmailStatus(@PathVariable(value = "id") Long id, @PathVariable(value = "status") Status status)
            throws NotificationNotFoundException {

        EmailNotification notification = emailRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException(id));

        if (status != null)
            notification.setStatus(status);
        return emailRepository.save(notification);
    }
}
