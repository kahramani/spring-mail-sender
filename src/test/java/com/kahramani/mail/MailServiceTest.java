package com.kahramani.mail;

import com.kahramani.mail.application.Application;
import com.kahramani.mail.service.MailBuilder;
import com.kahramani.mail.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by kahramani on 11/21/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class MailServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(MailServiceTest.class);

    @Autowired
    private MailService mailService;

    @Test
    public void sendSample() {
        try {
            MailBuilder mailBuilder = new MailBuilder();
            mailBuilder.setSubject("Test Mail");
            mailBuilder.appendToText("<p>For <b>Test</b> Purpose</p>");
            mailBuilder.setFrom("sender@domain.com.tr");
            mailBuilder.addTo("receiver@domain.com.tr");

            String attachmentPath = "/data/mail-sender/dummy.txt";
            String attachmentName = "changed_name.txt";
            boolean attachmentAdded = mailBuilder.addAttachment(attachmentPath, attachmentName);

            if (attachmentAdded) {
                mailService.send(mailBuilder);
            }

        } catch (Exception e) {
            logger.error("Failed to send mail", e);
        }
    }
}
