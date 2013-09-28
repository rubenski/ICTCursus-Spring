package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.domain.dto.entity.RegionEntityDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * User: rvanloen
 * Date: 14-7-12
 * Time: 21:23
 */
@Entity
public class Region implements DomainEntity {

    private Long id;
    private String name;

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

    @Override
    public String toString() {
        return name;
    }

    public RegionEntityDTO toDTO() {
        RegionEntityDTO regionEntityDTO = new RegionEntityDTO();
        regionEntityDTO.setId(id);
        regionEntityDTO.setName(name);
        return regionEntityDTO;
    }
}
