package com.api.controller;

import com.api.enums.NotificationType;
import com.api.enums.Status;
import com.api.exception.NotificationNotFoundException;
import com.api.model.EmailNotification;
import com.api.model.SmsNotification;
import com.api.repo.EmailRepository;
import com.api.repo.NotificationRepository;
import com.api.repo.SmsRepository;
import com.api.model.Notification;
import com.api.model.Template;
import com.api.exception.TemplateNotFoundException;
import com.api.repo.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TemplateController {

    @Autowired
    TemplateRepository templateRepository;
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    SmsRepository smsRepository;
    @Autowired
    EmailRepository emailRepository;


    /* Template Requests */

    @GetMapping("/templates")
    public @ResponseBody List<Template> getAllTemplates() {
        return templateRepository.findAll();
    }

    @PostMapping("/templates")
    public Template create(@RequestBody Template template) {
        return templateRepository.save(template);
    }

    @GetMapping("/templates/{id}")
    public Template getTemplateById(@PathVariable(value = "id") Long id) throws TemplateNotFoundException {
        return templateRepository.findById(id)
                .orElseThrow(() -> new TemplateNotFoundException(id));
    }

    @PutMapping("/templates/{id}")
    public Template update(@PathVariable(value = "id") Long id,
                           @RequestBody Template templateDetails) throws TemplateNotFoundException {

        Template template = templateRepository.findById(id)
                .orElseThrow(() -> new TemplateNotFoundException(id));

        template.setTemplateType(templateDetails.getTemplateType());
        template.setContent(templateDetails.getContent());
        template.setNumberOfUnknowns(templateDetails.getNumberOfUnknowns());

        return templateRepository.save(template);
    }

    @DeleteMapping("/templates/{id}")
    public ResponseEntity<Template> deleteTemplate(@PathVariable(value = "id") Long id) throws TemplateNotFoundException {
        Template template = templateRepository.findById(id)
                .orElseThrow(() -> new TemplateNotFoundException(id));

        templateRepository.delete(template);
        return ResponseEntity.ok().build();
    }

    /* Notification Requests */

    @PostMapping("/notifications")
    public Notification create(@RequestBody Notification notification) throws TemplateNotFoundException {

        if (notification.getTemplate() == null)
            throw new TemplateNotFoundException(0L);

        notification.prepareContent();
        return notificationRepository.save(notification);
    }

    @PostMapping("/notifications/send?type={type}")
    public Notification sendNextNotification(@PathVariable(value = "type") NotificationType type) throws NotificationNotFoundException {

        Notification notification = notificationRepository.getNext();
        if (notification == null) throw new NotificationNotFoundException(0L);

        notificationRepository.delete(notification);

        if (type == NotificationType.SMS)
            return smsRepository.save((SmsNotification) notification);

        return emailRepository.save((EmailNotification) notification);
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
