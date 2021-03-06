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

package club.jmint.mifty.crossing;

import club.jmint.crossing.client.CrossingClient;
import club.jmint.crossing.client.config.ClientConfig;
import club.jmint.crossing.client.utils.CrossLog;
import club.jmint.mifty.wizard.Wizard;

public class CrossingWizard extends Wizard {
	private final CrossingClient cc = CrossingClient.getInstance();
	private ClientConfig config = null;

	public CrossingWizard(String name) {
		super(name);
	}
	
	public CrossingClient getCrossingClient(){
		return cc;
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public void init() {
		super.init();    	
	}

	@Override
	public void startup() {
		super.startup();
    	//Start Crossing Client
    	try{
    		cc.startup();
    	}catch(Exception e){
    		CrossLog.logger.error("Connect to crossing server failed.");
    		CrossLog.printStackTrace(e);
    	}
    	
    	CrossLog.logger.info("Connected to crossing server.");

	}

	@Override
	public void shutdown() {
		super.shutdown();
		//Stop Crossing client
		cc.shutdown();
		CrossLog.logger.info("Disconnected to crossing server.");
	}

}
