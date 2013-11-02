package nl.codebasesoftware.produx.util.pdf;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

/**
 * User: rvanloen
 * Date: 2-11-13
 * Time: 1:37
 */
@Component
public class PdfGenerator {

    private Transformer transformer;

    PdfGenerator(){
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
    }

    public File generate(File xslFile, File outputPdfFile){

        try {
            FopFactory fopFactory = FopFactory.newInstance();

            // Capture input
            FileInputStream input = new FileInputStream(xslFile);
            StreamSource source = new StreamSource(input);

            // Prepare output destination
            OutputStream outStream = new FileOutputStream(outputPdfFile);
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, outStream);
            SAXResult output = new SAXResult(fop.getDefaultHandler());

            // Perform transformation
            transformer.transform(source, output);

            // Flush and close output
            outStream.flush();
            outStream.close();

        } catch (FOPException | TransformerException | IOException e) {
            e.printStackTrace();
        }

        return outputPdfFile;
    }
}
