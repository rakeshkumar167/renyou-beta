package com.renyou.db;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProductCategoryToProductAttributeRel implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;
    
    @ManyToOne
    @JoinColumn(name = "product_attribute_id")
    private ProductAttribute productAttribute;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public ProductAttribute getProductAttribute() {
		return productAttribute;
	}

	public void setProductAttribute(ProductAttribute productAttribute) {
		this.productAttribute = productAttribute;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

}
