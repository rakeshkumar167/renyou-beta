package com.renyou.dto;

public class ProductCategoryDTO {
	private Integer id;

	private String name;

	private String description;

	private Integer parentProductCategoryId;

	public ProductCategoryDTO() {

	}

	public ProductCategoryDTO(Integer id, String name, String description, Integer parentProductCategoryId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.parentProductCategoryId = parentProductCategoryId;
	}

	public Integer getParentProductCategoryId() {
		return parentProductCategoryId;
	}

	public void setParentProductCategoryId(Integer parentProductCategoryId) {
		this.parentProductCategoryId = parentProductCategoryId;
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
}
