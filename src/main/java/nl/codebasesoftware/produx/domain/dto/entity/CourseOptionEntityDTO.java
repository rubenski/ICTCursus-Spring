package nl.codebasesoftware.produx.domain.dto.entity;

import nl.codebasesoftware.produx.domain.OptionCategory;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 27-8-13
 * Time: 12:56
 * To change this template use File | Settings | File Templates.
 */
public class CourseOptionEntityDTO extends DomainEntityDTO {

    private Long id;
    private String displayName;
    private OptionCategoryEntityDTO category;
    private int displayRank;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public OptionCategoryEntityDTO getCategory() {
        return category;
    }

    public void setCategory(OptionCategoryEntityDTO category) {
        this.category = category;
    }

    public int getDisplayRank() {
        return displayRank;
    }

    public void setDisplayRank(int displayRank) {
        this.displayRank = displayRank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseOptionEntityDTO that = (CourseOptionEntityDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
