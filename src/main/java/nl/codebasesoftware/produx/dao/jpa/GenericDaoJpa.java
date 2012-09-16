package nl.codebasesoftware.produx.dao.jpa;

import nl.codebasesoftware.produx.dao.GenericDao;
import nl.codebasesoftware.produx.domain.DomainObject;
import org.springframework.beans.BeanUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


public class GenericDaoJpa<T extends DomainObject> implements GenericDao<T> {

	private Class<T> type;
	protected EntityManager entityManager;


    @PersistenceContext(name="mysqlPersistenceUnit")
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
    public void refresh(T object){
        entityManager.refresh(object);
    }

    @Override
    public void updateDetached(DomainObject object, String... ignoreFields) {
        DomainObject persisted = entityManager.find(object.getClass(), object.getId());
        BeanUtils.copyProperties(object, persisted, ignoreFields);
    }
}