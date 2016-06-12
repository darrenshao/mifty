package club.jmint.mifty.example;

import club.jmint.mifty.example.gen.ExaService;
import club.jmint.mifty.server.MiftyServer;
import club.jmint.mifty.service.DemoServiceImpl;
import club.jmint.mifty.service.gen.DemoService;

public class ExaServer {

	public static void main(String [] args) {
		
		MiftyServer ms = new MiftyServer();
		ms.setName("DemoMifty");
		
		//register service interface
		ms.add("ExaService", new ExaService.Processor<ExaService.Iface>(new ExaServiceImpl()));
		ms.add("DemoService", new DemoService.Processor<DemoService.Iface>(new DemoServiceImpl()));  

		//start
		ms.startup();
	}
}
