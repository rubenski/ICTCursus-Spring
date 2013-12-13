package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.dto.entity.ClickEntityDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * User: rvanloen
 * Date: 13-12-13
 * Time: 13:16
 */
public interface LinkClickService {
    void registerClick(HttpServletRequest request);
}
