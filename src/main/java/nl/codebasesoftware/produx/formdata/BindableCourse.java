package nl.codebasesoftware.produx.formdata;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * User: rvanloen
 * Date: 19-12-12
 * Time: 15:24
 */
public class BindableCourse {

    private Long id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private String certificateText;
    private String duration;
    private List<Long> regions = new ArrayList<Long>();
    private long category;
    private List<String> tags = new ArrayList<String>();
    private String formattedPrice;
    private boolean certificate;
    private String certificateName;
    private List<Long> times = new ArrayList<Long>();
    private List<String> dates = new ArrayList<String>();
    private List<Long> options = new ArrayList<Long>();
    private Calendar firstPublished;
    private boolean published;
    private boolean publishable;
    private String linkToSite;


    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLongDescription() {
        return cleanRichText(longDescription);
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormattedPrice() {
        return formattedPrice;
    }

    public void setFormattedPrice(String formattedPrice) {
        this.formattedPrice = formattedPrice;
    }

    public Long getPriceAsLong() {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        Number number = null;
        try {
            number = numberFormat.parse(formattedPrice);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Double priceDouble = number.doubleValue();
        Long priceLong = Math.round(priceDouble * 100);
        return priceLong;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public long getCategory() {
        return category;
    }

    public void setCategory(long category) {
        this.category = category;
    }

    public List<Long> getRegions() {
        return regions;
    }

    public void setRegions(List<Long> regions) {
        this.regions = regions;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public boolean isCertificate() {
        return certificate;
    }

    public void setCertificate(boolean certificate) {
        this.certificate = certificate;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public String getCertificateText() {
        return certificateText;
    }

    public void setCertificateText(String certificateText) {
        this.certificateText = certificateText;
    }

    public List<Long> getTimes() {
        return times;
    }

    public void setTimes(List<Long> times) {
        this.times = times;
    }

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    public List<Long> getOptions() {
        return options;
    }

    public void setOptions(List<Long> options) {
        this.options = options;
    }

    public Calendar getFirstPublished() {
        return firstPublished;
    }

    public void setFirstPublished(Calendar firstPublished) {
        this.firstPublished = firstPublished;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isPublishable() {
        return publishable;
    }

    public void setPublishable(boolean publishable) {
        this.publishable = publishable;
    }

    public String getLinkToSite() {
        return linkToSite;
    }

    public void setLinkToSite(String linkToSite) {
        this.linkToSite = linkToSite;
    }

    private String cleanRichText(String richText) {
        if (richText != null) {
            richText = richText.replace("<p></p>", "");
            richText = richText.replace("<p>&nbsp;</p>", "");
        }
        return richText;
    }

}