package com.renyou.dto;

import java.util.ArrayList;
import java.util.List;

import com.renyou.db.Designer;
import com.renyou.db.Image;
import com.renyou.db.Project;

public class DesignerDTO {

	private Integer id;
	private String name;
	private String description;
	private String address;
	private String city;
	private Boolean promoted;
	private List<String> images = new ArrayList<String>();
    private List<ProjectDTO> projects = new ArrayList<>();

	public DesignerDTO(Designer d) {
		this.id = d.getId();
		this.name = d.getName();
		this.description = d.getDescription();
		this.address = d.getAddress();
		this.city = d.getCity();
		this.promoted = d.getPromoted();
		
		for(Image img:d.getImages()){
			this.images.add(img.getPath());
		}
		
		if(d.getProjects()!=null && d.getProjects().size()>0){
			for(Project p:d.getProjects()){
				projects.add(new ProjectDTO(p));
			}
		}
	}
    
	public DesignerDTO() {
		
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Boolean getPromoted() {
		return promoted;
	}

	public void setPromoted(Boolean promoted) {
		this.promoted = promoted;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public List<ProjectDTO> getProjects() {
		return projects;
	}

	public void setProjects(List<ProjectDTO> projects) {
		this.projects = projects;
	}
}
