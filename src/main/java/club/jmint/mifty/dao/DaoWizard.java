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

import java.util.HashMap;

import club.jmint.mifty.wizard.Wizard;

public class DaoWizard extends Wizard {
	private HashMap<String, Dao> daoMap = new HashMap<String, Dao>();

	public DaoWizard(String name) {
		super(name);
	}

	public Dao getDefaultDao(){
		return daoMap.get("defaultDao");
	}
	
	/* (non-Javadoc)
	 * @see club.jmint.mifty.wizard.Wizard#init()
	 */
	@Override
	public void init() {
		super.init();
		if (daoMap==null){
			daoMap = new HashMap<String, Dao>();
		}
		daoMap.put("defaultDao", new Dao());
	}

	/* (non-Javadoc)
	 * @see club.jmint.mifty.wizard.Wizard#startup()
	 */
	@Override
	public void startup() {
		super.startup();
	}

	/* (non-Javadoc)
	 * @see club.jmint.mifty.wizard.Wizard#shutdown()
	 */
	@Override
	public void shutdown() {
		super.shutdown();
		Dao dao = daoMap.get("defaultDao");
		dao.closeSessionFactory();
	}

	
}
