package nl.codebasesoftware.produx.domain.dto.entity;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 16-8-13
 * Time: 8:32
 * To change this template use File | Settings | File Templates.
 */
public class RegionEntityDTO extends DomainEntityDTO {

    private Long id;
    private String name;

    @Override
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
