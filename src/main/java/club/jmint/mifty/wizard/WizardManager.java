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

package club.jmint.mifty.wizard;

import java.util.ArrayList;
import java.util.Iterator;

import club.jmint.mifty.config.ConfigWizard;
import club.jmint.mifty.crossing.CrossingWizard;
import club.jmint.mifty.dao.DaoWizard;


/**
 * @author shc
 *
 */
public class WizardManager {
	private static ArrayList<Wizard> alwizards = new ArrayList<Wizard>();
	
	public static void initWizard(){
		alwizards.add(new ConfigWizard("ConfigWizard"));
		alwizards.add(new DaoWizard("DaoWizard"));
		alwizards.add(new CrossingWizard("CrossingWizard"));
		
		Iterator<Wizard> it = alwizards.iterator();
		Wizard wiz;
		while(it.hasNext()){
			wiz = it.next();
			wiz.init();
		}
	}
	
	public static Wizard getWizard(String wizardName){
		Iterator<Wizard> it = alwizards.iterator();
		Wizard wiz;
		while(it.hasNext()){
			wiz = it.next();
			if (wiz.getName().equals(wizardName)){
				return wiz;
			}
		}
		return null;
	}
	
	public static void shutdownWizard(){
		Iterator<Wizard> it = alwizards.iterator();
		Wizard wiz;
		while(it.hasNext()){
			wiz = it.next();
			wiz.shutdown();;
		}
	}	
}
