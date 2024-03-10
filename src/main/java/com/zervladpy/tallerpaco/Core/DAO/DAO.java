package com.zervladpy.tallerpaco.Core.DAO;

import com.zervladpy.tallerpaco.Core.Entities.ITEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NamedQuery;
import lombok.Getter;

import java.util.List;

@Getter
public class DAO<T extends ITEntity> implements IDAO<T> {

    private final EntityManager session;
    private final Class<T> entityClass;

    public DAO(EntityManager session, Class<T> entityClass) {
        this.session = session;
        this.entityClass = entityClass;
    }

    @Override
    public T getById(int id) {
        return session.find(entityClass, id);
    }

    @Override
    public T getById(String id) {
        return session.find(entityClass, id);
    }

    @Override
    public List<T> getAll() {
        return session.createQuery("SELECT e FROM " + entityClass.getName() + " e", entityClass).getResultList();
    }

    @Override
    public void save(T entity) {

        session.getTransaction().begin();

        session.merge(entity);

        session.getTransaction().commit();

    }

    @Override
    public void saveMany(List<T> entities) {
        session.getTransaction().begin();
        for (T entity : entities) {
            session.merge(entity);
        }
        session.getTransaction().commit();
    }

    @Override
    public void update(T entity) {
        session.getTransaction().begin();
        session.merge(entity);
        session.getTransaction().commit();
    }

    @Override
    public void delete(T entity) {
        session.getTransaction().begin();
        session.remove(entity);
        session.getTransaction().commit();
    }

    @Override
    public void deleteById(int id) {
        session.getTransaction().begin();
        T entity = session.find(entityClass, id);
        session.remove(entity);
        session.getTransaction().commit();
    }

    public void deleteById(String id) {
        session.getTransaction().begin();
        T entity = session.find(entityClass, id);
        session.remove(entity);
        session.getTransaction().commit();
    }

    @Override
    public void deleteAll() {
        session.getTransaction().begin();
        session.createQuery("DELETE FROM " + entityClass.getName() + " e").executeUpdate();
        session.getTransaction().commit();
    }
}
