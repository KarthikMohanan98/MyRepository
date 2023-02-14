package Resources;

public enum Constants {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	DeletePlaceAPI("/maps/api/place/delete/json"),
	GetPlaceAPI("/maps/api/place/get/json");

	private String constant;
	Constants(String constant) {
		this.constant = constant;
	}
	
	public String getConstant() {
		return constant;
	}

}
