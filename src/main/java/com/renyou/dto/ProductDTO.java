package com.renyou.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.renyou.db.Image;
import com.renyou.db.Product;
import com.renyou.db.ProductCategory;
import com.renyou.db.ProductCategoryToProductAttributeRel;
import com.renyou.db.ProductToProductAttributeRel;

public class ProductDTO {
	private Integer id;
    private String name;
    private String description;
	private Integer productCategoryId;
	private ProductCategory category;
	private Integer brandId;
	private List<String> images = new ArrayList<String>();
	private List<ProductToProductAttributeRelDTO> productToProductAttributeRelDTOs = new ArrayList<>();
	
	public ProductDTO() {
		
	}
	
	public ProductDTO(Product product){
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.productCategoryId = product.getCategory()!=null?product.getCategory().getId():null;
		this.category = product.getCategory();
		this.brandId = product.getBrand()!=null?product.getBrand().getId():null;
		for(Image img:product.getImages()){
			this.images.add(img.getPath());
		}
		Set<Integer> paIdSet = new HashSet<>();
		if(product.getProductToProductAttributeRel()!=null){
			
			for(ProductToProductAttributeRel rel:product.getProductToProductAttributeRel()){
				ProductToProductAttributeRelDTO dto = new ProductToProductAttributeRelDTO(rel);
				productToProductAttributeRelDTOs.add(dto);
				paIdSet.add(dto.getProductAttributeId());
				
			}
		} 
		
		for(ProductCategoryToProductAttributeRel rel:product.getCategory().getProductCategoryToProductAttributeRel()){
			if(!paIdSet.contains(rel.getProductAttribute().getId())){
				ProductToProductAttributeRelDTO dto = new ProductToProductAttributeRelDTO(rel.getProductAttribute());
				productToProductAttributeRelDTOs.add(dto);
			}
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

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public List<ProductToProductAttributeRelDTO> getProductToProductAttributeRelDTOs() {
		return productToProductAttributeRelDTOs;
	}

	public void setProductToProductAttributeRelDTOs(
			List<ProductToProductAttributeRelDTO> productToProductAttributeRelDTOs) {
		this.productToProductAttributeRelDTOs = productToProductAttributeRelDTOs;
	}

}
