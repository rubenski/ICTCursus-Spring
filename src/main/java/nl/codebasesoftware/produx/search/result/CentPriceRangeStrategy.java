package nl.codebasesoftware.produx.search.result;

/**
 * User: rvanloen
 * Date: 19-9-13
 * Time: 22:05
 */
public class CentPriceRangeStrategy implements RangeFilterLinkStrategy {

    @Override
    public String createLabel(String value, Object gap) {
        int intValue = Integer.parseInt(value);
        int intGap = Integer.parseInt(gap.toString());
        return String.format("%d-%d", intValue / 100, (intValue + intGap) / 100);
    }

    @Override
    public String createUrlToken(String field, String value, Object gap) {
        int intValue = Integer.parseInt(value);
        int intGap = Integer.parseInt(gap.toString());
        return String.format("%s:%d-%d", field, intValue, intValue + intGap);
    }
}
