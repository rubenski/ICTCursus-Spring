package nl.codebasesoftware.produx.net.mail;

import nl.codebasesoftware.produx.domain.UserProfile;
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
 * Date: 7-11-12
 * Time: 17:50
 */

@Component
public class PasswordMailer {

    @Resource
    private VelocityEngine velocityEngine;
    @Resource
    private JavaMailSender mailSender;
    @Resource
    private Properties properties;

    public void sendPasswordEmail(final UserProfile userProfile, final String password, Locale locale) {

        final String fromEmail = properties.getProperty("email.from.address");
        final String host = properties.getProperty("site.host");
        final String port = properties.getProperty("site.port");
        final String protocol = properties.getProperty("site.protocol");
        final String subject = TextProperties.getTextProperty("password.request.mail.subject", locale.getLanguage());
        final String senderName = TextProperties.getTextProperty("mail.standard.sendername", locale.getLanguage());

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(userProfile.getEmail());
                message.setSubject(subject);
                message.setFrom(fromEmail, senderName);
                Map model = new HashMap();

                model.put("userProfile", userProfile);
                model.put("password", password);
                model.put("protocol", protocol);
                model.put("host", host);
                model.put("port", port);

                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/velocity/mail/password-request.vm", model);
                message.setText(text, true);
            }
        };
        mailSender.send(preparator);
    }
}
