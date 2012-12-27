package nl.codebasesoftware.produx.formdata;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
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
    private String duration;
    private List<Long> regions = new ArrayList<Long>();
    private long category;
    private List<String> tags = new ArrayList<String>();
    private String formattedPrice;

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
        return longDescription;
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

    public Long getPriceAsLong(){
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
}
