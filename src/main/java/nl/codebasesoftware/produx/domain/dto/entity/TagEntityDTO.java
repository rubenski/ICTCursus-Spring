package nl.codebasesoftware.produx.domain.dto.entity;

import java.io.Serializable;

/**
 * User: rvanloen
 * Date: 21-12-12
 * Time: 16:45
 */
public class TagEntityDTO extends DomainEntityDTO {
    private Long id;
    private String name;

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
}
