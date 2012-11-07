package nl.codebasesoftware.produx.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * User: rvanloen
 * Date: 31-10-12
 * Time: 22:28
 */
@Entity
@Table(name = "Right")
public class Right implements DomainObject, GrantedAuthority {

    private Long id;
    private Integer version;
    private String name;

    @Override
    @Id
    @GeneratedValue
    public final Long getId() {
        return id;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVersion(Integer version) {
        this.version = version;
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
}
