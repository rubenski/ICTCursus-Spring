package nl.codebasesoftware.produx.formdata;

/**
 * User: rvanloen
 * Date: 18-2-13
 * Time: 18:07
 */
public class BindableCourseOption implements RankOrderable {

    private Long id;
    private String displayName;
    private int displayRank;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDisplayRank() {
        return displayRank;
    }

    public void setDisplayRank(int displayRank) {
        this.displayRank = displayRank;
    }

    public boolean equals(Object o){
        if(!(o instanceof BindableCourseOption)) return false;
        return ((BindableCourseOption) o).id.equals(id);
    }

    public int hashCode(){
        return (int) (id * 17);
    }
}
