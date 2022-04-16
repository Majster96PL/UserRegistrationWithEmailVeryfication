package com.example.demo.email;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailSenderService implements EmailSender{

    private final static Logger log = LoggerFactory.getLogger(EmailSender.class);
    private final String FAILED_MESSAGE = "Failed to send email!";
    private final JavaMailSender mailSender;


    @Override
    @Async
    public void send(String receiver, String email) {
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
            mimeMessageHelper.setText(email, true);
            mimeMessageHelper.setTo(receiver);
            mimeMessageHelper.setSubject("Confirm your email!");
            mimeMessageHelper.setFrom("siemazrana@gmail.com");

        }catch (MessagingException e){
            log.error(FAILED_MESSAGE, e);
            throw new IllegalStateException(FAILED_MESSAGE);
        }

    }
}
