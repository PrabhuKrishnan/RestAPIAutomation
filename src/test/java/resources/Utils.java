package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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


	public static RequestSpecification requestSpecBuilder;
	
	//Request Specification Builder class, used to set query, path parameters,set cookies,headers,base URL,base path
	public RequestSpecification requestSpecification() throws IOException
	{

		if(requestSpecBuilder==null)
		{
		PrintStream log = new PrintStream(new FileOutputStream("apiLogging.txt"));
		requestSpecBuilder = new  RequestSpecBuilder()
				.setBaseUri(getGlobalValue("baseUrl"))
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))   //to log API Request
				.addFilter(ResponseLoggingFilter.logResponseTo(log)) //to log API Response
				.setContentType(ContentType.JSON)
				.build();
		return requestSpecBuilder;
		
		}
		
		return requestSpecBuilder;
		
	}
	
	
	public static  String getGlobalValue(String baseUrl) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("G:\\Profession\\Automation workspace\\RestAssuredFramework\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(baseUrl);
		 
	}
	
	
	public String getJsonPath(Response response,String attributeValue)
	{
		String res = response.asString();
		System.out.println("Json Parser Response: " + res);
		JsonPath jsonPath = new JsonPath(res);
		return jsonPath.get(attributeValue);
		
		
		 
	 
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
