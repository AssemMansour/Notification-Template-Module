package com.api.controller;

import com.company.Template;
import com.api.exception.TemplateNotFoundException;
import com.api.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TemplateController {

    @Autowired
    TemplateRepository templateRepository;

    @GetMapping("/templates")
    public List<Template> getAllTemplates() {
        return templateRepository.findAll();
    }

    @PostMapping("/templates")
    public Template create(@Valid @RequestBody Template template) {
        return templateRepository.save(template);
    }

    // Get a Single Note
    @GetMapping("/templates/{id}")
    public Template getTemplateById(@PathVariable(value = "id") Long id) throws TemplateNotFoundException {
        return templateRepository.findById(id)
                .orElseThrow(() -> new TemplateNotFoundException(id));
    }

    @PutMapping("/templates/{id}")
    public Template update(@PathVariable(value = "id") Long id,
                           @Valid @RequestBody Template templateDetails) throws TemplateNotFoundException {

        Template template = templateRepository.findById(id)
                .orElseThrow(() -> new TemplateNotFoundException(id));

        template.setTemplateType(templateDetails.getTemplateType());
        template.setContent(templateDetails.getContent());
        template.setNumberOfUnknowns(templateDetails.getNumberOfUnknowns());

        return templateRepository.save(template);
    }

    @DeleteMapping("/templates/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws TemplateNotFoundException {
        Template template = templateRepository.findById(id)
                .orElseThrow(() -> new TemplateNotFoundException(id));

        templateRepository.delete(template);
        return ResponseEntity.ok().build();
    }


}
