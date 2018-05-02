package com.renyou.db;

import java.io.Serializable;
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

import com.renyou.dto.ProjectSpaceDTO;

@Entity
public class ProjectSpace  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	
	@Column(length = 1000)
	private String shortDescription;
	
	@Column(length = 5000)
	private String description;
	
    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;
    
    @ManyToOne
    @JoinColumn(name="space_id")
    private Space space;
    
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "projectSpace")
    private Set<Image> images = new HashSet<>();
    
    public ProjectSpace() {
    	
    }

	public ProjectSpace(ProjectSpaceDTO projectSpace) {
		this.id = projectSpace.getId();
		this.name = projectSpace.getName();
		this.description = projectSpace.getDescription();
		this.shortDescription = projectSpace.getShortDescription();
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

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Space getSpace() {
		return space;
	}

	public void setSpace(Space space) {
		this.space = space;
	}

	public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}
    
    
}
