package org.example.services;

import org.example.dao.SalleDao;
import org.example.dao.UserDao;
import org.example.entity.Salle;
import org.example.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ServiceGestion {

    private EntityManagerFactory emf;
    private EntityManager em;
    private SalleDao sd;
    private UserDao ud;

    public ServiceGestion() {
        emf = Persistence.createEntityManagerFactory("hibernate_tp2");
        em = emf.createEntityManager();
        sd = new SalleDao(em);
        ud = new UserDao(em);
    }

    public void fermer() {
        em.close();
        emf.close();
    }

    public void ajouterSalle(Salle salle) {
        sd.create(salle);
    }

    public void ajouterUtilisateur(User utilisateur) {
        ud.create(utilisateur);
    }

    public List<Salle> listerSalles() {
        return sd.findAll();
    }

    public List<User> listerUtilisateurs() {
        return ud.findAll();
    }

    public List<User> utilisateursParSalle(String nomSalle) {
        return ud.findBySalleNom(nomSalle);
    }

    public List<Salle> sallesParCapaciteMin(int capaciteMin) {
        return sd.findByCapaciteMin(capaciteMin);
    }

    public Salle trouverSalle(Long id) {
        return sd.find(id);
    }

    public User trouverUtilisateur(Long id) {
        return ud.find(id);
    }

    public Salle modifierSalle(Salle salle) {
        return sd.update(salle);
    }

    public User modifierUtilisateur(User utilisateur) {
        return ud.update(utilisateur);
    }

    public void supprimerSalle(Salle salle) {
        sd.delete(salle);
    }

    public void supprimerUtilisateur(User utilisateur) {
        ud.delete(utilisateur);
    }
}