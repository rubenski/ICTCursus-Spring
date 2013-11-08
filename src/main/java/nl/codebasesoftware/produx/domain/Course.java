package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.comparator.RankComparator;
import nl.codebasesoftware.produx.domain.dto.entity.*;
import nl.codebasesoftware.produx.domain.dto.listing.ListingCourseDTO;
import nl.codebasesoftware.produx.service.business.url.CourseUrl;
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
public class Course implements DomainEntity {

    private Long id;
    private String name;
    private String listDescription;
    private String longDescription;
    private String duration;
    private Long price;
    private Company company;
    private Calendar lastUpdated;
    private Calendar lastIndexed;
    private Calendar firstPublished;
    private Category category;
    private boolean certificate;
    private String certificateName;
    private Set<Tag> tags = new HashSet<>();
    private Set<Experience> experiences = new HashSet<>();
    private Set<CourseDate> dates = new HashSet<>();
    private Set<Time> times = new HashSet<>();
    private Set<CourseOption> options = new HashSet<>();
    private Set<Region> regions = new HashSet<>();
    private boolean published;
    private Set<HighlightedCoursePeriod> highlightedCoursePeriods = new HashSet<>();


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
    public String getListDescription() {
        return listDescription;
    }

    public void setListDescription(String listDescription) {
        this.listDescription = listDescription;
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
    public Calendar getFirstPublished() {
        return firstPublished;
    }

    public void setFirstPublished(Calendar firstPublished) {
        this.firstPublished = firstPublished;
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

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    // CascadeType.ALL results in unsaved dates being automatically saved
    // Orphan removal removes any orphaned records in the coursedate table after an update of Course
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "course_id")
    public Set<CourseDate> getDates() {
        return dates;
    }

    public void setDates(Set<CourseDate> dates) {
        this.dates = dates;
    }

    // TODO: Move this to CourseEntityDTO
    @Deprecated
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

    // Sets new dates on the course while retaining the original collection that was created by Hibernate (or another ORM)
    @Transient
    public void replaceDates(Set<CourseDate> newDates) {
        this.dates.clear();
        this.dates.addAll(newDates);
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

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course", orphanRemoval = true)
    public Set<HighlightedCoursePeriod> getHighlightedCoursePeriods() {
        return highlightedCoursePeriods;
    }

    public void setHighlightedCoursePeriods(Set<HighlightedCoursePeriod> highlightedOnCategories) {
        this.highlightedCoursePeriods = highlightedOnCategories;
    }


    @Transient
    public boolean equals(Object o) {
        if (!(o instanceof Course)) return false;
        return ((Course) o).getId().equals(id);
    }

    @Transient
    public String getAdminUrl() {
        return CourseUrl.createAdminUrl(id);
    }

    @Transient
    public int hashCode() {
        return name.hashCode();
    }


    @Transient
    public boolean wasPreviouslyPublished() {
        return firstPublished != null;
    }

    @Transient
    @Deprecated // use dto version
    public boolean isPublishable() {
        if (name == null) return false;
        if (listDescription == null) return false;
        if (longDescription == null) return false;
        if (duration == null) return false;
        if (price == null) return false;
        if (company == null) return false;
        if (category == null) return false;
        if (regions == null || regions.size() == 0) return false;
        return true;
    }

    @Transient
    public ListingCourseDTO toListingCourseDTO() {
        ListingCourseDTO listingCourse = new ListingCourseDTO();
        listingCourse.setId(id);
        listingCourse.setName(name);
        listingCourse.setCategory(category.toDTO());
        listingCourse.setCompany(company.toListingCompanyDTO());
        listingCourse.setListDescription(listDescription);
        return listingCourse;
    }

    @Transient
    @Override
    public CourseEntityDTO toDTO() {
        CourseEntityDTO dto = new CourseEntityDTO();
        dto.setCategory(category.toDTO());
        dto.setCertificate(certificate);
        dto.setCertificateName(certificateName);
        dto.setCompany(company.toDTO());
        dto.setDates(getDatesAsDTOs());
        dto.setDuration(duration);
        dto.setFirstPublished(firstPublished);
        dto.setHighlightedOnCategories(getHighlightedCourseOnCategoryAsDTOs());
        dto.setId(id);
        dto.setLastIndexed(lastIndexed);
        dto.setLastUpdated(lastUpdated);
        dto.setListDescription(listDescription);
        dto.setLongDescription(longDescription);
        dto.setName(name);
        dto.setOptionCategories(getOptionCategories());
        dto.setOptions(getOptionDTOs());
        dto.setPrice(price);
        dto.setPublished(published);
        dto.setRegions(getRegionsAsDTOs());
        dto.setTags(getTagsAsDTOs());
        dto.setTimes(getTimesAsDTOs());

        return dto;
    }

    @Transient
    private List<CourseDateEntityDTO> getDatesAsDTOs() {
        List<CourseDateEntityDTO> dateDTOs = new ArrayList<>();
        for (CourseDate date : dates) {
            dateDTOs.add(date.toDTO());
        }
        return dateDTOs;
    }

    @Transient
    private List<HighlightedCourseOnCategoryEntityDTO> getHighlightedCourseOnCategoryAsDTOs() {
        List<HighlightedCourseOnCategoryEntityDTO> highlightedCourseDTOs = new ArrayList<>();
        for (HighlightedCoursePeriod highlightedOnCategory : highlightedCoursePeriods) {
            highlightedCourseDTOs.add(highlightedOnCategory.toDTO());
        }
        return highlightedCourseDTOs;
    }

    @Transient
    private List<OptionCategoryEntityDTO> getOptionCategories() {
        // This method inverts options and categories and therefore returns categories instead of options
        List<OptionCategoryEntityDTO> cats = new ArrayList<>();
        Set<OptionCategory> uniqueCategories = new HashSet<>();
        for (CourseOption option : options) {
            uniqueCategories.add(option.getCategory());
        }

        for (OptionCategory uniqueCategory : uniqueCategories) {
            cats.add(uniqueCategory.toDTO());
        }

        return cats;
    }

    @Transient
    private List<CourseOptionEntityDTO> getOptionDTOs() {
        List<CourseOptionEntityDTO> optionList = new ArrayList<>();
        for (CourseOption option : options) {
            optionList.add(option.toDTO());
        }
        return optionList;
    }

    @Transient
    private List<TagEntityDTO> getTagsAsDTOs() {
        List<TagEntityDTO> tagEntityDTOs = new ArrayList<>();
        for (Tag tag : tags) {
            tagEntityDTOs.add(tag.toDTO());
        }
        Collections.sort(tagEntityDTOs, new Comparator<TagEntityDTO>() {
            @Override
            public int compare(TagEntityDTO o1, TagEntityDTO o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return tagEntityDTOs;
    }

    @Transient
    private List<RegionEntityDTO> getRegionsAsDTOs() {
        List<RegionEntityDTO> regionEntityDTOs = new ArrayList<>();
        for (Region region : regions) {
            regionEntityDTOs.add(region.toDTO());
        }
        Collections.sort(regionEntityDTOs, new RankComparator());
        return regionEntityDTOs;
    }

    @Transient
    private List<TimeEntityDTO> getTimesAsDTOs() {
        List<TimeEntityDTO> timeDTOs = new ArrayList<>();
        for (Time time : times) {
            timeDTOs.add(time.toDTO());
        }
        return timeDTOs;
    }


}
