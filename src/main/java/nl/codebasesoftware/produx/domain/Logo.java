package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.util.ImageUtil;
import nl.codebasesoftware.produx.util.support.ImageType;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.io.File;

/**
 * User: rvanloen
 * Date: 31-12-12
 * Time: 14:26
 */
@Embeddable
public class Logo {

    private String fileName;
    private String fileExtension;
    private ImageType fileType;

    @Transient
    private String directoryPath;



    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ImageType getFileType() {
        return fileType;
    }

    public void setFileType(ImageType fileType) {
        this.fileType = fileType;
    }

    @Transient
    public String getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    @Transient
    public String getFullName(){
        return String.format("%s.%s", fileName, fileExtension);
    }

    @Transient
    public String getFullPath(){
        return String.format("%s%s", directoryPath, getFullName());
    }

    @Transient
    public String getBase64EncodedImage(){
        return ImageUtil.encodeBase64(new File(getFullPath()));
    }

    @Transient
    public String getDataTypeString(){
        String typeString = fileType.toString();
        return typeString;
    }

}
