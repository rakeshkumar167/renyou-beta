package com.renyou.dto;

import com.renyou.db.ProductCategoryToProductAttributeRel;

public class ProductCategoryToProductAttributeRelDTO {
	private Integer id;
	private String productAttributeName;
	private String productAttributeTypeName;
	
	public ProductCategoryToProductAttributeRelDTO() {
		
	}
	
	public ProductCategoryToProductAttributeRelDTO(ProductCategoryToProductAttributeRel rel) {
		this.id = rel.getId();
		this.productAttributeName = rel.getProductAttribute().getName();
		this.productAttributeTypeName = rel.getProductAttribute().getProductAttributeType().getName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductAttributeName() {
		return productAttributeName;
	}

	public void setProductAttributeName(String productAttributeName) {
		this.productAttributeName = productAttributeName;
	}

	public String getProductAttributeTypeName() {
		return productAttributeTypeName;
	}

	public void setProductAttributeTypeName(String productAttributeTypeName) {
		this.productAttributeTypeName = productAttributeTypeName;
	}


}
