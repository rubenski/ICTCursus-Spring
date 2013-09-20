package nl.codebasesoftware.produx.search;

/**
 * User: rvanloen
 * Date: 18-9-13
 * Time: 17:09
 */
public class SolrIdName {

    private static final String ID_NAME_SEPARATOR = "---";

    private long id;
    private String name;

    public SolrIdName(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String createForSolr(long id, String name){
        return String.format("%d%s%s", id, ID_NAME_SEPARATOR, name);
    }

    public static boolean isIdAndName(String value){
        return value.contains(ID_NAME_SEPARATOR);
    }

    public static SolrIdName createFromSolr(String solrIdName){
        String[] parts = solrIdName.split(ID_NAME_SEPARATOR);
        return new SolrIdName(Long.parseLong(parts[0]), parts[1]);
    }

    public String getId() {
        return "" + id;
    }

    public String getName() {
        return name;
    }
}
