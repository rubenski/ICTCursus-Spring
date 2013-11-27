package nl.codebasesoftware.produx.net.mail;


import nl.codebasesoftware.produx.domain.dto.entity.AccountRequestEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.UserProfileEntityDTO;
import nl.codebasesoftware.produx.properties.Properties;
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
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * User: rvanloen
 * Date: 22-4-13
 * Time: 17:53
 */
@Component
public class AccountRequestMailer {

    @Resource
    private VelocityEngine velocityEngine;
    @Resource
    private JavaMailSender mailSender;
    @Resource
    private Properties properties;

    public void sendNewAccountRequestMailToAdmin(final AccountRequestEntityDTO request, final List<UserProfileEntityDTO> admins, Locale locale) {

        final String fromEmail = properties.getProperty("email.from.address");
        final String subject = TextProperties.getTextProperty("account.request.arrived", locale.getLanguage());
        final String senderName = TextProperties.getTextProperty("mail.standard.sendername", locale.getLanguage());
        final String protocol = properties.getProperty("site.protocol");
        final String host = properties.getProperty("site.host");

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {

                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

                for (UserProfileEntityDTO admin : admins) {
                    message.addTo(admin.getEmail());
                }

                message.setSubject(subject);
                message.setFrom(fromEmail, senderName);

                Map model = new HashMap();

                model.put("request", request);
                model.put("link", createLink(request, protocol, host));

                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/velocity/mail/new-account-request.vm", model);
                message.setText(text, true);
            }
        };
        mailSender.send(preparator);
    }

    private String createLink(AccountRequestEntityDTO request, String protocol, String host) {
        return String.format("%s://%s/admin/sys/accountrequests/%s", protocol, host, request.getId());
    }

}
