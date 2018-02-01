package com.renyou.dto;

import com.renyou.db.ProductAttribute;

public class ProductAttributeDTO {
    private Integer id;

	private String name;
	
	private Integer productAttributeTypeId;
	
	public ProductAttributeDTO() {
		
	}
	
	public ProductAttributeDTO(ProductAttribute productAttribute) {
		this.id = productAttribute.getId();
		this.name = productAttribute.getName();
		this.productAttributeTypeId = productAttribute.getProductAttributeType()!=null?productAttribute.getProductAttributeType().getId():null;
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

	public Integer getProductAttributeTypeId() {
		return productAttributeTypeId;
	}

	public void setProductAttributeTypeId(Integer productAttributeTypeId) {
		this.productAttributeTypeId = productAttributeTypeId;
	}
}
