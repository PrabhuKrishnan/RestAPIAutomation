package resources;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class CreateAJsonUsingHashMap {

	public static void main(String[] args) throws IOException {
		
		ExcelData excelData = new ExcelData();
		
		ArrayList<String> data = excelData.getData("AddNewPlace","LibraryAddAPI");
		
		System.out.println("Add Request using the HashMap");
		Map<String,Object> libraryObject = new HashMap<String,Object>();
		libraryObject.put("name", data.get(1));
		libraryObject.put("isbn", data.get(2));
		libraryObject.put("aisle", data.get(3));
		libraryObject.put("author", data.get(4));
		
		
		
		//if the json have the nested json, the create other hash map like below
		/*
		 * Map<String,Object> locationObject = new HashMap<String,Object>();
		 * locationObject.put("location", "salem"); locationObject.put("place",
		 * "salem");
		 * 
		 * libraryObject.put("placeObject", locationObject);
		 */
		
		RestAssured.baseURI="http://216.10.245.166/";
		System.out.println();
		String postResponse = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json") //log().all() - for logging request and response
				.body(libraryObject)
				.when().post("/Library/Addbook.php")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();
				 
		
		
		System.out.println("Response..." + postResponse);

		JsonPath postResponseParse = new JsonPath(postResponse); //JsonPath class, used to parse the response and then we can extract the specific attribute 
		String Id = postResponseParse.getString("ID");

		System.out.println("Id... " + Id);
		
		
		
		
	}
	
}
