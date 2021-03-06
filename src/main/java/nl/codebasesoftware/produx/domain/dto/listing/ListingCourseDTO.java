package nl.codebasesoftware.produx.domain.dto.listing;

import nl.codebasesoftware.produx.domain.dto.entity.CategoryEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.DomainEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.TagEntityDTO;
import nl.codebasesoftware.produx.domain.dto.generic.WebVisitable;
import nl.codebasesoftware.produx.service.business.url.CourseUrl;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 14-8-13
 * Time: 17:21
 * To change this template use File | Settings | File Templates.
 */
public class ListingCourseDTO extends DomainEntityDTO implements WebVisitable {

    private Long id;
    private String name;
    private String listDescription;
    private ListingCompanyDTO company;
    private CategoryEntityDTO category;
    private boolean highlighted;
    private List<TagEntityDTO> tags;
    private String tagList;

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

    public ListingCompanyDTO getCompany() {
        return company;
    }

    public void setCompany(ListingCompanyDTO company) {
        this.company = company;
    }

    public CategoryEntityDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryEntityDTO category) {
        this.category = category;
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    public List<TagEntityDTO> getTags() {
        return tags;
    }

    public void setTags(List<TagEntityDTO> tags) {
        this.tags = tags;
    }

    public String getTagList() {
        return tagList;
    }

    public void setTagList(String tagList) {
        this.tagList = tagList;
    }

    @Override
    public String toString() {
        return String.format("%d : %s", id, name);
    }

    @Override
    public String getUrl() {
        return CourseUrl.createUrl(id, category.getName(), name);
    }
}
