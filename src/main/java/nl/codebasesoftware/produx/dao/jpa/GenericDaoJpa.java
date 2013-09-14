package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.GenericDao;
import nl.codebasesoftware.produx.domain.DomainEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


public class GenericDaoJpa<T extends DomainEntity> implements GenericDao<T> {

    private Class<T> type;
    protected EntityManager entityManager;


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
    public void flush() {
        entityManager.flush();
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

    @SuppressWarnings("unchecked")
    protected T getSingleResult(Query query) {
        List<T> resultList = query.getResultList();
        if (resultList.size() > 1) {
            throw new IllegalArgumentException("Multiple results were found when expecting a single result");
        }
        return resultList.isEmpty() ? null : resultList.get(0);
    }


}