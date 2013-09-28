package nl.codebasesoftware.produx.search.result;

import nl.codebasesoftware.produx.domain.dto.listing.ListingCourseDTO;
import nl.codebasesoftware.produx.search.criteria.SearchCriteria;
import nl.codebasesoftware.produx.util.StringUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * User: rvanloen
 * Date: 24-9-13
 * Time: 10:06
 */
public class ResultListing {

    private SearchResult searchResult;
    private SearchCriteria criteria;
    private String filters;


    private ResultListing() {
    }


    public static class Builder {

        private SearchResult searchResult;
        private SearchCriteria criteria;
        private String filters;
        private Integer page;

        public Builder setSearchResult(SearchResult result) {
            this.searchResult = result;
            return this;
        }

        public Builder setCriteria(SearchCriteria criteria) {
            this.criteria = criteria;
            return this;
        }

        public Builder setFilters(String filters) {
            this.filters = filters;
            return this;
        }


        public ResultListing build() {
            ResultListing listing = new ResultListing();

            if (this.criteria == null) {
                throw new IllegalArgumentException("Please set criteria before building a result listing. It is required");
            }

            if (this.searchResult == null) {
                throw new IllegalArgumentException("Please set search result before building a result listing. It is required");
            }

            listing.searchResult = searchResult;
            listing.criteria = criteria;
            listing.filters = filters;

            return listing;
        }

    }


    public Collection<ListingCourseDTO> getCourses() {
        return searchResult.getCourses();
    }

    public List<String> getPagingUrls() {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < searchResult.getNumberOfResultPages(); i++) {
            String link = "/" + searchResult.getBasePath();

            if (!StringUtil.isNullOrEmpty(filters)) {
                link += "/" + filters;
            }

            if (i > 0) {
                link += "/p" + i;
            }

            if (criteria.hasQuery()) {
                try {
                    link += "?for=" + URLEncoder.encode(criteria.getQuery(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            list.add(link);
        }

        return list;
    }

    public boolean hasPaging() {
        return searchResult.getNumberOfResultPages() > 0;
    }


}
