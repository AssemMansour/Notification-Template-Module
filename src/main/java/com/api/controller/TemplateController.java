package com.api.controller;

import com.api.model.EmailNotification;
import com.api.model.SmsNotification;
import com.api.repo.EmailRepository;
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

    /* Sms Notification Requests */

    @GetMapping("/sms_notifications")
    public @ResponseBody List<SmsNotification> getSmsNotifications() {
        return smsRepository.findAll();
    }

    @PostMapping("/sms_notifications")
    public SmsNotification create(@RequestBody SmsNotification notification) throws TemplateNotFoundException {
        templateRepository.findById(notification.getTemplateId())
                .orElseThrow(() -> new TemplateNotFoundException(notification.getTemplateId()));
        return smsRepository.save(notification); }

    @DeleteMapping("/sms_notifications")
    public ResponseEntity<SmsNotification> clearSmsList() {
        smsRepository.deleteAll();
        return ResponseEntity.ok().build();
    }

    /* Email Notification Requests */

    @GetMapping("/email_notifications")
    public @ResponseBody List<EmailNotification> getEmailNotifications() {
        return emailRepository.findAll();
    }

    @PostMapping("/email_notifications")
    public Notification create(@RequestBody EmailNotification notification) throws TemplateNotFoundException {
        templateRepository.findById(notification.getTemplateId())
                .orElseThrow(() -> new TemplateNotFoundException(notification.getTemplateId()));
        return emailRepository.save(notification);
    }

    @DeleteMapping("/email_notifications")
    public ResponseEntity<EmailNotification> clearEmailList() {
        emailRepository.deleteAll();
        return ResponseEntity.ok().build();
    }

}
