package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.formdata.BindableCourseOption;

import javax.persistence.*;

/**
 * User: rvanloen
 * Date: 18-2-13
 * Time: 15:39
 */
@Entity
public class CourseOption implements DomainObject {

    private Long id;
    private String displayName;
    private OptionCategory category;
    private int displayRank;

    @Override
    @Id
    @GeneratedValue
    public final Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(unique = true, nullable = false)
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @ManyToOne
    @JoinColumn(name = "optioncategory_id", nullable = false)
    public OptionCategory getCategory() {
        return category;
    }

    public void setCategory(OptionCategory category) {
        this.category = category;
    }

    @Column(nullable = false)
    public int getDisplayRank() {
        return displayRank;
    }

    public void setDisplayRank(int displayRank) {
        this.displayRank = displayRank;
    }

    public boolean equals(Object o){
        if(!(o instanceof CourseOption)) return false;
        return ((CourseOption)o).id.equals(id);
    }

    public int hashCode(){
        return (int) (id * 17);
    }

    public BindableCourseOption toBindable() {
        BindableCourseOption bindableCourseOption = new BindableCourseOption();
        bindableCourseOption.setId(id);
        bindableCourseOption.setDisplayName(displayName);
        bindableCourseOption.setDisplayRank(displayRank);
        return bindableCourseOption;
    }
}
