package com.ideas.example;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Iterator;
import java.util.List;


public class StoreData {

	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;

	public static SessionFactory createSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure();
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
				configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}
public static void main(String[] args) {
	
	//creating configuration object
	Configuration cfg=new Configuration();
	cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file
	
	//creating seession factory object
	SessionFactory factory= createSessionFactory();
	
	//creating session object
	Session session=factory.openSession();
	
	//creating transaction object
	Transaction t=session.beginTransaction();
		
	Employee e1=new Employee();
	e1.setId(115);
	e1.setFirstName("Prafulla");
	e1.setLastName("Girgaonkar");
	
	session.persist(e1);//persisting the object
	
	t.commit();//transaction is commited

    Transaction tx = null;
    try{
        tx = session.beginTransaction();
        List employees = session.createQuery("FROM Employee").list();
        for (Iterator iterator =
             employees.iterator(); iterator.hasNext();){
            Employee employee = (Employee) iterator.next();
            System.out.println("        id: " + employee.getId());
            System.out.println("First Name: " + employee.getFirstName());
            System.out.println(" Last Name: " + employee.getLastName());

        }
        tx.commit();
    }catch (HibernateException e) {
        if (tx!=null) tx.rollback();
        e.printStackTrace();
    }finally {
        session.close();
    }
	//session.close();
	
	System.out.println("successfully saved");
	
}
}
