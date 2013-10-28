package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.dto.entity.InvoiceEntityDTO;

import java.util.List;
import java.util.Locale;

/**
 * User: rvanloen
 * Date: 27-10-13
 * Time: 13:05
 */
public interface InvoiceService {

    List<InvoiceEntityDTO> generateNewForMonth(int month);
}
