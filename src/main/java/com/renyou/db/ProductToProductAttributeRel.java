package com.renyou.db;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProductToProductAttributeRel {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "product_attribute_id")
    private ProductAttribute productAttribute;
    
    private String value;
    
    public ProductToProductAttributeRel(){
    	
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ProductAttribute getProductAttribute() {
		return productAttribute;
	}

	public void setProductAttribute(ProductAttribute productAttribute) {
		this.productAttribute = productAttribute;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
