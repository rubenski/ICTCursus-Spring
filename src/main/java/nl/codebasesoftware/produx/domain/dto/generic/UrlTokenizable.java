package nl.codebasesoftware.produx.domain.dto.generic;

import nl.codebasesoftware.produx.search.FacetFilterLink;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 5-9-13
 * Time: 21:50
 * To change this template use File | Settings | File Templates.
 */
public interface UrlTokenizable {
    List<String> getUrlTokens();
}
