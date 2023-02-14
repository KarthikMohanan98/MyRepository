package StepDefenition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {// Hook class should be created inside the package StepDefenition

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		//this method ,will only run when the placeId is null
		APIDefenition def = new APIDefenition();
		if (APIDefenition.placeID == null) {
			def.add_place_payload_with("Harry", "German", "3rd cross ,Berlin");
			def.user_calls_with_http_request("AddPlaceAPI", "POST");
			def.verify_place_id_created_for_using("Harry", "GetPlaceAPI");
		}
	}
}
