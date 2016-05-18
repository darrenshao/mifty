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

package com.shc.mifty.wizard;

import com.shc.crossing.log.MyLog;

/**
 * @author shc
 *
 */
public class Wizard implements IWizard {
	private String name;	
	
	public Wizard(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}

	public void init() {
		MyLog.logger.debug("Initializing " + name + ".");
	}

	public void startup() {
		// TODO Auto-generated method stub
		
	}

	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

}
