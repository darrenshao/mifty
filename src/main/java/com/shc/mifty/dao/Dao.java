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

package com.shc.mifty.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.shc.mifty.log.MyLog;

public class Dao {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			//            return new Configuration().configure().buildSessionFactory(
			//			    new StandardServiceRegistryBuilder().build() );

			Configuration cfg = new Configuration().configure();  
			StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()  
					.applySettings(cfg.getProperties()).build();  
			return cfg.buildSessionFactory(serviceRegistry); 
		}
		catch (Throwable ex) {
			MyLog.logger.error("Initial SessionFactory creation failed.");
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
