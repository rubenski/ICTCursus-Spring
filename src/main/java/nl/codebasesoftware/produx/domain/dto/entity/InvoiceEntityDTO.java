package nl.codebasesoftware.produx.domain.dto.entity;

import nl.codebasesoftware.produx.comparator.TimeComparable;
import nl.codebasesoftware.produx.comparator.TimeComparator;
import nl.codebasesoftware.produx.domain.InvoiceRecord;
import nl.codebasesoftware.produx.domain.InvoiceRecordType;
import nl.codebasesoftware.produx.util.StringUtil;

import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 27-8-13
 * Time: 17:23
 * To change this template use File | Settings | File Templates.
 */
public class InvoiceEntityDTO extends DomainEntityDTO {

    private Long id;
    private Calendar dateCreated;
    private CompanyEntityDTO company;
    private int serialNumber;
    private String invoiceNumber;
    private String lastInvoiceNumber;
    private int forYear;
    private int forMonth;
    private Set<InvoiceRecordEntityDTO> records = new TreeSet(new TimeComparator());

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Calendar dateCreated) {
        this.dateCreated = dateCreated;
    }

    public CompanyEntityDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyEntityDTO company) {
        this.company = company;
    }

    public Set<InvoiceRecordEntityDTO> getRecords() {
        return records;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getLastInvoiceNumber() {
        return lastInvoiceNumber;
    }

    public void setLastInvoiceNumber(String lastInvoiceNumber) {
        this.lastInvoiceNumber = lastInvoiceNumber;
    }

    public void setRecords(Set<InvoiceRecordEntityDTO> records) {
        this.records = records;
    }

    public void addRecord(InvoiceRecordEntityDTO record) {
        this.records.add(record);
    }

    public int getForYear() {
        return forYear;
    }

    public void setForYear(int forYear) {
        this.forYear = forYear;
    }

    public int getForMonth() {
        return forMonth;
    }

    public void setForMonth(int forMonth) {
        this.forMonth = forMonth;
    }

    public String getCreationDatePretty(){
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(dateCreated.getTime());
    }

    public String getMonthAsText(){
        return new DateFormatSymbols().getMonths()[forMonth-1];
    }

    public String getInvoiceTitle(){
        return String.format("%s %d", getMonthAsText(), forYear);
    }

    public String getInvoiceNumber(){
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber){
        this.invoiceNumber = invoiceNumber;
    }

    public String getFileName(String extension){
        return String.format("%s-%d%d.%s", getInvoiceNumber(), forMonth, forYear, extension);
    }

    public boolean hasRequestRecords(){
        for (InvoiceRecordEntityDTO record : records) {
            if(record.getType().equals(InvoiceRecordType.COURSE_REQUEST)){
                return true;
            }
        }
        return false;
    }

    private long getTotalRequestsAmountInCents(){
        long centsPrice = 0;
        for (InvoiceRecordEntityDTO record : records) {
            centsPrice += record.getPriceInCents();
        }
        return centsPrice;
    }

    public String getTotalRequestsAmountInEuros(){
        return StringUtil.centsToEuroPrice(getTotalRequestsAmountInCents());
    }

    public String getTotalInvoiceAmountExVat(){
        return StringUtil.centsToEuroPrice(getTotalRequestsAmountInCents());
    }

    public String getTotalInvoiceAmountIncVat(){
        return StringUtil.centsToEuroPrice(Math.round(getTotalRequestsAmountInCents() * 1.21));
    }

    public String getVat(){
        long vatAmountInCents = Math.round(getTotalRequestsAmountInCents() * 0.21);
        return StringUtil.centsToEuroPrice(vatAmountInCents);
    }

    public int getNumberOfClickRecords(){
        int count = 0;
        for (InvoiceRecordEntityDTO record : records) {
            if(record.getType().equals(InvoiceRecordType.CLICK_TO_EXTERNAL_SITE)){
                count++;
            }
        }
        return count;
    }

    public int getNumberOfCourseRequestRecords(){
        int count = 0;
        for (InvoiceRecordEntityDTO record : records) {
            if(record.getType().equals(InvoiceRecordType.COURSE_REQUEST)){
                count++;
            }
        }
        return count;
    }

    public String getInvoiceSummaryLine(){
        StringBuilder builder = new StringBuilder();
        int numberOfClickRecords = getNumberOfClickRecords();
        int numberOfCourseRequestRecords = getNumberOfCourseRequestRecords();
        if(numberOfCourseRequestRecords > 0){
            builder.append(numberOfCourseRequestRecords).append(" cursusaanvragen");
        }

        if(numberOfClickRecords > 0){
            if(numberOfCourseRequestRecords > 0){
                builder.append(" en ");
            }

            builder.append(numberOfClickRecords).append( "kliks");
        }

        builder.append(" via ictcursus.nl (zie specificatie)");
        return builder.toString();
    }
}