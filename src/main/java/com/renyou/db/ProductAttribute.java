package com.renyou.db;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.renyou.dto.ProductAttributeDTO;

@Entity
public class ProductAttribute {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String name;
    
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="product_attribute_type_id")
    private ProductAttributeType productAttributeType;
    
    public ProductAttribute() {
    	
    }

	public ProductAttribute(ProductAttributeDTO dto) {
		this.name = dto.getName();
		this.id = dto.getId();
		
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

	public ProductAttributeType getProductAttributeType() {
		return productAttributeType;
	}

	public void setProductAttributeType(ProductAttributeType productAttributeType) {
		this.productAttributeType = productAttributeType;
	}
}
