package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;

/**
 * User: rvanloen
 * Date: 24-12-13
 * Time: 16:33
 */
public interface BudgetWarningService {
    void checkBudgetAndSendWarningOrIgnore(CompanyEntityDTO company);
}
