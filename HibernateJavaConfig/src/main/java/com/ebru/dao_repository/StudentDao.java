package com.ebru.dao_repository;

import com.ebru.model_entity.Student;
import com.ebru.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDao {

    public void saveStudent(Student student){

        Transaction transaction= null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
                session.save(student);
            transaction.commit();
        } catch (HibernateException e){
            System.out.println("Hata: "+ e);
            if(transaction != null) {
                transaction.rollback();
            }
        }
    }

   public List<Student> getStudents(){
       try (Session session = HibernateUtil.getSessionFactory().openSession()) {
          return session.createQuery("from Student", Student.class).list();
       } catch (HibernateException e){
          return null;
       }
   }

}
