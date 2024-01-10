package com.ebru.dao;

import com.ebru.model.Seller;
import com.ebru.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SellerDAO implements RealEstateDAO<Seller> {
    @Override
    public void saveOrUpdate(Seller seller){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(seller);
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
    public Seller getFindById(Long id){

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Seller.class, id);
        } catch (HibernateException e) {
            System.out.println("Hata: " + e);
            e.printStackTrace();
        }

        return null;
    }
    public List<Seller> getFindAll(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Seller", Seller.class).list();
        } catch (HibernateException e) {
            System.out.println("Hata: " + e);
            e.printStackTrace();
        }

        return null;
    }
    public void deleteFindById(Long id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Seller seller = session.get(Seller.class, id);
            if(seller !=null)
                session.delete(seller);

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
