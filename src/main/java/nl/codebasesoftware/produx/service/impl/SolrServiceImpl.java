package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.domain.dto.entity.CourseEntityDTO;
import nl.codebasesoftware.produx.exception.ProduxServiceException;
import nl.codebasesoftware.produx.search.SolrServerFactory;
import nl.codebasesoftware.produx.service.SolrService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 9-8-13
 * Time: 21:56
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SolrServiceImpl implements SolrService {

    private SolrServerFactory solrServerFactory;
    private ConversionService conversionService;


    @Autowired
    public SolrServiceImpl(SolrServerFactory solrServerFactory,
                           ConversionService conversionService) {
        this.solrServerFactory = solrServerFactory;
        this.conversionService = conversionService;
    }

    @Override
    public QueryResponse search(SolrParams params) throws ProduxServiceException {

        QueryResponse response;

        try {
            response = solrServerFactory.getServer().query(params);
            solrServerFactory.getServer().query(new SolrQuery().addFilterQuery());
        } catch (SolrServerException e) {
            throw new ProduxServiceException("Solr server at couldn't be reached", e);
        }

        return response;
    }

    @Override
    public int addOrUpdate(Collection<CourseEntityDTO> courses) {
        List<SolrInputDocument> solrDocuments = new ArrayList<>();

        for (CourseEntityDTO indexableCourse : courses) {
            SolrInputDocument solrInputDocument = conversionService.convert(indexableCourse, SolrInputDocument.class);
            solrDocuments.add(solrInputDocument);
        }

        SolrServer server = solrServerFactory.getServer();

        UpdateResponse response = null;

        try {
            server.add(solrDocuments);
            response = server.commit();

        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }

        assert response != null;
        return response.getStatus();
    }


    @Override
    public int addOrUpdate(CourseEntityDTO course) {
        return addOrUpdate(Arrays.asList(course));
    }


}
