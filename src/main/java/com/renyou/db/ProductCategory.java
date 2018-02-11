package com.renyou.db;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.renyou.dto.ProductCategoryDTO;

@Entity
public class ProductCategory {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String name;
    
    private String description;
    
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="parent_category_id")
    private ProductCategory parentProductCategory;
    
    @OneToMany(mappedBy="parentProductCategory")
    private List<ProductCategory> subCategories = new ArrayList<>();

    @OneToMany(mappedBy = "productCategory", cascade = CascadeType.ALL, orphanRemoval=true)
    private Set<ProductCategoryToProductAttributeRel> productCategoryToProductAttributeRel = new HashSet<ProductCategoryToProductAttributeRel>();
    
    public ProductCategory() {
    	
    }

	public ProductCategory(ProductCategoryDTO productCategoryDTO) {
		this.id = productCategoryDTO.getId();
		this.name = productCategoryDTO.getName();
		this.description = productCategoryDTO.getDescription();
	}

	public ProductCategory getParentProductCategory() {
		return parentProductCategory;
	}

	public void setParentProductCategory(ProductCategory parentProductCategory) {
		this.parentProductCategory = parentProductCategory;
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

	public List<ProductCategory> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<ProductCategory> subCategories) {
		this.subCategories = subCategories;
	}

	public Set<ProductCategoryToProductAttributeRel> getProductCategoryToProductAttributeRel() {
		return productCategoryToProductAttributeRel;
	}

	public void setProductCategoryToProductAttributeRel(
			Set<ProductCategoryToProductAttributeRel> productCategoryToProductAttributeRel) {
		this.productCategoryToProductAttributeRel = productCategoryToProductAttributeRel;
	}
}
