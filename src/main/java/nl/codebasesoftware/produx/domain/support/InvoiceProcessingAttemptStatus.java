package nl.codebasesoftware.produx.domain.support;

/**
 * User: rvanloen
 * Date: 13-12-13
 * Time: 20:44
 */
public enum InvoiceProcessingAttemptStatus {
    SUCCESS(1),
    FAIL(-1);

    private int status;

    InvoiceProcessingAttemptStatus(int status){
        this.status = status;
    }

    private int getStatus(){
        return this.status;
    }
}
