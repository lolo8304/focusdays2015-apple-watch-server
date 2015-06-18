package com.focusdays2015.glass;

public class ValueObject {

	private String id;
	private String name;
	private String price;
	
	public ValueObject(String id, String name, String price) {
		this.setId(id);
		this.setName(name);
		this.setPrice(price);
	}
	public ValueObject() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public String toJSON() {
		return "{ \"id\": \""+this.getId()+"\", \"name\": \""+this.getName()+"\", \"price\": \""+this.getPrice()+"\"}";
	}

}
