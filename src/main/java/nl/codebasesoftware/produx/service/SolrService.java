package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.dto.entity.CourseEntityDTO;
import nl.codebasesoftware.produx.exception.ProduxServiceException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.SolrParams;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 9-8-13
 * Time: 21:57
 * To change this template use File | Settings | File Templates.
 */
public interface SolrService {
    QueryResponse search(SolrParams params) throws ProduxServiceException;

    int addOrUpdate(Collection<CourseEntityDTO> courses);

    int addOrUpdate(CourseEntityDTO course);
}
