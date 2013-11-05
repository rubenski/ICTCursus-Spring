package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.CourseRequestEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.InvoiceEntityDTO;
import nl.codebasesoftware.produx.exception.ProduxServiceException;

import java.io.File;
import java.util.List;
import java.util.Locale;

/**
 * User: rvanloen
 * Date: 27-10-13
 * Time: 13:05
 */
public interface InvoiceService {

    // 1 tonen van disk
    // 1 opnieuw genereren en vervangen op disk (in geval van mutatie in afgesloten maand)
    // list facturen van bedrijf tonen
    // genereren voor alle bedrijven en bewaren op disk

    File generateNewVersionForMonth(long companyId, int month);
    
    void saveAsNewVersion(InvoiceEntityDTO invoice);

    File fromDisk(String invoiceNumber) throws ProduxServiceException;

}
