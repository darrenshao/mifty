package com.shc.mifty.example;
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

// Generated code


import org.apache.thrift.TException;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TTransport;

import com.google.gson.JsonObject;
import com.shc.crossing.exception.CrossException;
import com.shc.crossing.specs.ParamBuilder;

import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;

public class ExaClient {
	public static void main(String [] args) {

		try {
			TTransport transport;
			//      if (true) {
			transport = new TSocket("localhost", 9090);
			//transport.open();
			//      }
			//      else {
			//        /*
			//         * Similar to the server, you can use the parameters to setup client parameters or
			//         * use the default settings. On the client side, you will need a TrustStore which
			//         * contains the trusted certificate along with the public key. 
			//         * For this example it's a self-signed cert. 
			//         */
			//        TSSLTransportParameters params = new TSSLTransportParameters();
			//        params.setTrustStore("../../lib/java/test/.truststore", "thrift", "SunX509", "JKS");
			//        /*
			//         * Get a client transport instead of a server transport. The connection is opened on
			//         * invocation of the factory method, no need to specifically call open()
			//         */
			//        transport = TSSLTransportFactory.getClientSocket("localhost", 9091, 0, params);
			//      }

			TProtocol protocol = new  TBinaryProtocol(transport);
			TMultiplexedProtocol mp1 = new TMultiplexedProtocol(protocol,"ExaService");
			ExaService.Client client = new ExaService.Client(mp1);

			//            TMultiplexedProtocol mp2 = new TMultiplexedProtocol(protocol,"UserService");
			//            UserService.Client service2 = new UserService.Client(mp2);

			transport.open();



			JsonObject pp = new JsonObject();
			pp.addProperty("echo", "How are you!");
			pp.addProperty("name", "Darren");
			String signP =null;
			try {
				signP = ParamBuilder.buildSignedParams(pp.toString(), "miftyExampleKey");
			} catch (CrossException e) {
				e.printStackTrace();
			}
			String ret = client.echo(signP, false);
			System.out.println(ParamBuilder.checkSignAndRemove(ret, "miftyExampleKey"));

			JsonObject pp2 = new JsonObject();
			pp2.addProperty("hello", "Good morning!");
			pp2.addProperty("name", "Darren");
			String signP2 =null;
			try {
				signP2 = ParamBuilder.buildEncryptedParams(
						ParamBuilder.buildSignedParams(pp2.toString(), "miftyExampleKey"),"miftyExampleKey");
			} catch (CrossException e) {
				e.printStackTrace();
			}
			ret = client.sayHello(signP2, true);
			System.out.println(ParamBuilder.checkSignAndRemove(
					ParamBuilder.buildDecryptedParams(ret, "miftyExampleKey"),"miftyExampleKey"));

			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		} catch (CrossException e1) {
			e1.printStackTrace();
		} 

	}
}
