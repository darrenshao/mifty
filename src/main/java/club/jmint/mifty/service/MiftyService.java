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

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.thrift.TException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import club.jmint.mifty.config.ConfigWizard;
import club.jmint.mifty.config.ServerConfig;
import club.jmint.mifty.dao.IDao;
import club.jmint.mifty.runtime.Constants;
import club.jmint.mifty.utils.CrossLog;
import club.jmint.crossing.specs.ErrorCode;
import club.jmint.crossing.specs.CrossException;
import club.jmint.crossing.specs.CrossingService;

public class MiftyService extends CrossingService implements IDao  {
	public MiftyService(){
		ServerConfig config = (ServerConfig)ConfigWizard.getConfig(Constants.CONFIG_SERVER);
		setSignType(config.getItem("server.sign.type"));
		setSignKey(config.getItem("server.sign.key"));
		setEncryptType(config.getItem("server.encrypt.type"));
		setEnKey(config.getItem("server.encrypt.key"));
		setDeKey(config.getItem("server.decrypt.key"));
		//CrossLog.logger.info("MiftyService init.");
	}
	
	public String callProxy(String method, String params, boolean isEncrypt) throws TException {
		//parse parameters and verify signature
		CrossLog.logger.debug("callProxy: " + method + "(in: " +params+")");
		JsonObject ip;
		try{
			ip = parseInputParams(params, isEncrypt);
		}catch(CrossException ce){
			return buildOutputByCrossException(ce);
		}
		
		//extract all parameters
		HashMap<String, String> inputMap = new HashMap<String, String>();
		Iterator<Entry<String, JsonElement>> it = ip.entrySet().iterator();
		Entry<String, JsonElement> en = null;
		String key;
		JsonElement je;
		while(it.hasNext()){
			en = it.next();
			key = en.getKey();
			je = en.getValue();
			if (je.isJsonArray()){
				inputMap.put(key, je.getAsJsonArray().toString());
				//System.out.println(je.getAsJsonArray().toString());
			}
			else if (je.isJsonNull()){
				inputMap.put(key, je.getAsJsonNull().toString());
				//System.out.println(je.getAsJsonNull().toString());
			}
			else if (je.isJsonObject()){
				inputMap.put(key, je.getAsJsonObject().toString());
				//System.out.println(je.getAsJsonObject().toString());
			}
			else if (je.isJsonPrimitive()){
				inputMap.put(key, je.getAsJsonPrimitive().getAsString());
				//System.out.println(je.getAsJsonPrimitive().toString());
			}
			else{
				//unkown type;
			}
		}

		//execute specific method
		Method[] ma = this.getClass().getMethods();
		int idx = 0;
		for (int i=0;i<ma.length;i++){
			if (ma[i].getName().equals(method)){
				idx = i;
				break;
			}
		}
		HashMap<String, String> outputMap = null;
		try{
			Method m = this.getClass().getDeclaredMethod(method, ma[idx].getParameterTypes());
			outputMap = (HashMap<String, String>) m.invoke(this, inputMap);
			CrossLog.logger.debug("callProxy: " + method + "() executed.");
		}
		catch(NoSuchMethodException nsm){
			CrossLog.logger.error("callProxy: " + method + "() not found.");
			CrossLog.printStackTrace(nsm);
			return buildOutputError(ErrorCode.CROSSING_ERR_INTERFACE_NOT_FOUND.getCode(), 
						ErrorCode.CROSSING_ERR_INTERFACE_NOT_FOUND.getInfo());			
		}
		catch(Exception e){
			CrossLog.logger.error("callProxy: " + method + "() executed with exception.");
			CrossLog.printStackTrace(e);
			if (e instanceof CrossException){
				return buildOutputByCrossException((CrossException)e);
			}
			else {
				return buildOutputError(ErrorCode.COMMON_ERR_UNKOWN.getCode(), 
						ErrorCode.COMMON_ERR_UNKOWN.getInfo());
			}
		}
		//if got error then return immediately
		String ec = outputMap.get("errorCode");
		String ecInfo = outputMap.get("errorInfo");
		if ((ec!=null) && (!ec.isEmpty())){
			return buildOutputError(Integer.parseInt(ec), ecInfo);
		}
		
		//build response parameters
		JsonObject op = new JsonObject();
		Iterator<Entry<String,String>> ito = outputMap.entrySet().iterator();
		Entry<String, String> eno = null;
		JsonParser jpo = new JsonParser();
		JsonElement jeo = null;
		while(ito.hasNext()){
			eno = ito.next();
			try{
				jeo = jpo.parse(eno.getValue());
			}catch(JsonSyntaxException e){
//				MyLog.logger.error("callProxy: Malformed output parameters["+eno.getKey()+"].");
//				return buildOutputError(ErrorCode.COMMON_ERR_PARAM_MALFORMED.getCode(), 
//						ErrorCode.COMMON_ERR_PARAM_MALFORMED.getInfo());
				//we do assume that it should be a common string
				jeo = new JsonPrimitive(eno.getValue());
			}
			op.add(eno.getKey(), jeo);
		}
		
		String output = null;
		try{
			output = buildOutputParams(op, isEncrypt);
		}catch(CrossException ce){
			return buildOutputByCrossException(ce);
		}
		CrossLog.logger.debug("callProxy: " + method + "(out: "+output+")");
		return output;
	}

	


}
