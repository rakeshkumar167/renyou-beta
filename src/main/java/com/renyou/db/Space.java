package com.renyou.db;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.renyou.dto.SpaceDTO;

@Entity
public class Space {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String name;
    
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="space_type_id")
    private SpaceType spaceType;

	private String description;
    
    public Space() {
    	
    }
    
    public Space(SpaceDTO dto) {
    	this.id = dto.getId();
    	this.name = dto.getName();
    	this.description = dto.getDescription();
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
    
    public SpaceType getSpaceType() {
		return spaceType;
	}


	public void setSpaceType(SpaceType spaceType) {
		this.spaceType = spaceType;
	}
}
