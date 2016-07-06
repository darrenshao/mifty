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

package club.jmint.mifty.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.commons.configuration.PropertiesConfiguration;

import club.jmint.mifty.utils.CrossLog;

public class ServerConfig extends Config {
	private HashMap<String, String> scMap;
	
	public ServerConfig(String filePath){
		super("ServerConfig", filePath);
		init();
	}
	
	private void init(){
		if (scMap==null) {
			scMap = new HashMap<String, String>();
		}
	}
	
	@Override
	public ServerConfig loadConfig(){
		super.loadConfig();
		PropertiesConfiguration conf = loadPropertiesConfigFile(configFilePath);
		Iterator<String> it = conf.getKeys();
		String key = null;
		while(it.hasNext()){
			key = it.next();
			scMap.put(key, conf.getString(key));
		}
		return this;
	}
	
	public String getItem(String key){
		return scMap.get(key);
	}

	@Override
	public void print(){
		StringBuffer sb = new StringBuffer();
		sb.append(name+":\n");
		Iterator<Entry<String,String>> it = scMap.entrySet().iterator();
		Entry<String,String> en;
		while(it.hasNext()){
			en = it.next();
			sb.append(String.format("%-30s= %s\n", en.getKey(), en.getValue()));
		}
		CrossLog.logger.info(sb.toString());
	}

}

