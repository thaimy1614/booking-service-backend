package com.s_service.s_service.service.notification.mail;

import com.s_service.s_service.dto.request.mail.CustomerInfo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
@RequiredArgsConstructor
class EmailServiceImpl implements EmailService {
    public static final String NEW_USER_ACCOUNT_VERIFICATION = "New User Account Verification";
    public static final String UTF_8_ENCODING = "UTF-8";
    public static final String EMAIL_TEMPLATE = "email-template";
    public static final String CONTACT_TEMPLATE = "contact";
    public static final String NEW_PASSWORD_TEMPLATE = "send-new-password";
    public static final String OTP_TEMPLATE = "send-otp";

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    @Async
    public void sendVerification(String name, String to, String url) {
        try {
            Context context = new Context();
            context.setVariable("name", name);
            context.setVariable("url", url);
            String text = templateEngine.process(EMAIL_TEMPLATE, context);
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setText(text, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            log.info("Email sending failed!");
            throw new RuntimeException(e);
        }
    }

    @Override
    @Async
    public void sendOtp(String topic, String email, String otp) {
        try {
            Context context = new Context();
            context.setVariable("otp", otp);
            String text = templateEngine.process(OTP_TEMPLATE, context);
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject("OTP Đã Sẵn Sàng");
            helper.setFrom(from);
            helper.setTo(email);
            helper.setText(text, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Async
    public void sendNewPassword(String topic, String email, String password) {
        try {
            Context context = new Context();
            context.setVariable("password", password);
            String text = templateEngine.process(NEW_PASSWORD_TEMPLATE, context);
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject("Mật Khẩu Mới Của Bạn Đã Sẵn Sàng");
            helper.setFrom(from);
            helper.setTo(email);
            helper.setText(text, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Async
    public void sendContact(String topic, CustomerInfo info) {
        try {
            Context context = new Context();
            context.setVariable("name", info.getName());
            String text = templateEngine.process(CONTACT_TEMPLATE, context);
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject("Cảm Ơn Vì Đã Chọn Dịch Vụ Của Chúng Tôi");
            helper.setFrom(from);
            helper.setTo(info.getEmail());
            helper.setText(text, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
