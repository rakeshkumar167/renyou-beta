package com.renyou.dto;

import java.util.ArrayList;
import java.util.List;

public class ProductDTO {
	private Integer id;
    private String name;
    private String description;
	private Integer productCategoryId;
	private Integer brandId;
	private List<String> images = new ArrayList<String>();
	
	public ProductDTO() {
		
	}
	
	public ProductDTO(Integer id, String name, String description, Integer productCategoryId, Integer brandId){
		this.id = id;
		this.name = name;
		this.description = description;
		this.productCategoryId = productCategoryId;
		this.brandId = brandId;
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
	public Integer getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(Integer productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	
	public void addImage(String img){
		this.images.add(img);
	}

}
