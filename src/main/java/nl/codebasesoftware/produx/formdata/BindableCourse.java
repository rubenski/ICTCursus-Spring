package nl.codebasesoftware.produx.formdata;

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
    private long[] regions;
    private Double price;
    private long category;
    private long[] tags;


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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public long[] getRegions() {
        return regions;
    }

    public void setRegions(long[] regions) {
        this.regions = regions;
    }

    public long[] getTags() {
        return tags;
    }

    public void setTags(long[] tags) {
        this.tags = tags;
    }
}
