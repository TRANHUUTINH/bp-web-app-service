package com.bp.contact.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.AddressException;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.bp.contact.model.ContactForm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${contact.receiver.email}")
    private String toEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendContactEmail(ContactForm form) throws MessagingException {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

    helper.setFrom("Henry@bppackaging.net");
    helper.setReplyTo(form.getEmail());
    helper.setTo(toEmail);
    helper.setSubject("📩 [Liên hệ] " + form.getSubject());


    String content = String.format(
        "📧 Email: %s\n📞 Số điện thoại: %s\n---\n%s",
        form.getEmail(),
        form.getPhone(),
        form.getMessage()
    );
    helper.setText(content);

    mailSender.send(message);
}
}