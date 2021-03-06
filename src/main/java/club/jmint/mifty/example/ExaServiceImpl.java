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

package club.jmint.mifty.example;

import org.apache.thrift.TException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.google.gson.JsonObject;

import club.jmint.crossing.specs.CrossException;
import club.jmint.mifty.dao.Dao;
import club.jmint.mifty.example.gen.ExaService.Iface;
import club.jmint.mifty.service.MiftyService;
import club.jmint.mifty.utils.CrossLog;

public class ExaServiceImpl extends MiftyService implements Iface {

	/**
	 * @param {"hello":"Good morning!","name":"Darren"}
	 */
	public String sayHello(String params, boolean isEncrypt) throws TException {
		//parse parameters and verify signature
		CrossLog.logger.debug("sayHello: " + params);
		JsonObject ip;
		try{
			ip = parseInputParams(params, isEncrypt);
		}catch(CrossException ce){
			return buildOutputByCrossException(ce);
		}
		
		//validate parameters on business side
		String name,hello;
		try{
			name = getParamAsString(ip, "name");
			hello = getParamAsString(ip, "hello");
		}catch(CrossException ce){
			return buildOutputByCrossException(ce);
		}
		
		//do more checks here if you need.
		Session session = Dao.getSessionFactory().openSession();
		session.beginTransaction();
		session.createSQLQuery("insert into test.user (uid,name) values (3,'zhouguoxing'); ").executeUpdate();
		SQLQuery sq = session.createSQLQuery("select * from test.user;");
		System.out.println(sq.getFirstResult());
		session.createSQLQuery("delete from test.user where uid=1; ").executeUpdate();
		session.getTransaction().commit();
		session.close();
		
		//do your business logics
		System.out.println("name: " + name);
		System.out.println("hello: " + hello);
		CrossLog.logger.info("\nname: " + name + "\nsay: " + hello);

		String sentence = "Hi, " + name + " ," + hello;
		//do more things here.
		
		
		//build response parameters
		JsonObject op = new JsonObject();
		op.addProperty("sentence", sentence);
		String output = null;
		try{
			output = buildOutputParams(op, isEncrypt);
		}catch(CrossException ce){
			return buildOutputByCrossException(ce);
		}
		CrossLog.logger.debug("sayHello: " + output);
		return output;
	}

	/**
	 * @param {"echo":"I am back","name":"Darren"}
	 */
	public String echo(String params, boolean isEncrypt) throws TException {
		//parse parameters and verify signature
		CrossLog.logger.debug("echo: " + params);
		JsonObject ip;
		try{
			ip = parseInputParams(params, isEncrypt);
		}catch(CrossException ce){
			return buildOutputByCrossException(ce);
		}
		
		//validate parameters on business side
		String name,echo;
		try{
			name = getParamAsString(ip, "name");
			echo = getParamAsString(ip, "echo");
		}catch(CrossException ce){
			return buildOutputByCrossException(ce);
		}
		
		//do more checks here if you need.
		
		
		//do your business logics
		System.out.println("name: " + name);
		System.out.println("echo: " + echo);
		CrossLog.logger.info("\nname: " + name + "\nsay: " + echo);

		String sentence = "Hi, " + name + " ," + echo;
		//do more things here.
		
		
		//build response parameters
		JsonObject op = new JsonObject();
		op.addProperty("sentence", sentence);
		String output = null;
		try{
			output = buildOutputParams(op, isEncrypt);
		}catch(CrossException ce){
			return buildOutputByCrossException(ce);
		}
		CrossLog.logger.debug("sayHello: " + output);
		return output;
	}

}
