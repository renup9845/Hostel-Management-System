package com.hostelMS.config;

import java.util.Properties;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.hostelMS.model.User;
import com.hostelMS.model.Room;

public class HibernateUtil {
	
	private static SessionFactory sesFactory;
	
	public static SessionFactory getSessionFactory() {
		
		if(sesFactory == null) {
			
			try {
				
				Configuration config =new Configuration();
				Properties pro = new Properties();
				
				pro.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				pro.put(Environment.URL, "jdbc:mysql://localhost:3306/hostelMS");
				pro.put(Environment.USER, "root");
				pro.put(Environment.PASS, "root");
				pro.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
				pro.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				pro.put(Environment.SHOW_SQL, "false");
				pro.put(Environment.HBM2DDL_AUTO, "update");
			
				config.setProperties(pro);
				config.addAnnotatedClass(User.class);
				config.addAnnotatedClass(Room.class);
				
				sesFactory = config.buildSessionFactory();
				
			
			
			
			}
			catch(Exception e) {
				
				e.printStackTrace();
				return null;
				
			}
			
		}
		return sesFactory;
	}
		
	public static Session getSession() {
			return getSessionFactory().openSession();
		
		
		}

	}
	
	


