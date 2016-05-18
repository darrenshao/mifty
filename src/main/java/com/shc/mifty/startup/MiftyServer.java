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

package com.shc.mifty.startup;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

import com.shc.mifty.config.ConfigWizard;
import com.shc.mifty.config.ServerConfig;
import com.shc.mifty.example.ExaService;
import com.shc.mifty.example.ExaService.Iface;
import com.shc.mifty.example.ExaServiceImpl;
import com.shc.mifty.log.MyLog;
import com.shc.mifty.runtime.Constants;

import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;

public class MiftyServer {
	private String name="Mifty Server";
	private String version="0.1.0";
	private String bind_address;
	private int port = 9090;
	private int sslport = 9091;
	private boolean sslEnabled;
	private int backlog = 100;
	private String keystore;
	private String signType;
	private String signKey;
	private String encryptType;
	private String encryptKey;
	private String decryptKey;


	public MiftyServer(){
		init();
	}

	private void init(){
		ServerConfig config = (ServerConfig) ConfigWizard.getConfig(Constants.CONFIG_SERVER);
		this.bind_address = config.getItem("server.bind_address");
		this.port = Integer.parseInt(config.getItem("server.port"));
		this.sslport = Integer.parseInt(config.getItem("server.ssl.port"));
		this.sslEnabled = Boolean.parseBoolean(config.getItem("server.ssl.enabled"));
		this.backlog = Integer.parseInt(config.getItem("server.backlog"));
		this.keystore = config.getItem("server.keystore");
		this.signType = config.getItem("server.sign.type");
		this.signKey = config.getItem("server.sign.key");
		this.encryptType = config.getItem("server.encrypt.type");
		this.encryptKey = config.getItem("server.encrypt.key");
		this.decryptKey = config.getItem("server.decrypt.key");
	}

	private void simple(TMultiplexedProcessor p){
		try {
			TServerTransport t = new TServerSocket(port);
			TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(t).processor(p));
			//TServer server = new TSimpleServer(new Args(serverTransport).processor(processor ));

			MyLog.logger.info("Starting Mifty server...");
			server.serve();
		}catch(Exception e){
			MyLog.logger.error("Mifty server startup failed.");
			MyLog.printStackTrace(e);
		}
	}

	private void secure(TMultiplexedProcessor p){
		try {
			TSSLTransportParameters params = new TSSLTransportParameters();
			params.setKeyStore(keystore, "thrift", null, null);

			TServerTransport serverTransport = TSSLTransportFactory.getServerSocket(sslport, 0, null, params);
			//TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));

			// Use this for a multi threaded server
			TServer sslserver = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(p));

			MyLog.logger.info("Starting Mifty secure server...");
			sslserver.serve();

		}catch(Exception e){
			MyLog.logger.error("Mifty server startup failed.");
			MyLog.printStackTrace(e);
		}
	}

	public void startup(){

		final TMultiplexedProcessor processor = new TMultiplexedProcessor();
		processor.registerProcessor("ExaService", new ExaService.Processor<ExaService.Iface>(new ExaServiceImpl()));
		//processor.registerProcessor("UserService", new UserService.Processor<UserService.Iface>(new UserImpl()));  

		Runnable simple = new Runnable() {
			public void run() {
				simple(processor);
			}
		};      
		Runnable secure = new Runnable() {
			public void run() {
				secure(processor);
			}
		};

		new Thread(simple).start();
		if (sslEnabled){
			new Thread(secure).start();
		}
	}

}