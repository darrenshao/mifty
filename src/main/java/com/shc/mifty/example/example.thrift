namespace java com.shc.mifty.example

service ExampleService {
   	string sayHello(1:string params, 2:bool isEncrypt),
	string echo(1:string params, 2:bool isEncrypt)
}
