package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.domain.dto.entity.RightEntityDTO;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * User: rvanloen
 * Date: 31-10-12
 * Time: 22:28
 */
@Entity
@Table(name = "Right")
public class Right implements DomainEntity, GrantedAuthority {

    private Long id;
    private String name;

    @Override
    @Id
    @GeneratedValue
    public final Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    @Transient
    public String getAuthority() {
        return name;
    }

    @Override
    @Transient
    public RightEntityDTO toDTO() {
        RightEntityDTO dto = new RightEntityDTO();
        dto.setId(id);
        dto.setName(name);
        return dto;
    }
}
