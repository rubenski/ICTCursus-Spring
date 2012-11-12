package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.RegionDao;
import nl.codebasesoftware.produx.domain.Region;
import nl.codebasesoftware.produx.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: rvanloen
 * Date: 11-11-12
 * Time: 14:19
 */
@Service
public class RegionServiceImpl implements RegionService {

    private RegionDao regionDao;

    @Autowired
    public RegionServiceImpl(RegionDao regionDao) {
        this.regionDao = regionDao;
    }

    @Override
    public List<Region> findRegionsBySubstring(String substring) {
        return regionDao.findRegionsBySubstring(substring);
    }
}
