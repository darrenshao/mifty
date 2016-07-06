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

package club.jmint.mifty.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

import club.jmint.mifty.config.ConfigWizard;
import club.jmint.mifty.config.ServerConfig;
import club.jmint.mifty.example.ExaServiceImpl;
import club.jmint.mifty.example.gen.ExaService;
import club.jmint.mifty.runtime.Constants;
import club.jmint.mifty.service.DemoServiceImpl;
import club.jmint.mifty.service.gen.DemoService;
import club.jmint.mifty.utils.CrossLog;
import club.jmint.mifty.wizard.WizardManager;

import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;

public class MiftyServer {
	private String name="Mifty Framework v0.1.0";
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
	
	final TMultiplexedProcessor processor = new TMultiplexedProcessor();

	public void setName(String name){
		this.name = name;
	}
	
	public MiftyServer(){
		//Load configurations
		WizardManager.initWizard();
		
		init();

	}
	
	private void cleanup(){
		WizardManager.shutdownWizard();
	}
	

	private void init(){
		ServerConfig config = (ServerConfig) ConfigWizard.getConfig(Constants.CONFIG_SERVER);
		this.name = config.getItem("server.name");
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

//            TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(port);
//            TThreadedSelectorServer.Args args = new TThreadedSelectorServer.Args(serverTransport);
//            args.processor(p);
//            args.protocolFactory(new TCompactProtocol.Factory());
//            args.transportFactory(new TFramedTransport.Factory());
//            args.processorFactory(new TProcessorFactory(p));
//            args.selectorThreads(2);
//            ExecutorService pool = Executors.newFixedThreadPool(3);
//            args.executorService(pool);
//            TThreadedSelectorServer server = new TThreadedSelectorServer(args);
//            MyLog.logger.info("Starting "+ name +" Server on port "+ port +"...");
//            server.serve();
            
			TServerTransport t = new TServerSocket(port);
			TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(t).processor(p));
			//TServer server = new TSimpleServer(new Args(serverTransport).processor(processor ));

			CrossLog.logger.info("Starting "+ name +" Server...");
			server.serve();
		}catch(Exception e){
			cleanup();
			CrossLog.logger.error(name + " server startup failed.");
			CrossLog.printStackTrace(e);
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

			CrossLog.logger.info("Starting " + name + " secure server...");
			sslserver.serve();

		}catch(Exception e){
			cleanup();
			CrossLog.logger.error(name + " server startup failed.");
			CrossLog.printStackTrace(e);
		}
	}

	public void add(String service, TProcessor tp){
		this.processor.registerProcessor(service, tp);
	}
	
	public void startup(){
//		processor.registerProcessor("ExaService", new ExaService.Processor<ExaService.Iface>(new ExaServiceImpl()));
//		processor.registerProcessor("DemoService", new DemoService.Processor<DemoService.Iface>(new DemoServiceImpl()));  

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