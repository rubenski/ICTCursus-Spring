package nl.codebasesoftware.produx.search;

import org.apache.solr.common.SolrDocument;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 9-8-13
 * Time: 22:46
 * To change this template use File | Settings | File Templates.
 */
public class SolrDocumentIterator implements Iterator<SolrDocument> {


    @Override
    public boolean hasNext() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SolrDocument next() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void remove() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
