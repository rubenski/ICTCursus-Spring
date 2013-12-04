package nl.codebasesoftware.produx.domain.dto.entity;

import nl.codebasesoftware.produx.domain.dto.generic.WebVisitable;
import nl.codebasesoftware.produx.service.business.url.CourseUrl;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 9-8-13
 * Time: 22:00
 * To change this template use File | Settings | File Templates.
 */
public class CourseEntityDTO extends DomainEntityDTO implements WebVisitable {

    private Long id;
    private String name;
    private String listDescription;
    private String longDescription;
    private String duration;
    private Long price;
    private CompanyEntityDTO company;
    private Calendar lastUpdated;
    private Calendar lastIndexed;
    private Calendar firstPublished;
    private CategoryEntityDTO category;
    private boolean certificate;
    private String certificateName;
    private List<TagEntityDTO> tags = new ArrayList<>();
    private List<CourseDateEntityDTO> dates = new ArrayList<>();
    private List<TimeEntityDTO> times = new ArrayList<>();
    private List<RegionEntityDTO> regions = new ArrayList<>();
    private boolean published;
    private List<HighlightedCourseOnCategoryEntityDTO> highlightedOnCategories = new ArrayList<>();
    private List<OptionCategoryEntityDTO> optionCategories = new ArrayList<>();
    private List<CourseOptionEntityDTO> options = new ArrayList<>();

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getListDescription() {
        return listDescription;
    }

    public void setListDescription(String listDescription) {
        this.listDescription = listDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public CompanyEntityDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyEntityDTO company) {
        this.company = company;
    }

    public Calendar getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Calendar lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Calendar getLastIndexed() {
        return lastIndexed;
    }

    public void setLastIndexed(Calendar lastIndexed) {
        this.lastIndexed = lastIndexed;
    }

    public Calendar getFirstPublished() {
        return firstPublished;
    }

    public void setFirstPublished(Calendar firstPublished) {
        this.firstPublished = firstPublished;
    }

    public CategoryEntityDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryEntityDTO category) {
        this.category = category;
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

    public List<TagEntityDTO> getTags() {
        return tags;
    }

    public List<Long> getTagsIds() {
        List<Long> ids = new ArrayList<>();
        for (TagEntityDTO tag : tags) {
            ids.add(tag.getId());
        }
        return ids;
    }

    public void setTags(List<TagEntityDTO> tags) {
        this.tags = tags;
    }

    public List<CourseDateEntityDTO> getDates() {
        return dates;
    }

    public void setDates(List<CourseDateEntityDTO> dates) {
        this.dates = dates;
    }

    public List<TimeEntityDTO> getTimes() {
        return times;
    }

    public void setTimes(List<TimeEntityDTO> times) {
        this.times = times;
    }

    public List<RegionEntityDTO> getRegions() {
        return regions;
    }

    public void setRegions(List<RegionEntityDTO> regions) {
        this.regions = regions;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public List<HighlightedCourseOnCategoryEntityDTO> getHighlightedOnCategories() {
        return highlightedOnCategories;
    }

    public void setHighlightedOnCategories(List<HighlightedCourseOnCategoryEntityDTO> highlightedOnCategories) {
        this.highlightedOnCategories = highlightedOnCategories;
    }

    public List<OptionCategoryEntityDTO> getOptionCategories() {
        return optionCategories;
    }

    public void setOptionCategories(List<OptionCategoryEntityDTO> optionCategories) {
        this.optionCategories = optionCategories;
    }

    public List<CourseOptionEntityDTO> getOptions() {
        return options;
    }

    public void setOptions(List<CourseOptionEntityDTO> options) {
        this.options = options;
    }

    public List<String> getRegionNames() {
        List<String> regionNames = new ArrayList<String>();
        for (RegionEntityDTO region : regions) {
            regionNames.add(region.getName());
        }
        return regionNames;
    }


    public List<String> getRegionNamesAndIds() {
        List<String> regionsAndIds = new ArrayList<>();
        for (RegionEntityDTO region : regions) {
            regionsAndIds.add(region.getSolrIdName());
        }
        return regionsAndIds;
    }

    public List<Long> getRegionIds() {
        List<Long> regionIds = new ArrayList<>();
        for (RegionEntityDTO region : regions) {
            regionIds.add(region.getId());
        }
        return regionIds;
    }


    public List<String> getTagNames() {
        List<String> names = new ArrayList<String>();
        Iterator<TagEntityDTO> tagIterator = tags.iterator();
        for (int i = 0; tagIterator.hasNext(); i++) {
            TagEntityDTO tag = tagIterator.next();
            names.add(tag.getName());
        }
        return names;
    }

    public List<String> getTagNamesAndIds() {
        List<String> tagNamesAndIds = new ArrayList<>();
        for (TagEntityDTO tag : tags) {
            tagNamesAndIds.add(tag.getSolrIdName());
        }
        return tagNamesAndIds;
    }

    public String getEuroPrice() {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        return format.format(price / 100);
    }

    @Override
    public String getUrl() {
        return CourseUrl.createUrl(id, category.getName(), name);
    }

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

}
