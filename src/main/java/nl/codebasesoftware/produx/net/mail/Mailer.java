package nl.codebasesoftware.produx.net.mail;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * User: rvanloen
 * Date: 7-11-12
 * Time: 17:50
 */
public class Mailer {



    public static void sendHtmlEmail() throws EmailException {
        // Create the email message
        HtmlEmail email = new HtmlEmail();
        email.setHostName("mail.mirabeau.nl");

        email.addTo("rvanloen@mirabeau.nl", "John Doe");
        email.setFrom("ruben@example.com", "Me");
        email.setSubject("Test email with inline image");

        // embed the image and get the content id
        URL url = null;
        try {
            url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        String cid = email.embed(url, "Apache logo");

        // set the html message
        email.setHtmlMsg("<html>The apache logo - <img src=\"cid:" + cid + "\"></html>");

        // set the alternative message
        email.setTextMsg("Your email client does not support HTML messages");

        // send the email
        email.send();


    }
}
