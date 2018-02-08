package com.renyou.db;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.renyou.dto.ProductDTO;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String name;
    
    private String description;
    
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="category_id")
    private ProductCategory category;
    
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="brand_id")
    private Brand brand;
    

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "product")
    private Set<Image> images = new HashSet<>();
    
    @OneToMany(mappedBy = "product")
    private Set<ProductToProductAttributeRel> productToProductAttributeRel = new HashSet<ProductToProductAttributeRel>();
    
    public Product(){
    	
    }
    
    public Product(ProductDTO dto) {
    	this.id = dto.getId();
    	this.name = dto.getName();
    	this.description = dto.getDescription();
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

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}

	public Set<ProductToProductAttributeRel> getProductToProductAttributeRel() {
		return productToProductAttributeRel;
	}

	public void setProductToProductAttributeRel(Set<ProductToProductAttributeRel> productToProductAttributeRel) {
		this.productToProductAttributeRel = productToProductAttributeRel;
	}


}
