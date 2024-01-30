package com.ebru;

import com.ebru.model.Address;
import com.ebru.model.Customer;
import com.ebru.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AppMain {
    public static void main(String[] args) {

        Customer customer1 = new Customer();
        customer1.setFirstname("Donald");
        customer1.setLastname("Duck");

        Customer customer2 = new Customer("Austin", "Bert");


        Address address1 = new Address("Germany","Munich", "Abc");
        Address address2 = new Address("Turkey","Ankara", "Def");
        Address address3 = new Address("Canada","Toronto", "Xyz");
        Address address4 = new Address("USA","New York", "Rklm");
        Address address5 = new Address("Holland","Amsterdam", "EFM");


        Set<Address> addressList = new HashSet<>();
        addressList.addAll(Arrays.asList(address1, address2, address3));

        customer1.getAddress().add(address1);
        customer1.getAddress().add(address2);

        customer2.getAddress().add(address2);
        customer2.getAddress().add(address3);
        customer2.getAddress().add(address4);
        customer2.getAddress().add(address5);

        Customer customer3 = new Customer("Sally", "Egg");

        //--------------------------------------

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
                session.save(customer1);
                session.save(customer2);
                session.save(customer3);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            System.out.println("Error: " + e);
        } finally {
            session.close();
        }


    }
}
