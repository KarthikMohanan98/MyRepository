package StepDefenition;

import java.io.IOException;

import org.junit.Assert;

import Resources.Constants;
import Resources.TestDataBuild;
import Resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class APIDefenition extends Utils{
	
	ResponseSpecification resspec;
	RequestSpecification reqspec;
	Response response;
	TestDataBuild tdata = new TestDataBuild();
	public static String placeID;

	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		//RestAssured.baseURI="https://rahulshettyacademy.com";
		 
		reqspec=given().spec(requestSpecification())
		.body(tdata.addPlacePayload(name, language, address));
	}
	
	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String api, String httpMethod) { 
		
		Constants con = Constants.valueOf(api);//create object for Conatants enum and call method using it inside post()
		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(httpMethod.equalsIgnoreCase("POST"))
			response =reqspec.when().post(con.getConstant());
		else if(httpMethod.equalsIgnoreCase("GET"))
			response =reqspec.when().get(con.getConstant());
		else if(httpMethod.equalsIgnoreCase("DELETE"))
			response =reqspec.when().delete(con.getConstant());
	}
	
	@Then("API call got successful with statuscode {int}")
	public void api_call_got_successful_with_statuscode(Integer int1) {
	    Assert.assertEquals(200,response.getStatusCode());
	}
	
	@Then("{string} in the response body is {string}")
	public void in_the_response_body_is(String key, String value) {
	   String status = getJsonValue(response, key);
	  Assert.assertEquals(value, status);
	  System.out.println("true"); 
	}

	@Then("Verify PlaceID created for {string} using {string}")
	public void verify_place_id_created_for_using(String name, String api) throws IOException {
	    
		placeID = getJsonValue(response, "place_id");
		reqspec = given().spec(requestSpecification()).queryParam("place_id", placeID);
		user_calls_with_http_request(api , "GET");
		String actualName = getJsonValue(response,"name");
		Assert.assertEquals(name, actualName);
	}
	
	@Given("Delete place payload")
	public void delete_place_payload() throws IOException {
	    
		reqspec=given().spec(requestSpecification()).body(tdata.deletePlacePayload(placeID));
		
	}
	
}
