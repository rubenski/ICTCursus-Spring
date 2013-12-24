package nl.codebasesoftware.produx.service;

import javax.servlet.http.HttpServletRequest;

/**
 * User: rvanloen
 * Date: 24-12-13
 * Time: 16:45
 */
public interface RegisterLinkClickService {
    void registerClickOrIgnore(HttpServletRequest request);
}
