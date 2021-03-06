package com.renyou.dto;

import java.util.ArrayList;
import java.util.List;

import com.renyou.db.Image;
import com.renyou.db.Project;
import com.renyou.db.ProjectSpace;

public class ProjectDTO {
	
	private Integer id;
	
	private String name;

	private String description;

	private String status;

	private String type;

	private String location;

	private String style;
	
	private Integer designerId;
	
	private Boolean promoted;
	
	private List<String> images = new ArrayList<String>();
	
	private List<ProjectSpaceDTO> projectSpaces = new ArrayList<>();

	
	public ProjectDTO(){
		
	}
	
	public ProjectDTO(Project p){
		this.id = p.getId();
		this.name = p.getName();
		this.location = p.getLocation();
		this.status = p.getStatus();
		this.style = p.getStyle();
		this.type = p.getType();
		this.description = p.getDescription();
		this.promoted = p.getPromoted();
		if(p.getDesigner()!=null){
			this.designerId = p.getDesigner().getId();
		}
		for(Image img:p.getImages()){
			this.images.add(img.getPath());
		}
		if(p.getProjectSpaces()!=null){
			for(ProjectSpace ps:p.getProjectSpaces()){
				projectSpaces.add(new ProjectSpaceDTO(ps));
			}
		}
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Integer getDesignerId() {
		return designerId;
	}

	public void setDesignerId(Integer designerId) {
		this.designerId = designerId;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public List<ProjectSpaceDTO> getProjectSpaces() {
		return projectSpaces;
	}

	public void setProjectSpaces(List<ProjectSpaceDTO> projectSpaces) {
		this.projectSpaces = projectSpaces;
	}

	public Boolean getPromoted() {
		return promoted;
	}

	public void setPromoted(Boolean promoted) {
		this.promoted = promoted;
	}
}
