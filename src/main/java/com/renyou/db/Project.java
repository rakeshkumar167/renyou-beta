package com.renyou.db;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.renyou.dto.ProjectDTO;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull
	@Column(unique = true)
	private String name;

	@Column(length = 5000)
	private String description;

	private String status;

	private String type;

	private String location;

	private String style;
	
	private Boolean promoted;
	
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="designer_id")
    private Designer designer;
    
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "project")
    private Set<Image> images = new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "project")
    private Set<ProjectSpace> projectSpaces = new HashSet<>();
    
    public Project(){
    	
    }
    
    public Project(ProjectDTO p){
    	this.id = p.getId();
		this.name = p.getName();
		this.location = p.getLocation();
		this.status = p.getStatus();
		this.style = p.getStyle();
		this.type = p.getType();
		this.description = p.getDescription();
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

	public Designer getDesigner() {
		return designer;
	}

	public void setDesigner(Designer designer) {
		this.designer = designer;
	}

	public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}

	public Set<ProjectSpace> getProjectSpaces() {
		return projectSpaces;
	}

	public void setProjectSpaces(Set<ProjectSpace> projectSpaces) {
		this.projectSpaces = projectSpaces;
	}

	public Boolean getPromoted() {
		return promoted;
	}

	public void setPromoted(Boolean promoted) {
		this.promoted = promoted;
	}
	
}
