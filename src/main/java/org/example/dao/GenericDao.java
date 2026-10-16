package org.example.dao;

import javax.persistence.EntityTransaction;
import javax.persistence.EntityManager;

import java.util.List;

public class GenericDao<T> {

    private final Class<T> ec;
    protected EntityManager em;

    public GenericDao(Class<T> entityClass, EntityManager em) {
        this.ec = entityClass;
        this.em = em;
    }

    public void create(T entity) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(entity);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

    public T find(Object id) {
        return em.find(ec, id);
    }

    public T update(T entity) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            T merged = em.merge(entity);
            tx.commit();
            return merged;
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

    public void delete(T entity) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

    public List<T> findAll() {
        return em.createQuery("SELECT e FROM " + ec.getSimpleName() + " e", ec).getResultList();
    }
}