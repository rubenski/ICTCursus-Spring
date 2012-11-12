package nl.codebasesoftware.produx.dao;

import nl.codebasesoftware.produx.domain.Region;

import java.util.List;

/**
 * User: rvanloen
 * Date: 11-11-12
 * Time: 14:11
 */
public interface RegionDao extends GenericDao<Region> {
    List<Region> findRegionsBySubstring(String substring);
}
