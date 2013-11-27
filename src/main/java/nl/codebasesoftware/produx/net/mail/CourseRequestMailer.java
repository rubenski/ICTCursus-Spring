package nl.codebasesoftware.produx.net.mail;

import nl.codebasesoftware.produx.domain.dto.entity.CourseRequestEntityDTO;
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
 * Date: 16-10-13
 * Time: 18:54
 */
@Component
public class CourseRequestMailer {

    @Resource
    private VelocityEngine velocityEngine;
    @Resource
    private JavaMailSender mailSender;
    @Resource
    private Properties properties;
    @Resource
    private UserProfileService userProfileService;

    public void sendCourseRequestMail(final CourseRequestEntityDTO request, Locale locale) throws MessagingException {


        final String fromEmail = properties.getProperty("email.from.address");
        final String host = properties.getProperty("site.host");
        final String protocol = properties.getProperty("site.protocol");
        final String subject = TextProperties.getTextProperty("courserequest.email.subject", locale.getLanguage());
        final String senderName = TextProperties.getTextProperty("mail.standard.sendername", locale.getLanguage());


        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

                for(UserProfileEntityDTO admin : userProfileService.findByRole(RoleName.SYS_ADMIN)){
                    message.addBcc(admin.getEmail());
                }

                message.addTo(request.getCourse().getCompany().getEmail());
                message.setSubject(subject);
                message.setFrom(fromEmail, senderName);
                Map<String,Object>  model= new HashMap<>();

                model.put("request", request);
                model.put("linkToRequest", createLinkToRequest(protocol, host, request.getId()));
                model.put("linkToCourse", createLinkToCourse(protocol, host, request));

                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/velocity/mail/course-request.vm", model);
                message.setText(text, true);
            }
        };
        mailSender.send(preparator);
    }

    private String createLinkToRequest(String protocol, String host, Long id){
        return String.format("%s://%s/admin/courserequests/%d", protocol, host, id);
    }

    private String createLinkToCourse(String protocol, String host, CourseRequestEntityDTO request){
        return String.format("%s://%s%s", protocol, host, request.getCourse().getUrl());
    }

}
