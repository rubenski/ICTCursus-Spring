package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.Region;

import java.util.List;

/**
 * User: rvanloen
 * Date: 11-11-12
 * Time: 14:18
 */
public interface RegionService {
    List<Region> findRegionsBySubstring(String substring);
    List<Region> findAll();
}
