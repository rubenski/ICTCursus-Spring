package nl.codebasesoftware.produx.domain;

import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.*;
import java.util.*;

/**
 * User: rvanloen
 * Date: 14-7-12
 * Time: 21:06
 */
@Entity
@Configurable
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
    private Calendar lastUpdated;
    private Calendar lastIndexed;
    private Category category;
    private Set<Tag> tags;
    private Set<Experience> experiences;
    private boolean certificate;
    private Set<CourseDate> dates;
    private Set<Time> times;
    private Set<CourseOption> options;


    public Course() {
    }

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
    @ManyToMany(cascade = {javax.persistence.CascadeType.ALL}, fetch = FetchType.LAZY)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Calendar lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getLastIndexed() {
        return lastIndexed;
    }

    public void setLastIndexed(Calendar lastIndexed) {
        this.lastIndexed = lastIndexed;
    }

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    public Set<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(Set<Experience> experiences) {
        this.experiences = experiences;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToMany(fetch = FetchType.LAZY)
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

    @OneToMany(fetch = FetchType.LAZY)
    public Set<CourseDate> getDates() {
        return dates;
    }

    public void setDates(Set<CourseDate> dates) {
        this.dates = dates;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    public Set<Time> getTimes() {
        return times;
    }

    public void setTimes(Set<Time> times) {
        this.times = times;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    public Set<CourseOption> getOptions() {
        return options;
    }

    public void setOptions(Set<CourseOption> options) {
        this.options = options;
    }

    @Transient
    public List<String> getTagNames() {
        List<String> names = new ArrayList<String>();
        Iterator<Tag> tagIterator = tags.iterator();
        for (int i = 0; tagIterator.hasNext(); i++) {
            Tag tag = tagIterator.next();
            names.add(tag.getName());
        }
        return names;
    }

    @Transient
    public boolean equals(Object o) {
        if (!(o instanceof Course)) return false;
        return ((Course) o).getId().equals(id);
    }

    @Transient
    public int hashCode() {
        return name.hashCode();
    }

    @Transient
    public List<String> getRegionNames() {
        List<String> regionNames = new ArrayList<String>();
        for (Region region : regions) {
            regionNames.add(region.getName());
        }
        return regionNames;
    }

}
