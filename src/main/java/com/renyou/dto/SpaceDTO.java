package com.renyou.dto;

import com.renyou.db.Space;

public class SpaceDTO {

	public SpaceDTO(){
		
	}
	
	public SpaceDTO(Integer id, String name, String description, Integer spaceTypeId){
		this.id = id;
		this.name = name;
		this.spaceTypeId = spaceTypeId;
		this.description = description;
	}
	
	public SpaceDTO(Space space){
		this.id = space.getId();
		this.name = space.getName();
		this.description = space.getDescription();
		this.spaceTypeId = space.getSpaceType()!=null?space.getSpaceType().getId():null;
	}
	
    private Integer id;

	private String name;
	
	private String description;
	
	private Integer spaceTypeId;
	
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

	public Integer getSpaceTypeId() {
		return spaceTypeId;
	}

	public void setSpaceTypeId(Integer spaceTypeId) {
		this.spaceTypeId = spaceTypeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
