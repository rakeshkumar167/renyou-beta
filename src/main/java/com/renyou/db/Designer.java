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
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.renyou.dto.DesignerDTO;

@Entity
public class Designer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull
	@Column(unique = true)
	private String name;

	@Column(length = 5000)
	private String description;

	@Column(length = 500)
	private String address;

	private String city;
	
	private Boolean promoted;
	
	@OneToMany(fetch = FetchType.EAGER,
            mappedBy = "designer")
    private Set<Project> projects = new HashSet<>();
	
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "designer")
    private Set<Image> images = new HashSet<>();
    
    public Designer() {
    	
    }
    
    public Designer(DesignerDTO dto){
    	this.id = dto.getId();
    	this.name = dto.getName();
    	this.description = dto.getDescription();
    	this.city = dto.getCity();
    	this.address = dto.getAddress();
    	this.promoted = dto.getPromoted();
    }
    
    public void setDesignerDTO(DesignerDTO dto){
    	this.name = dto.getName();
    	this.description = dto.getDescription();
    	this.city = dto.getCity();
    	this.address = dto.getAddress();
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

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public Boolean getPromoted() {
		return promoted;
	}

	public void setPromoted(Boolean promoted) {
		this.promoted = promoted;
	}

	public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}
}