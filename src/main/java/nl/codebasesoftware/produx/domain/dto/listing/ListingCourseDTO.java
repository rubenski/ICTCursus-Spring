package nl.codebasesoftware.produx.domain.dto.listing;

import nl.codebasesoftware.produx.domain.dto.entity.*;

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
    private ListingCompanyDTO company;
    private CategoryEntityDTO category;


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

}
