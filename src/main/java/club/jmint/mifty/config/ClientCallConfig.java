package club.jmint.mifty.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.ConfigurationNode;

import club.jmint.crossing.client.ClientCallInfo;
import club.jmint.crossing.client.CrossingServerInfo;
import club.jmint.mifty.log.MyLog;


public class ClientCallConfig extends Config {
	private HashMap<String, ClientCallInfo> cciMap = new HashMap<String, ClientCallInfo>();
	private HashMap<String ,CrossingServerInfo> csiMap = new HashMap<String, CrossingServerInfo>();

	public ClientCallConfig(String filePath) {
		super("ClientCallConfig", filePath);
		init();
	}

	public CrossingServerInfo getDefaultCrossingServerInfo(){
		return csiMap.get("DEFAULT");
	}
	
	
	public HashMap<String, ClientCallInfo> getClientCallInfoMap(){
		return cciMap;
	}
	
	private void init(){
		if (cciMap==null){
			cciMap = new HashMap<String, ClientCallInfo>();
		}
		if (csiMap==null){
			csiMap = new HashMap<String, CrossingServerInfo>();
		}
	}

	@Override
	public Config loadConfig() {
		super.loadConfig();
		XMLConfiguration conf = loadXMLConfigFile(configFilePath);

		ConfigurationNode root = conf.getRootNode();

		List<ConfigurationNode> csrvnode = root.getChildren("crossingserver");
		List<ConfigurationNode> srvnode = csrvnode.get(0).getChildren();
		ConfigurationNode cnode;
		List<ConfigurationNode> name,ip,port,ssl;
		for(int i=0;i<srvnode.size();i++){
			cnode = srvnode.get(i);
			name = cnode.getAttributes("name");
			ip = cnode.getAttributes("ip");
			port = cnode.getAttributes("port");
			ssl = cnode.getAttributes("sslEnabled");			
			
			csiMap.put(name.get(0).getValue().toString(), new CrossingServerInfo(
					name.get(0).getValue().toString(),
					ip.get(0).getValue().toString(),
					port.get(0).getValue().toString(),
					Boolean.parseBoolean(ssl.get(0).getValue().toString())
					));
		}
		
		
		List<ConfigurationNode> objectsnode = root.getChildren("callobjects");
		List<ConfigurationNode> objnode = objectsnode.get(0).getChildren();
		ConfigurationNode node;
		List<ConfigurationNode> service,signType,signKey,encryptType,encryptKey,decryptKey;
		for(int i=0;i<objnode.size();i++){
			node = objnode.get(i);
			service = node.getAttributes("service");
			signType = node.getAttributes("signType");
			signKey = node.getAttributes("signKey");
			encryptType = node.getAttributes("encryptType");
			encryptKey = node.getAttributes("encryptKey");
			decryptKey = node.getAttributes("decryptKey");
			
//			Iterator<ConfigurationNode> it = node.getAttributes().iterator();
//			ConfigurationNode cn;
//			while(it.hasNext()){
//				cn = it.next();
//				System.out.println(cn.getName());
//				System.out.println(cn.getValue());
//			}
			
			
			cciMap.put(service.get(0).getValue().toString(), new ClientCallInfo("",
					service.get(0).getValue().toString(),"",
					signType.get(0).getValue().toString(),
					signKey.get(0).getValue().toString(),
					encryptType.get(0).getValue().toString(),
					encryptKey.get(0).getValue().toString(),
					decryptKey.get(0).getValue().toString()));
		}

		return this;
	}

	@Override
	public void print() {
		super.print();
		StringBuffer sb = new StringBuffer();
		sb.append(name+":\n");
		
		sb.append("Crossing Server:\n");
		Iterator<Entry<String,CrossingServerInfo>> it = csiMap.entrySet().iterator();
		Entry<String,CrossingServerInfo> en;
		while(it.hasNext()){
			en = it.next();
			sb.append(String.format("%-20s= (%s,%s,%s)\n", en.getKey(), en.getValue().ip,en.getValue().port,en.getValue().ssl_enabled));
		}
		
		
		sb.append("Call Objects:\n");
		Iterator<Entry<String,ClientCallInfo>> it2 = cciMap.entrySet().iterator();
		Entry<String,ClientCallInfo> en2;
		while(it2.hasNext()){
			en2 = it2.next();
			sb.append(String.format("%-20s= %s\n", en2.getKey(), en2.getValue()));
		}
	
		MyLog.logger.info(sb.toString());
	}
	
	
}
