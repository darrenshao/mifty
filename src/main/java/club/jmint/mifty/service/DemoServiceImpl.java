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

package club.jmint.mifty.service;

import org.apache.thrift.TException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.google.gson.JsonObject;
import club.jmint.mifty.config.ConfigWizard;
import club.jmint.mifty.config.ServerConfig;
import club.jmint.mifty.dao.Dao;
import club.jmint.mifty.log.MyLog;
import club.jmint.mifty.runtime.Constants;
import club.jmint.mifty.service.gen.DemoService.Iface;

import club.jmint.crossing.exception.CrossException;
import club.jmint.crossing.specs.CrossingService;

public class DemoServiceImpl extends CrossingService implements Iface {
	public DemoServiceImpl(){
		ServerConfig config = (ServerConfig)ConfigWizard.getConfig(Constants.CONFIG_SERVER);
		setSignType(config.getItem("server.sign.type"));
		setSignKey(config.getItem("server.sign.key"));
		setEncryptType(config.getItem("server.encrypt.type"));
		setEnKey(config.getItem("server.encrypt.key"));
		setDeKey(config.getItem("server.decrypt.key"));
	}

	/**
	 * @param {"echo":"I am a DEMO","name":"DemoService"}
	 */
	public String demoSay(String params, boolean isEncrypt) throws TException {
		//parse parameters and verify signature
		MyLog.logger.debug("echo: " + params);
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
		MyLog.logger.info("\nname: " + name + "\nsay: " + echo);

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
		MyLog.logger.debug("sayHello: " + output);
		return output;
	}

}
