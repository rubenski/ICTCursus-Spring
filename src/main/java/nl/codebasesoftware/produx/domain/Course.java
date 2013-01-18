package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.formdata.BindableCourse;

import javax.persistence.*;
import java.text.NumberFormat;
import java.util.*;

/**
 * User: rvanloen
 * Date: 14-7-12
 * Time: 21:06
 */
@Entity
public class Course implements DomainObject {

    private Long id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private String certificateText;
    private String duration;
    private Set<Region> regions = new HashSet<Region>();
    private Long price;
    private Company company;
    private Date lastUpdated;
    private Category category;
    private Set<Tag> tags;
    private Set<Experience> experiences;
    private boolean inCompany;
    private boolean certificate;


    @Override
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Lob
    @Column(nullable = false)
    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Lob
    @Column(nullable = false)
    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getDuration() {
        return duration;
    }

    @Column(nullable = false)
    public void setDuration(String duration) {
        this.duration = duration;
    }

    // TODO: is cascading save actions really necessary here? Not sure. Might be removed. Test please.
    @ManyToMany(cascade = {javax.persistence.CascadeType.ALL})
    public Set<Region> getRegions() {
        return regions;
    }

    public void setRegions(Set<Region> regions) {
        this.regions = regions;
    }

    @Column(nullable = false)
    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @ManyToOne
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @OneToMany (mappedBy = "course")
    public Set<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(Set<Experience> experiences) {
        this.experiences = experiences;
    }

    @ManyToOne
    @JoinColumn(nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToMany
    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Column(nullable = false)
    public boolean isCertificate() {
        return certificate;
    }

    public void setCertificate(boolean certificate) {
        this.certificate = certificate;
    }

    public String getCertificateText() {
        return certificateText;
    }

    public void setCertificateText(String certificateText) {
        this.certificateText = certificateText;
    }

    @Column(nullable = false)
    public boolean isInCompany() {
        return inCompany;
    }

    public void setInCompany(boolean inCompany) {
        this.inCompany = inCompany;
    }

    public BindableCourse toBindableCourse() {
        BindableCourse bindableCourse = new BindableCourse();
        bindableCourse.setCategory(category.getId());
        bindableCourse.setDuration(duration);
        bindableCourse.setId(id);
        bindableCourse.setLongDescription(longDescription);
        bindableCourse.setName(name);
        bindableCourse.setFormattedPrice(getFormattedPrice());
        bindableCourse.setRegions(getRegionIds());
        bindableCourse.setShortDescription(shortDescription);
        bindableCourse.setTags(getTagNames());
        bindableCourse.setCertificate(certificate);
        bindableCourse.setCertificateText(certificateText);
        bindableCourse.setInCompany(inCompany);
        return bindableCourse;
    }

    @Transient
    private List<Long> getRegionIds() {
        List<Long> idList = new ArrayList<Long>();
        Iterator<Region> regionsIterator = regions.iterator();
        for (int i = 0; regionsIterator.hasNext(); i++) {
            Region region = regionsIterator.next();
            idList.add(region.getId());
        }
        return idList;
    }

    @Transient
    private List<String> getTagNames() {
        List<String> names = new ArrayList<String>();
        Iterator<Tag> tagIterator = tags.iterator();
        for (int i = 0; tagIterator.hasNext(); i++) {
            Tag region = tagIterator.next();
            names.add(region.getName());
        }
        return names;
    }

    @Transient
    private String getFormattedPrice() {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        Double priceDouble = price / 100d;
        String s = numberFormat.format(priceDouble);
        return s;
    }

    @Transient
    public boolean equals(Object o){
        if(!(o instanceof Course)) return false;
        return ((Course) o).getId().equals(id);
    }

    @Transient
    public int hashCode(){
        return name.hashCode();
    }

}
