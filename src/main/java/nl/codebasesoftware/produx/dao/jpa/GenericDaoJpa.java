package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.GenericDao;
import nl.codebasesoftware.produx.domain.DomainObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;



public class GenericDaoJpa<T extends DomainObject> implements GenericDao<T> {

    private Class<T> type;
    protected EntityManager entityManager;
    private static final Logger LOG = LoggerFactory.getLogger(GenericDaoJpa.class);


    @PersistenceContext(name = "mysqlPersistenceUnit")
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public GenericDaoJpa(Class<T> type) {
        super();
        this.type = type;
    }

    @Override
    public T find(Long id) {
        if (id == null) {
            return null;
        } else {
            return entityManager.find(type, id);
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return entityManager.createQuery(String.format("from %s", type.getName())).getResultList();
    }

    @Override
    public void persist(T object) {
        entityManager.persist(object);
    }

    @Override
    public void delete(T object) {
        entityManager.remove(object);
    }

    @Override
    public T merge(T object) {
        return entityManager.merge(object);

    }

    @Override
    public void refresh(T object) {
        entityManager.refresh(object);
    }

    // Replaces silly getSingleResult behaviour (throws an exception when nothing is found)
    protected T getSingleResult(Query query) {
        List<T> resultList = query.getResultList();
        if(resultList.size() > 1){
            LOG.warn(String.format("Multiple results were found when expecting a single result for query \n '%s' \n", query));
        }
        return resultList.isEmpty() ? null : resultList.get(0);
    }


}