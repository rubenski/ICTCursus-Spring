package nl.codebasesoftware.produx.domain.dto.entity;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 14-8-13
 * Time: 17:21
 * To change this template use File | Settings | File Templates.
 */
public class ListingCourseDTO extends DomainEntityDTO {

    private Long id;
    private String name;
    private String listDescription;
    private CompanyEntityDTO company;
    private CategoryEntityDTO category;
    private List<TagEntityDTO> tags = new ArrayList<>();
    private List<RegionEntityDTO> regions = new ArrayList<>();

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

    public CompanyEntityDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyEntityDTO company) {
        this.company = company;
    }

    public CategoryEntityDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryEntityDTO category) {
        this.category = category;
    }

    public List<TagEntityDTO> getTags() {
        return tags;
    }

    public void setTags(List<TagEntityDTO> tags) {
        this.tags = tags;
    }

    public List<RegionEntityDTO> getRegions() {
        return regions;
    }

    public void setRegions(List<RegionEntityDTO> regions) {
        this.regions = regions;
    }
}
