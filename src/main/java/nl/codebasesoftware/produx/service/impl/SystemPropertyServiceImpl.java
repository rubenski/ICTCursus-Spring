package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.SystemPropertyDao;
import nl.codebasesoftware.produx.domain.SystemProperty;
import nl.codebasesoftware.produx.service.SystemPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * User: rvanloen
 * Date: 1-2-13
 * Time: 22:19
 */
@Service
public class SystemPropertyServiceImpl implements SystemPropertyService {

    private SystemPropertyDao systemPropertyDao;

    @Autowired
    public SystemPropertyServiceImpl(SystemPropertyDao systemPropertyDao) {
        this.systemPropertyDao = systemPropertyDao;
    }

    @Override
    public Calendar lastSolrUpdateDate() {
        SystemProperty property = systemPropertyDao.findForKey("last.solr.update.date");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        try {
            if (property != null) {
                String lastSolrUpdateDate = property.getValue();
                sdf.parse(lastSolrUpdateDate);
            } else {
                sdf.parse("1970-01-01 00:00:00.000");
            }
        } catch(ParseException e){
            e.printStackTrace();
        }

        return sdf.getCalendar();
    }
}
