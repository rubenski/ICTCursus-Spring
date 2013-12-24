package nl.codebasesoftware.produx.net.mail;

import nl.codebasesoftware.produx.domain.dto.entity.ArticleSuggestionEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.UserProfileEntityDTO;
import nl.codebasesoftware.produx.domain.optionlists.RoleName;
import nl.codebasesoftware.produx.properties.Properties;
import nl.codebasesoftware.produx.service.UserProfileService;
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
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * User: rvanloen
 * Date: 22-12-13
 * Time: 16:39
 */
@Component
public class BudgetWarningMailer {


    @Resource
    private VelocityEngine velocityEngine;
    @Resource
    private JavaMailSender mailSender;
    @Resource
    private Properties properties;
    @Resource
    private UserProfileService userProfileService;

    public void sendBudgetWarningMail(final CompanyEntityDTO company, Locale locale) throws MessagingException {


        final String fromEmail = properties.getProperty("email.from.address");
        final String host = properties.getProperty("site.host");
        final String protocol = properties.getProperty("site.protocol");
        final String subject = TextProperties.getTextProperty("budget.warning.mailsubject", locale.getLanguage());
        final String senderName = TextProperties.getTextProperty("mail.standard.sendername", locale.getLanguage());


        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

                // Send the warning to all users within the company
                for (UserProfileEntityDTO profile : userProfileService.findForCompany(company)) {
                    message.addTo(profile.getEmail());
                }

                message.setSubject(subject);
                message.setFrom(fromEmail, senderName);
                Map<String,Object> model= new HashMap<>();

                model.put("company", company);
                model.put("link", createLinkToCompanySettings(protocol, host));

                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/velocity/mail/budget-warning.vm", model);
                message.setText(text, true);
            }
        };

        mailSender.send(preparator);
    }

    private String createLinkToCompanySettings(String protocol, String host){
        return String.format("%s://%s/admin/company/settings", protocol, host);
    }
}
