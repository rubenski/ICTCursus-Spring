package nl.codebasesoftware.produx.net.mail;


import nl.codebasesoftware.produx.domain.UserInvitation;
import nl.codebasesoftware.produx.util.Properties;
import nl.codebasesoftware.produx.util.TextProperties;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * User: rvanloen
 * Date: 27-3-13
 * Time: 11:41
 */
@Component
public class InvitationMailer {

    @Resource
    private VelocityEngine velocityEngine;
    @Resource
    private JavaMailSender mailSender;
    @Resource
    private Properties properties;

    private final static int DEFAULT_PORT = 80;

    public void sendInvitationEmail(final UserInvitation invitation, Locale locale) throws MessagingException {


        final String fromEmail = properties.getProperty("email.from.address");
        final String host = properties.getProperty("site.host");
        final String port = properties.getProperty("site.port");
        final String protocol = properties.getProperty("site.protocol");
        final String subject = TextProperties.getTextProperty("invitation.email.subject", locale.getLanguage());
        final String senderName = TextProperties.getTextProperty("mail.standard.sendername", locale.getLanguage());


        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(invitation.getEmail());
                message.setSubject(subject);
                message.setFrom(fromEmail, senderName);
                Map model = new HashMap();

                String activationLink = createLink(invitation, protocol, host, port);

                model.put("activationUrl", activationLink);
                model.put("invitation", invitation);

                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/velocity/mail/user-invitation.vm", model);
                message.setText(text, true);
            }
        };
        mailSender.send(preparator);
    }

    private String createLink(UserInvitation invitation, String protocol, String host, String port) {
        String portInUrl = "";
        if (Integer.parseInt(port) != DEFAULT_PORT) {
            portInUrl = String.format(":%s", port);
        }
        return String.format("%s://%s%s/users/activate/%s", protocol, host, portInUrl, invitation.getSecurityCode());
    }


}
