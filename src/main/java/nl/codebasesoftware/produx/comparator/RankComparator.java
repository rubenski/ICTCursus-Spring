package nl.codebasesoftware.produx.comparator;

import java.util.Comparator;

/**
 * User: rvanloen
 * Date: 20-2-13
 * Time: 9:40
 */
public class RankComparator implements Comparator<RankOrderable> {

    @Override
    public int compare(RankOrderable o1, RankOrderable o2) {
        return o2.getDisplayRank() - o1.getDisplayRank();
    }
}
