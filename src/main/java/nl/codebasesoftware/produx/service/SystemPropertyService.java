package nl.codebasesoftware.produx.service;

import java.util.Calendar;

/**
 * User: rvanloen
 * Date: 1-2-13
 * Time: 22:18
 */
public interface SystemPropertyService {
    Calendar lastSolrUpdateDate();

    String findByKey(String key);
}
