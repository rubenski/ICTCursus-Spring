package nl.codebasesoftware.produx.domain.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * User: rvanloen
 * Date: 9-5-13
 * Time: 10:14
 */
public class HighlightCourseResultDto {
    List<String> errors = new ArrayList<String>();
    boolean ok;

    public List<String> getErrors() {
        return errors;
    }

    public void addError(String error) {
        this.errors.add(error);
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
