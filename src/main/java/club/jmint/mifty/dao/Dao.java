/*
 * Copyright 2016 The Mifty Project
 *
 * The Mifty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package club.jmint.mifty.dao;

import java.io.File;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import club.jmint.mifty.log.MyLog;

public class Dao {
	//Database field types
	public static final int DB_FIELD_VALUE_NUM = 1;
	public static final int DB_FIELD_VALUE_CHAR = 2;
	public static final int DB_FIELD_VALUE_TIME = 3;
	public static final int DB_FIELD_VALUE_NULL = 4;
	
	
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			//            return new Configuration().configure().buildSessionFactory(
			//			    new StandardServiceRegistryBuilder().build() );
//			String udir = System.getProperty("user.dir");
//			String fdir = udir + File.separator+"conf"+File.separator+"hibernate.cfg.xml";
			Configuration cfg = new Configuration().configure(new File("conf/hibernate.cfg.xml"));  
			
			StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()  
					.applySettings(cfg.getProperties()).build(); 
			SessionFactory sf = cfg.buildSessionFactory(serviceRegistry); 
			MyLog.logger.info("DAO SessionFactory initialized.");
			return sf;
		}
		catch (Throwable ex) {
			MyLog.logger.error("DAO SessionFactory initialization failed.");
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void closeSessionFactory(){
		sessionFactory.close();
	}
	
	public static Session openSession(){
		return sessionFactory.openSession();
	}
	
	public static Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public static void closeSession(Session session){
		session.disconnect();
		session.close();
	}
	

	
}
