package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.domain.dto.entity.TimeEntityDTO;

import javax.persistence.*;

/**
 * User: rvanloen
 * Date: 2-2-13
 * Time: 19:50
 */
@Entity
public class Time implements DomainEntity {

    private Long id;
    private String name;
    private int displayRank;

    @Id
    @GeneratedValue
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDisplayRank() {
        return displayRank;
    }

    public void setDisplayRank(int displayRank) {
        this.displayRank = displayRank;
    }

    @Override
    @Transient
    public TimeEntityDTO toDTO(){
        TimeEntityDTO dto = new TimeEntityDTO();
        dto.setName(name);
        dto.setId(id);
        dto.setDisplayRank(displayRank);
        return dto;
    }
}
