Feature: Validating AddPlace API's

@AddPlaceAPI
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
Given Add Place Payload with "<name>" "<language>" "<address>"
When User call "AddPlaceAPI" with "post" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
And Verify place_id created maps to "<name>" using "getPlaceAPI"

Examples:
  |name  | language |address|
  |Prabhu| English  |salem  |
  

@DeletePlaceAPI
Scenario: Verify that the delete place API is working or not 
Given Delete Place API Payload 
When  User call "deletePlaceAPI" with "post" http request
Then  The delete Place API got success with status code 200
And "status" in response body is "OK"
