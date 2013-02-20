package nl.codebasesoftware.produx.dao;

import nl.codebasesoftware.produx.domain.SystemProperty;

/**
 * User: rvanloen
 * Date: 1-2-13
 * Time: 21:50
 */
public interface SystemPropertyDao extends GenericDao<SystemProperty> {
    SystemProperty findByKey(String key);
}
