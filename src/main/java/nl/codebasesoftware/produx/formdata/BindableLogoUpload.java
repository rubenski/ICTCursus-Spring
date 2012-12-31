package nl.codebasesoftware.produx.formdata;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * User: rvanloen
 * Date: 28-12-12
 * Time: 3:07
 */
public class BindableLogoUpload {

	private CommonsMultipartFile fileData;

    public CommonsMultipartFile getFileData() {
        return fileData;
    }

    public void setFileData(CommonsMultipartFile fileData) {
        this.fileData = fileData;
    }
}