package nl.codebasesoftware.produx.util.pdf;

import org.apache.fop.apps.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private FopConfigurator configurator;

    @Autowired
    PdfGenerator(FopConfigurator configurator){
        this.configurator = configurator;

        try {
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }

    }

    public File generate(File xslFile, File outputPdfFile) throws IOException, TransformerException, FOPException {


        // Capture input
        FileInputStream input = new FileInputStream(xslFile);
        StreamSource source = new StreamSource(input);

        // Prepare output destination
        OutputStream outStream = new BufferedOutputStream(new FileOutputStream(outputPdfFile));

        FOUserAgent userAgent = configurator.newUserAgent();
        Fop fop = configurator.getFactoryInstance().newFop(MimeConstants.MIME_PDF, userAgent, outStream);
        SAXResult output = new SAXResult(fop.getDefaultHandler());

        // Perform transformation
        transformer.transform(source, output);

        // Flush and close output
        outStream.flush();
        outStream.close();



        return outputPdfFile;
    }
}
