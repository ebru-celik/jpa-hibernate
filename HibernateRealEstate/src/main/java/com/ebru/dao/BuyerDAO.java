package com.ebru.dao;

import com.ebru.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.ebru.model.Buyer;
import java.util.List;

public class BuyerDAO implements RealEstateDAO<Buyer> {

    @Override
    public void saveOrUpdate(Buyer buyer){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(buyer);
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
    public Buyer getFindById(Long id){

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Buyer.class, id);
        } catch (HibernateException e) {
            System.out.println("Hata: " + e);
            e.printStackTrace();
        }

        return null;
    }
    @Override
    public List<Buyer> getFindAll(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Buyer", Buyer.class).list();
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
            Buyer buyer = session.get(Buyer.class, id);
            if(buyer !=null)
                session.delete(buyer);

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
