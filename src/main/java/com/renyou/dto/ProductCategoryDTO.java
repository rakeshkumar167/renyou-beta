package com.renyou.dto;

import java.util.ArrayList;
import java.util.List;

import com.renyou.db.ProductCategory;
import com.renyou.db.ProductCategoryToProductAttributeRel;

public class ProductCategoryDTO {
	private Integer id;

	private String name;

	private String description;

	private Integer parentProductCategoryId;
	
	private List<Integer> productAttributeIds = new ArrayList<Integer>();

	public ProductCategoryDTO() {

	}

	public ProductCategoryDTO(Integer id, String name, String description, Integer parentProductCategoryId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.parentProductCategoryId = parentProductCategoryId;
	}
	
	public ProductCategoryDTO (ProductCategory pc) {
		this.id = pc.getId();
		this.name = pc.getName();
		this.description = pc.getDescription();
		this.parentProductCategoryId = pc.getParentProductCategory()!=null?pc.getParentProductCategory().getId():null;
		if(pc.getProductCategoryToProductAttributeRel()!=null){
			for(ProductCategoryToProductAttributeRel rel:pc.getProductCategoryToProductAttributeRel()){
				productAttributeIds.add(rel.getProductAttribute().getId());
			}
		}
				
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

	public List<Integer> getProductAttributeIds() {
		return productAttributeIds;
	}

	public void setProductAttributeIds(List<Integer> productAttributeIds) {
		this.productAttributeIds = productAttributeIds;
	}

}
