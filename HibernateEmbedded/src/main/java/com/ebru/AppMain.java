package com.ebru;

import com.ebru.model.Address;
import com.ebru.model.Customer;
import com.ebru.model.Worker;
import com.ebru.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

public class AppMain {
    public static void main(String[] args) {

        Customer customer = new Customer();
        customer.setFirstName("A");
        customer.setLastName("B");
        customer.setCreateDate(new Date());

            Address address = new Address();
            address.setCity("Ankara");
            address.setDistrict("Çankaya");

        customer.setAddress(address);
//-------------------------------------------------------------------
        Customer customer2 = new Customer("C", "D");
        customer2.setCreateDate(new Date());

            Address address2 = new Address();
            address2.setCity("Sivas");
            address2.setDistrict("Kangal");

        customer2.setAddress(address2);
//-------------------------------------------------------------------
        Customer customer3 = new Customer("E", "F");
        customer3.setCreateDate(new Date());

            Address address3 = new Address();
            address3.setCity("Kars");
            address3.setDistrict("Sarıkamis");

        customer3.setAddress(address3);


//-------------------------------------------------------------------

        Worker worker = new Worker();
        worker.setFirstName("Y");
        worker.setLastName("M");
        worker.setCreateDate(new Date());

            Address address4 = new Address();
            address4.setCity("Adana");
            address4.setDistrict("Çinçin");

        worker.setAddress(address4);
//-------------------------------------------------------------------



        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction= null;

       try {
           transaction = session.beginTransaction();
               session.save(customer);
               session.save(customer2);
               session.save(customer3);
               session.save(worker);
           transaction.commit();
       } catch (HibernateException e){
           transaction.rollback();
           System.out.println("Error: "+ e);
       } finally {
           session.close();
       }

    }
}