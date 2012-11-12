package nl.codebasesoftware.produx.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: rvanloen
 * Date: 14-7-12
 * Time: 21:23
 */
@Entity
@XmlRootElement(name = "region")
public class Region implements DomainObject {

    private Long id;
    private String name;

    @Override
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    @XmlElement
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }
}
