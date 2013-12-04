package nl.codebasesoftware.produx.domain.support;

/**
 * User: rvanloen
 * Date: 30-11-13
 * Time: 15:05
 */
public enum CourseListingType {
    Eenvoudig(0),
    Uitgebreid(1);

    private int type;

    CourseListingType(int type){
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
