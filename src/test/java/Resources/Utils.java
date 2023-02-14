package Resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	static RequestSpecification req ;
	
	public RequestSpecification requestSpecification() throws IOException {
		
		if(req==null) {
		PrintStream log = new PrintStream(new FileOutputStream("log.txt")); //for logging 
		req =new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))//'for logging the request in .txt file
				.addFilter(ResponseLoggingFilter.logResponseTo(log)) // for logging the response in .txt file
				.setContentType(ContentType.JSON).build();
		return req;
		}
		return req;
	}
	
	public static String getGlobalValue( String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\karthikmoh\\Desktop\\Karthik\\NewWorkspace\\"
				+ "APIFramework\\src\\test\\java\\Resources\\Global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	
	public String getJsonValue(Response resp, String key) {
		String res = resp.asString();
		JsonPath js=new JsonPath(res);
		return js.get(key).toString();
	}
}
