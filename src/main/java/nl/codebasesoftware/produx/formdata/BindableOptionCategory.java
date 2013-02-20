package nl.codebasesoftware.produx.formdata;

import java.util.Set;
import java.util.TreeSet;

/**
 * User: rvanloen
 * Date: 18-2-13
 * Time: 17:45
 */
public class BindableOptionCategory {

    private String displayName;
    private Set<BindableCourseOption> options = new TreeSet<BindableCourseOption>(new RankComparator());

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Set<BindableCourseOption> getOptions() {
        return options;
    }

    public void addOption(BindableCourseOption option){
        options.add(option);
    }

    public boolean equals(Object o){
        if(!(o instanceof BindableOptionCategory)) return false;
        return ((BindableOptionCategory) o).displayName.equals(displayName);
    }

    public int hashCode(){
        return displayName.hashCode();
    }
}
