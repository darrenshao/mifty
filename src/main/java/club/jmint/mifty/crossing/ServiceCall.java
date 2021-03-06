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

import com.google.gson.JsonObject;

import club.jmint.crossing.client.CallResult;
import club.jmint.crossing.client.CrossingClient;
import club.jmint.crossing.specs.CrossException;

public class ServiceCall {
	public static final CrossingClient cc = CrossingClient.getInstance();
	
	public CallResult invoke(String inf, JsonObject params) throws CrossException {
		return cc.serviceCall(inf, params, false);
	}
	
	public CallResult invoke(String inf, JsonObject params, boolean isEncrypt) throws CrossException {
		return cc.serviceCall(inf, params, isEncrypt);
	}
}
