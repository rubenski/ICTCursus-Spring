package nl.codebasesoftware.produx.search.solrquery;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 6-8-13
 * Time: 14:45
 * To change this template use File | Settings | File Templates.
 */
public class SolrQueryStringBuilder {

    StringBuilder builder = new StringBuilder();
    boolean firstPart = true;

    public SolrQueryStringBuilder appendParameters(Collection<String> params){

        for (String param : params) {
            if(firstPart){
                builder.append("?");
                firstPart = false;
            } else {
                builder.append("&");
            }

            builder.append(param);
        }
        return this;
    }

    public SolrQueryStringBuilder appendSomething(String something){
        builder.append(something);
        return this;
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}
