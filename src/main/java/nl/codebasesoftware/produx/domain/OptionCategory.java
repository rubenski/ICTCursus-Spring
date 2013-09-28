package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.domain.dto.entity.OptionCategoryEntityDTO;
import nl.codebasesoftware.produx.formdata.BindableOptionCategory;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * User: rvanloen
 * Date: 18-2-13
 * Time: 16:50
 */
@Entity
public class OptionCategory implements DomainEntity {

    private Long id;
    private String name;
    private Set<CourseOption> options = new HashSet<>();
    private int displayRank;

    @Override
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", targetEntity = CourseOption.class, cascade = CascadeType.ALL)
    @OrderBy("displayRank ASC")
    public Set<CourseOption> getOptions() {
        return options;
    }

    public void setOptions(Set<CourseOption> options) {
        this.options = options;
    }

    @Column(nullable = false)
    public int getDisplayRank() {
        return displayRank;
    }

    public void setDisplayRank(int displayRank) {
        this.displayRank = displayRank;
    }


    public boolean equals(Object o) {
        return o instanceof OptionCategory && ((OptionCategory) o).id.equals(id);
    }

    public int hashCode() {
        return (int) (id * 17);
    }

    @Override
    @Transient
    @SuppressWarnings("unchecked")
    public OptionCategoryEntityDTO toDTO() {
        OptionCategoryEntityDTO dto = new OptionCategoryEntityDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setDisplayRank(displayRank);

        for (CourseOption option : options) {
            dto.addOption(option.toDTO());
        }

        return dto;
    }
}
