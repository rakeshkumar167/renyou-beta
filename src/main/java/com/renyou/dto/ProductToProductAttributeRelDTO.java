package com.renyou.dto;

import com.renyou.db.ProductAttribute;
import com.renyou.db.ProductToProductAttributeRel;

public class ProductToProductAttributeRelDTO {
    private Integer id;
    private Integer productAttributeId;
    private String productAttributeName;
    private String value;
    
    public ProductToProductAttributeRelDTO(ProductToProductAttributeRel rel){
    	this.id = rel.getId();
    	this.productAttributeId = rel.getProductAttribute().getId();
    	this.productAttributeName = rel.getProductAttribute().getName();
    	this.value = rel.getValue();
    }
    
    public ProductToProductAttributeRelDTO(ProductAttribute pa){
    	this.productAttributeId = pa.getId();
    	this.productAttributeName = pa.getName();
    }
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProductAttributeId() {
		return productAttributeId;
	}
	public void setProductAttributeId(Integer productAttributeId) {
		this.productAttributeId = productAttributeId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public String getProductAttributeName() {
		return productAttributeName;
	}

	public void setProductAttributeName(String productAttributeName) {
		this.productAttributeName = productAttributeName;
	}

}
