package nl.codebasesoftware.produx.comparator;

import nl.codebasesoftware.produx.domain.Category;

import java.util.Comparator;

/**
 * User: rvanloen
 * Date: 15-9-12
 * Time: 17:28
 */
public class CategoryAlphabetComparator implements Comparator<Category> {

    @Override
    public int compare(Category o1, Category o2) {
        return o2.getName().compareTo(o1.getName());
    }
}
