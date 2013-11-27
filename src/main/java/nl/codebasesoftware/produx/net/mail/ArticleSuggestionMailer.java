package nl.codebasesoftware.produx.net.mail;

import nl.codebasesoftware.produx.domain.dto.entity.ArticleSuggestionEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.UserProfileEntityDTO;
import nl.codebasesoftware.produx.domain.optionlists.RoleName;
import nl.codebasesoftware.produx.service.UserProfileService;
import nl.codebasesoftware.produx.properties.Properties;
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
 * Date: 22-11-13
 * Time: 23:29
 */
@Component
public class ArticleSuggestionMailer {

    @Resource
    private VelocityEngine velocityEngine;
    @Resource
    private JavaMailSender mailSender;
    @Resource
    private Properties properties;
    @Resource
    private UserProfileService userProfileService;

    public void sendNewSuggestionMail(final ArticleSuggestionEntityDTO suggestion, Locale locale) throws MessagingException {


        final String fromEmail = properties.getProperty("email.from.address");
        final String host = properties.getProperty("site.host");
        final String protocol = properties.getProperty("site.protocol");
        final String subject = TextProperties.getTextProperty("article.suggestion.mailsubject", locale.getLanguage());
        final String senderName = TextProperties.getTextProperty("mail.standard.sendername", locale.getLanguage());


        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

                for(UserProfileEntityDTO admin : userProfileService.findByRole(RoleName.SYS_ADMIN)){
                    message.addTo(admin.getEmail());
                }

                message.setSubject(subject);
                message.setFrom(fromEmail, senderName);
                Map<String,Object> model= new HashMap<>();

                model.put("suggestion", suggestion);
                model.put("link", createLinkToSuggestion(protocol, host));

                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/velocity/mail/new-article-suggestion.vm", model);
                message.setText(text, true);
            }
        };
        mailSender.send(preparator);
    }

    private String createLinkToSuggestion(String protocol, String host){
        return String.format("%s://%s/admin/sys/articlesuggestions", protocol, host);
    }
}
