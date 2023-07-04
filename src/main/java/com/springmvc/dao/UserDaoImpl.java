package com.springmvc.dao;

import com.springmvc.config.HibernateConfig;
import com.springmvc.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    Transaction transaction = null;

    @Override
    public void save(User user) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.persist(user);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public List<User> list() {
//        Session session = HibernateConfig.getSessionFactory().openSession();
//        EntityManager em = session.getEntityManagerFactory().createEntityManager();
//
//        String queryString = "FROM User";
//        TypedQuery<User> query = em.createQuery(queryString, User.class);
//        return query.getResultList();

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            List <User> users = session.createQuery("from User", User.class).list();
            return users;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }
}
