package nl.codebasesoftware.produx.domain.dto.entity;

import nl.codebasesoftware.produx.comparator.RankOrderable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 27-8-13
 * Time: 16:31
 * To change this template use File | Settings | File Templates.
 */
public class OptionCategoryEntityDTO extends DomainEntityDTO implements RankOrderable {

    private Long id;
    private String name;
    private int displayRank;
    private List<CourseOptionEntityDTO> options = new ArrayList<>();

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

    public List<CourseOptionEntityDTO> getOptions() {
        return options;
    }

    public void addOption(CourseOptionEntityDTO option) {
        this.options.add(option);
    }

    @Override
    public int getDisplayRank() {
        return displayRank;
    }

    public void setDisplayRank(int displayRank) {
        this.displayRank = displayRank;
    }
}
