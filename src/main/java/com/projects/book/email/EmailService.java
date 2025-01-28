package com.projects.book.email;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;



@Service
@AllArgsConstructor

public class EmailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Async
    public void sendEmail(
            String to,
            String username,
            EmailTemplateName emailTemplate,
            String confirmationUrl,
            String activationCode,
            String subject
    ) throws MessagingException {
      String templateName;
      if (emailTemplate != null) {
          templateName = "activate_account.html";
      }
      else {
          templateName = emailTemplate.getName();
      }

      MimeMessage mimeMessage = mailSender.createMimeMessage();
      MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
        mimeMessage,
        MimeMessageHelper.MULTIPART_MODE_MIXED,
              StandardCharsets.UTF_8.name()
      );
        Map<String, Object> properties = new HashMap<>();
        properties.put("username", username);
        properties.put("confirmationUrl", confirmationUrl);
        properties.put("activationCode", activationCode);

        Context context = new Context();
        context.setVariables(properties);

        mimeMessageHelper.setFrom("abdulmalik@BSN.com");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);

        String template = templateEngine.process(templateName, context);
        mimeMessageHelper.setText(template, true);

        mailSender.send(mimeMessage);
    }

}
