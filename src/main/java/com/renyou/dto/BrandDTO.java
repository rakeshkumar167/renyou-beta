package com.renyou.dto;

public class BrandDTO {
	
	public BrandDTO(){
		
	}
	
	public BrandDTO(Integer id, String name, String description){
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public BrandDTO(String name, String description){
		this.name = name;
		this.description = description;
	}
	
    private Integer id;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private String name;
    
    private String description;
}
