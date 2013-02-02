package nl.codebasesoftware.produx.domain;

import javax.persistence.*;

/**
 * User: rvanloen
 * Date: 1-2-13
 * Time: 21:28
 */
@Entity
public class SystemProperty implements DomainObject{

    private Long id;
    private String key;
    private String value;

    @Override
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, unique = true, name = "_key")
    public String getKey() {
        return key;
    }


    public void setKey(String key) {
        this.key = key;
    }

    @Column(nullable = false)
    @Lob
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
