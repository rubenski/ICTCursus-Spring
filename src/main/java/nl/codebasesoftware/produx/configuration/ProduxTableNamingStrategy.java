package nl.codebasesoftware.produx.configuration;

import org.hibernate.cfg.ImprovedNamingStrategy;

/**
 * User: rvanloen
 * Date: 2-11-12
 * Time: 22:31
 */
public class ProduxTableNamingStrategy extends ImprovedNamingStrategy {

    private final String PREFIX = "px_";

    @Override
    public String tableName(String tableName) {
        String name = super.tableName(tableName);
        return (PREFIX + name).toLowerCase();
    }

    @Override
    public String classToTableName(String className) {
        return (PREFIX + className).toLowerCase();
    }

    @Override
    public String collectionTableName(String ownerEntity, String ownerEntityTable, String associatedEntity, String associatedEntityTable, String propertyName) {

        String name = "";

        if (associatedEntity != null) {
            name = PREFIX + ownerEntityTable + "2" + associatedEntityTable;
        } else {
            // @Embeddables don't have their own entity, therefore we base the name on the property name
            name = PREFIX + propertyName;
        }

        return name.toLowerCase();
    }

    @Override
    public String foreignKeyColumnName(String propertyName, String propertyEntityName, String propertyTableName, String referencedColumnName) {
        return propertyTableName.toLowerCase() + "_id";
    }
}
