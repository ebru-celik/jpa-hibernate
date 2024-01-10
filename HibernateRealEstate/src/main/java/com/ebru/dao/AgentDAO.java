package com.ebru.dao;

import com.ebru.model.Agent;
import com.ebru.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AgentDAO implements RealEstateDAO<Agent>{

    @Override
    public void saveOrUpdate(Agent agent){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(agent);
            transaction.commit();
        } catch (HibernateException e) {
            if(transaction!=null)
                transaction.rollback();

            System.out.println("Hata: " + e);
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    @Override
    public Agent getFindById(Long id){

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Agent.class, id);
        } catch (HibernateException e) {
            System.out.println("Hata: " + e);
            e.printStackTrace();
        }

        return null;
    }
    @Override
    public List<Agent > getFindAll(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Agent", Agent.class).list();
        } catch (HibernateException e) {
            System.out.println("Hata: " + e);
            e.printStackTrace();
        }

        return null;
    }
    @Override
    public void deleteFindById(Long id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Agent agent = session.get(Agent.class, id);
            if(agent !=null)
                session.delete(agent);

            transaction.commit();

        } catch (HibernateException e) {
            if(transaction!=null)
                transaction.rollback();

            System.out.println("Hata: " + e);
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
