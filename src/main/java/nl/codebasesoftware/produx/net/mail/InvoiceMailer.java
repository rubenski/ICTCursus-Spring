package nl.codebasesoftware.produx.net.mail;


import nl.codebasesoftware.produx.domain.dto.entity.InvoiceEntityDTO;
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
import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * User: rvanloen
 * Date: 27-3-13
 * Time: 11:41
 */
@Component
public class InvoiceMailer {

    @Resource
    private VelocityEngine velocityEngine;
    @Resource
    private JavaMailSender mailSender;
    @Resource
    private Properties properties;

    public void sendInvoiceEmail(final File pdf, final InvoiceEntityDTO invoice, Locale locale) throws MessagingException {


        final String fromEmail = properties.getProperty("email.from.address");
        final String host = properties.getProperty("site.host");
        final String protocol = properties.getProperty("site.protocol");
        final String subject = TextProperties.getTextProperty("invoice.mail.subject", locale.getLanguage());
        final String senderName = TextProperties.getTextProperty("mail.standard.sendername", locale.getLanguage());


        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
                message.setTo(invoice.getCompany().getEmail());
                message.setSubject(subject);
                message.setFrom(fromEmail, senderName);
                message.addAttachment(pdf.getName(), pdf);
                Map<String, Object> model = new HashMap<>();

                String adminUrl = createLink(protocol, host);

                model.put("invoice", invoice);
                model.put("adminUrl", adminUrl);

                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/velocity/mail/invoice.vm", model);
                message.setText(text, true);
            }
        };
        mailSender.send(preparator);
    }

    private String createLink(String protocol, String host) {
        return String.format("%s://%s/admin/invoices", protocol, host);
    }


}
