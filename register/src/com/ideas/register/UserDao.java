package com.ideas.register;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class UserDao {

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

	public static int register(User u){
       int i=0;
		//creating seession factory object
		SessionFactory factory= createSessionFactory();

		//creating session object
		Session session=factory.openSession();
		
		Transaction t=session.beginTransaction();
		//t.begin();
				
		i=(Integer)session.save(u);
				
		t.commit();
		session.close();
		return i;
	}
}
