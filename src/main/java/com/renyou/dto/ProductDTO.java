package com.renyou.dto;

import java.util.ArrayList;
import java.util.List;

import com.renyou.db.Image;
import com.renyou.db.Product;

public class ProductDTO {
	private Integer id;
    private String name;
    private String description;
	private Integer productCategoryId;
	private Integer brandId;
	private List<String> images = new ArrayList<String>();
	
	public ProductDTO() {
		
	}
	
	public ProductDTO(Product product){
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.productCategoryId = product.getCategory()!=null?product.getCategory().getId():null;
		this.brandId = product.getBrand()!=null?product.getBrand().getId():null;
		for(Image img:product.getImages()){
			this.images.add(img.getPath());
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

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

}
