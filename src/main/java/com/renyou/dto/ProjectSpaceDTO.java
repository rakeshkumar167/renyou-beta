package com.renyou.dto;

import java.util.ArrayList;
import java.util.List;

import com.renyou.db.Image;
import com.renyou.db.ProjectSpace;

public class ProjectSpaceDTO {

	private Integer id;
	private String name;
	private String description;
	private Integer spaceId;
	private String spaceName;
	private String shortDescription;
	private Integer projectId;
	
	private List<String> images = new ArrayList<String>();

	
	public ProjectSpaceDTO(){
		
	}
	public ProjectSpaceDTO(ProjectSpace projectSpace){
		this.id = projectSpace.getId();
		this.name = projectSpace.getName();
		this.description = projectSpace.getDescription();
		this.shortDescription = projectSpace.getShortDescription();
		if(projectSpace.getSpace()!=null){
			this.spaceId = projectSpace.getSpace().getId();
			this.spaceName = projectSpace.getSpace().getName();
		}
		this.projectId = projectSpace.getProject().getId();
		if(projectSpace.getImages()!=null){
			for(Image img:projectSpace.getImages()){
				this.images.add(img.getPath());
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
	public Integer getSpaceId() {
		return spaceId;
	}
	public void setSpaceId(Integer spaceId) {
		this.spaceId = spaceId;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getSpaceName() {
		return spaceName;
	}
	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
}
