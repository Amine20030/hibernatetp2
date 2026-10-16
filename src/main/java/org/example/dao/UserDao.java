package org.example.dao;

import org.example.entity.User;
import javax.persistence.EntityManager;
import java.util.List;

public class UserDao extends GenericDao<User> {

    public UserDao(EntityManager em) {
        super(User.class, em);
    }

    public List<User> findBySalleNom(String nomSalle) {
        return em.createQuery("SELECT u FROM User u WHERE u.salle.nom = :nomSalle", User.class)
                .setParameter("nomSalle", nomSalle)
                .getResultList();
    }
}