package nl.codebasesoftware.produx.util;

import org.apache.log4j.Logger;

/**
 * User: rvanloen
 * Date: 31-12-12
 * Time: 11:43
 */
public class HttpUtil {

    private static Logger LOG = Logger.getLogger(HttpUtil.class);

    /*
    public static void writeBytesToResponse(HttpServletResponse response, byte[] bytes) {
        try {
            PrintWriter responseWriter = response.getWriter();

            int value = 0;
            for(int i = 0; i < bytes.length; i++) {
                char c = (char) value;
                responseWriter.write(c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    */


}
