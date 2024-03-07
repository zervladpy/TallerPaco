package com.zervladpy.tallerpaco.Core.DAO;

import com.zervladpy.tallerpaco.Core.Entities.ITEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.Getter;

import java.util.List;

@Getter
public class DAO<T extends ITEntity> implements IDAO<T> {

    private final EntityManagerFactory emf;
    private final Class<T> entityClass;

    public DAO(EntityManagerFactory emf, Class<T> entityClass) {
        this.emf = emf;
        this.entityClass = entityClass;
    }

    @Override
    public T getById(int id) {
        EntityManager session = emf.createEntityManager();
        T entity = session.find(entityClass, id);
        session.close();
        return entity;
    }

    @Override
    public List<T> getAll() {
        EntityManager session = emf.createEntityManager();
        List<T> entities = session.createQuery("SELECT e FROM " + entityClass.getName() + " e", entityClass).getResultList();
        session.close();
        return entities;
    }

    @Override
    public void save(T entity) {
        EntityManager session = emf.createEntityManager();
        session.getTransaction().begin();
        session.merge(entity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveMany(List<T> entities) {
        EntityManager session = emf.createEntityManager();
        session.getTransaction().begin();
        for (T entity : entities) {
            session.merge(entity);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(T entity) {
        EntityManager session = emf.createEntityManager();
        session.getTransaction().begin();
        session.merge(entity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(T entity) {
        EntityManager session = emf.createEntityManager();
        session.getTransaction().begin();
        session.remove(entity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteById(int id) {
        EntityManager session = emf.createEntityManager();
        T entity = session.find(entityClass, id);
        session.getTransaction().begin();
        session.remove(entity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteAll() {
        EntityManager session = emf.createEntityManager();
        session.getTransaction().begin();
        session.createQuery("DELETE FROM " + entityClass.getName()).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
