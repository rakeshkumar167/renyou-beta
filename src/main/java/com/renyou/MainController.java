package com.renyou;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.renyou.db.BrandRepository;
import com.renyou.db.DesignerRepository;
import com.renyou.db.ProductCategoryRepository;
import com.renyou.db.ProductRepository;
import com.renyou.db.SpaceRepository;

@Controller
public class MainController {

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private ProductCategoryRepository productCateogryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private SpaceRepository spaceRepository;
	
	@Autowired
	private DesignerRepository designerRepository;

	@RequestMapping("/")
	public String welcome(Model model) {
		model.addAttribute("brands", brandRepository.count());
		model.addAttribute("productCategories", productCateogryRepository.count());
		model.addAttribute("spaces", spaceRepository.count());
		model.addAttribute("products", productRepository.count());
		model.addAttribute("designers", designerRepository.count());

		return "index";
	}

	public BrandRepository getBrandRepository() {
		return brandRepository;
	}

	public void setBrandRepository(BrandRepository brandRepository) {
		this.brandRepository = brandRepository;
	}

	public ProductCategoryRepository getProductCateogryRepository() {
		return productCateogryRepository;
	}

	public void setProductCateogryRepository(ProductCategoryRepository productCateogryRepository) {
		this.productCateogryRepository = productCateogryRepository;
	}

	public ProductRepository getProductRepository() {
		return productRepository;
	}

	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
}
