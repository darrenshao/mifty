/* define your thrift service here*/
namespace java club.jmint.mifty.service.gen

service demoService {
	string demoSay(1:string params, 2:bool isEncrypt)
}