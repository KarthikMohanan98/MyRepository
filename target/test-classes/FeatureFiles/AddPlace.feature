Feature: Validating Place API

@AddPlace
Scenario Outline: Validating Place is successfully added to the API
	
	Given Add place payload with "<name>" "<language>" "<address>"
	When User calls "AddPlaceAPI" with "POST" http request 
	Then API call got successful with statuscode 200
	And "status" in the response body is "OK"
	And "scope" in the response body is "APP"
	And Verify PlaceID created for "<name>" using "GetPlaceAPI"
Examples:
	|name|language|address|
	|House|English|14th cross road,London|
#	|Home|French|2nd main Road ,France|


@DeletePlace
Scenario: Validating Place is successfully deleted using DeleteAPI
	
	Given Delete place payload
	When User calls "DeletePlaceAPI" with "POST" http request
	Then API call got successful with statuscode 200
	And "status" in the response body is "OK"