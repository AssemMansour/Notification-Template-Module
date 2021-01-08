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



}
