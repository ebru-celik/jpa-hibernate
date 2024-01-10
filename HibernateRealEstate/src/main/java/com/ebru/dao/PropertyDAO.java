package com.ebru.dao;

import com.ebru.model.Property;
import com.ebru.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PropertyDAO implements RealEstateDAO<Property> {

    @Override
    public void saveOrUpdate(Property property){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(property);
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
    public Property getFindById(Long id){

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Property.class, id);
        } catch (HibernateException e) {
            System.out.println("Hata: " + e);
            e.printStackTrace();
        }

        return null;
    }
    @Override
    public List<Property> getFindAll(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Property ", Property.class).list();
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
            Property property = session.get(Property.class, id);
            if(property !=null)
                session.delete(property);

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
