package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.GoogleAddPlaceAPI;
import pojo.Location;

public class TestDataBuilder {
	
	
	public GoogleAddPlaceAPI addPlacePayLoad(String name,String language,String address)
	{
	
		GoogleAddPlaceAPI addPlaceAPI = new GoogleAddPlaceAPI();
		addPlaceAPI.setAccuracy(50);
		addPlaceAPI.setName(name);
		addPlaceAPI.setPhone_number("9994042448");
		addPlaceAPI.setAddress(address);
		addPlaceAPI.setWebsite("https://prabhukrishnanphotography.com");
		addPlaceAPI.setLanguage(language);

		//In the request if it mentioned as [], then its an array of list 
		List<String> typesList =new ArrayList<String>();
		typesList.add("canon");
		typesList.add("sony");

		addPlaceAPI.setTypes(typesList);

		//nested json and in the request  its mentioned as {},then need to create a new class
		Location location = new Location();
		location.setLat(-33.334344);
		location.setLng(33.320332);

		addPlaceAPI.setLocation(location);
		
		return addPlaceAPI;
		
		
	}
	
	
	
	public String  deletePlaceAPIPayLoad(String placeId) {
		
		return "{\"place_id\":\""+placeId+"\"}";
		
		
	}
	
	
	
	
	

}
