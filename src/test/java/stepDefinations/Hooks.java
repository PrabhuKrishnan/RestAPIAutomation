package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	
	@Before("@DeletePlaceAPI")
	public void beforeScenario() throws IOException
	{
		
		
		StepDefinations sd = new StepDefinations();
		
		if(StepDefinations.place_id==null)
		{
		sd.add_Place_Payload_with("venkat", "tamil", "cbe");
		sd.user_call_with_Post_http_request("AddPlaceAPI", "POST");
		sd.verify_place_id_created_maps_to_using("venkat", "getPlaceAPI");
		}
		
	}

}
