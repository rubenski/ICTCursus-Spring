package nl.codebasesoftware.produx.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * User: rvanloen
 * Date: 21-5-13
 * Time: 0:23
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class CourseRequestInvoiceRecord extends InvoiceRecord {


}
