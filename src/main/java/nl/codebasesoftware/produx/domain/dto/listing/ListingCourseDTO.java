package nl.codebasesoftware.produx.domain.dto.listing;

import nl.codebasesoftware.produx.domain.dto.entity.*;
import nl.codebasesoftware.produx.domain.dto.generic.WebVisitable;
import nl.codebasesoftware.produx.service.business.CourseUrl;

import java.util.*;

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

    @Override
    public String toString() {
        return String.format("%d : %s", id, name);
    }

    @Override
    public String getUrl() {
        return CourseUrl.createUrl(id, category.getName(), name);
    }
}
