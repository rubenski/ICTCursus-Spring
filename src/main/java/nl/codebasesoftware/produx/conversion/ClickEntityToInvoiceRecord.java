package nl.codebasesoftware.produx.conversion;

import nl.codebasesoftware.produx.domain.InvoiceRecord;
import nl.codebasesoftware.produx.domain.InvoiceRecordType;
import nl.codebasesoftware.produx.domain.dto.entity.ClickEntityDTO;
import nl.codebasesoftware.produx.domain.optionlists.Prefixes;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * User: rvanloen
 * Date: 13-12-13
 * Time: 15:00
 */
public class ClickEntityToInvoiceRecord implements Converter<ClickEntityDTO, InvoiceRecord> {

    @Override
    public InvoiceRecord convert(ClickEntityDTO click) {

        InvoiceRecord record = new InvoiceRecord();
        record.setPriceInCents(click.getInvoicePriceInCents());
        record.setSourceRecordCreated(click.getCreated());
        record.setDescription(click.getInvoiceDescription());
        record.setType(InvoiceRecordType.CLICK_TO_EXTERNAL_SITE);

        Map<String, String> fields = new HashMap<>();
        fields.put("clickId", click.getId().toString());
        fields.put("ip", click.getIp());
        fields.put("userAgent", click.getUserAgent());
        fields.put("externalUrl", click.getExternalUrl());
        record.setSourceFields(fields);

        return record;

    }

}
