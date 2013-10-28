package nl.codebasesoftware.produx.util;

import nl.codebasesoftware.produx.domain.DomainEntity;
import nl.codebasesoftware.produx.domain.dto.entity.DomainEntityDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * User: rvanloen
 * Date: 27-10-13
 * Time: 13:50
 */
public class EntityCollectionConverter<Y extends DomainEntity, T  extends DomainEntityDTO> {

    public List<T> convert(Collection<Y> collection){
        List<T> dtoList = new ArrayList<>();
        for (Y domainObject : collection) {
            T dto = domainObject.toDTO();
            dtoList.add(dto);
        }
        return dtoList;
    }
}