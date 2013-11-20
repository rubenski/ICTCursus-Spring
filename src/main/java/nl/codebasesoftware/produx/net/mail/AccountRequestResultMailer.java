package nl.codebasesoftware.produx.net.mail;


import nl.codebasesoftware.produx.domain.AccountRequest;
import nl.codebasesoftware.produx.util.Properties;
import nl.codebasesoftware.produx.util.TextProperties;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * User: rvanloen
 * Date: 22-4-13
 * Time: 17:53
 */
@Component
public class AccountRequestResultMailer {

    @Resource
    private VelocityEngine velocityEngine;
    @Resource
    private JavaMailSender mailSender;
    @Resource
    private Properties properties;

    public void sendRejectionMail(final AccountRequest request, Locale locale) {

        final String fromEmail = properties.getProperty("email.from.address");
        final String subject = TextProperties.getTextProperty("account.request.rejected.subject", locale.getLanguage());
        final String senderName = TextProperties.getTextProperty("mail.standard.sendername", locale.getLanguage());


        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {

                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(request.getEmail());
                message.setSubject(subject);
                message.setFrom(fromEmail, senderName);
                Map<String, Object> model = new HashMap();

                model.put("request", request);

                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/velocity/mail/rejection-message.vm", model);
                message.setText(text, true);
            }
        };
        mailSender.send(preparator);
    }


    public void sendAcceptedMail(final AccountRequest request, final String generatedPassword, Locale locale) {

        final String fromEmail = properties.getProperty("email.from.address");
        final String subject = TextProperties.getTextProperty("account.request.accepted.subject", locale.getLanguage());
        final String senderName = TextProperties.getTextProperty("mail.standard.sendername", locale.getLanguage());
        final String host = properties.getProperty("site.host");
        final String protocol = properties.getProperty("site.protocol");

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {

                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(request.getEmail());
                message.setSubject(subject);
                message.setFrom(fromEmail, senderName);
                Map<String, Object> model = new HashMap<>();

                model.put("request", request);
                model.put("generatedPassword", generatedPassword);
                model.put("protocol", protocol);
                model.put("host", host);


                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/velocity/mail/accepted-message.vm", model);
                message.setText(text, true);
            }
        };
        mailSender.send(preparator);
    }
}
