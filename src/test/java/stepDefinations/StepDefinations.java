package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuilder;
import resources.Utils;

public class StepDefinations extends Utils
{

	RequestSpecification requestSpecBuilder;
	ResponseSpecification responseSpecBuilder;
	Response response;
	APIResources apiResources;
	static String place_id;

	TestDataBuilder testData = new TestDataBuilder();

	//Adding the Request attributes and headers 
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {


		//requestSpecification() - method to set the baseurl(https://rahulshettyacademy.com), query param and logging
		requestSpecBuilder = given()
				.spec(requestSpecification()) 
				.body(testData.addPlacePayLoad(name,language,address));

		//Response Spec Builder Class, used to set the status code, content type -  
		responseSpecBuilder =  new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();

	}

	@When("User call {string} with {string} http request")
	public void user_call_with_Post_http_request(String apiMethodResourcePath, String apiMethodName) {


		apiResources =  APIResources.valueOf(apiMethodResourcePath);
		System.out.println("API Resource path: " + apiResources.getResource());


		//with the Response specification object,get the API resourse Method(POST,GET,DELETE

		if(apiMethodName.equalsIgnoreCase("POST")) 
		{
			response = requestSpecBuilder.when().post(apiResources.getResource());
		}
		 
		else if(apiMethodName.equalsIgnoreCase("GET"))
		{
			response = requestSpecBuilder.when().get(apiResources.getResource());
		}
		
		
		


	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {

		assertEquals(response.getStatusCode(),200);


	}

	@And("{string} in response body is {string}")
	public void in_response_body_is(String attributeValue, String expectedAttributeValue) {


		assertEquals(getJsonPath(response,attributeValue), expectedAttributeValue);
	}


	@Then("Verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String apiMethodResourcePath) throws IOException
	{
		    place_id = getJsonPath(response,"place_id");
		    requestSpecBuilder = given().spec(requestSpecification()).queryParam("place_id", place_id);
		    user_call_with_Post_http_request(apiMethodResourcePath,"GET");
		    String actualName = getJsonPath(response, "name");
		    assertEquals(actualName,expectedName);

	}
	
	@Given("Delete Place API Payload")
	public void delete_Place_API_Payload() throws IOException {
		
		requestSpecBuilder = given().spec(requestSpecification()).body(testData.deletePlaceAPIPayLoad(place_id));
	}
	
	

	@Then("The delete Place API got success with status code {int}")
	public void the_delete_Place_API_got_success_with_status_code(Integer int1) {
		
		
		
	}








}



