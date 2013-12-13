package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.Click;
import nl.codebasesoftware.produx.domain.dto.entity.ClickEntityDTO;
import nl.codebasesoftware.produx.service.business.invoice.MonthAndYear;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * User: rvanloen
 * Date: 13-12-13
 * Time: 13:16
 */
public interface LinkClickService {
    void registerClickOrIgnore(HttpServletRequest request);
    List<ClickEntityDTO> findForCompanyAndMonth(long companyId, MonthAndYear monthAndYear);
}