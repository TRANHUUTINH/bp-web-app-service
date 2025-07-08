package com.bp.contact.controller;

import com.bp.contact.model.ContactForm;
import com.bp.contact.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private final EmailService emailService;

    public ContactController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<String> sendContact(@Valid @RequestBody ContactForm form) {
        try {
            emailService.sendContactEmail(form);
            return ResponseEntity.ok("Gửi email thành công!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Gửi email thất bại: " + e.getMessage());
        }
    }
}
