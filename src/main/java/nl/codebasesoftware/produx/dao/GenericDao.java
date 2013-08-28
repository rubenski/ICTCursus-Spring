package nl.codebasesoftware.produx.dao;

import nl.codebasesoftware.produx.domain.DomainEntity;

import java.util.List;

public interface GenericDao<T extends DomainEntity> {
	public T find(Long id);
	public List<T> findAll();
	public void persist(T object);
	public void delete(T object);
    public T merge(T object);
    public void refresh(T object);
    public void flush();
}