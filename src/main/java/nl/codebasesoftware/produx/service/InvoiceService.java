package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.InvoiceProcessingAttempt;
import nl.codebasesoftware.produx.domain.dto.entity.InvoiceBatchEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.InvoiceEntityDTO;
import nl.codebasesoftware.produx.exception.ProduxServiceException;
import nl.codebasesoftware.produx.service.business.invoice.MonthAndYear;

import java.io.File;
import java.util.List;

/**
 * User: rvanloen
 * Date: 27-10-13
 * Time: 13:05
 */
public interface InvoiceService {

    File fromDisk(long id) throws ProduxServiceException;

    InvoiceBatchEntityDTO runInvoiceBatch(int month, int year) throws ProduxServiceException;

    List<InvoiceEntityDTO> findForCompany(long companyId, int year);
}
