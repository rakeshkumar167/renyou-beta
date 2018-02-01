package com.renyou;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.renyou.db.Brand;
import com.renyou.db.BrandRepository;
import com.renyou.db.Image;
import com.renyou.db.Product;
import com.renyou.db.ProductAttribute;
import com.renyou.db.ProductAttributeRepository;
import com.renyou.db.ProductAttributeType;
import com.renyou.db.ProductAttributeTypeRepository;
import com.renyou.db.ProductCategory;
import com.renyou.db.ProductCategoryRepository;
import com.renyou.db.ProductRepository;
import com.renyou.dto.ProductAttributeDTO;
import com.renyou.dto.ProductCategoryDTO;
import com.renyou.dto.ProductDTO;
import com.renyou.storage.StorageService;

@Controller
public class ProductController {
	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private ProductCategoryRepository productCateogryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductAttributeTypeRepository productAttributeTypeRepository;
	
	@Autowired
	private ProductAttributeRepository productAttributeRepository;
	
	@Autowired
	private StorageService storageService;
	
	private static final String IMG_FOLDER="product";
	
	@RequestMapping("/listProductCategory")
	public String productCategoryList(Model model) {
		model.addAttribute("productCategories", productCateogryRepository.findAll());

		return "list-product-category";
	}

	@RequestMapping(value = "/editProductCategory", method = RequestMethod.GET)
	public String editProductCategory(@RequestParam(value = "id", required = false) Integer id, Model model) {
		if (id != null) {
			ProductCategory pc = productCateogryRepository.findOne(id);
			model.addAttribute("productCategory", new ProductCategoryDTO(pc.getId(), pc.getName(), pc.getDescription(),
					pc.getParentProductCategory() != null ? pc.getParentProductCategory().getId() : null));
		}
		model.addAttribute("productCategoryList", productCateogryRepository.findAll());
		return "edit-product-category";
	}

	@RequestMapping(value = "/saveProductCategory", method = RequestMethod.POST)
	public String ProductCategorySave(ProductCategoryDTO productCategoryDTO, Model model) {
		ProductCategory pc = new ProductCategory(productCategoryDTO);
		if (productCategoryDTO.getParentProductCategoryId() != null
				&& productCategoryDTO.getParentProductCategoryId() > 0) {
			ProductCategory ppc = productCateogryRepository.findOne(productCategoryDTO.getParentProductCategoryId());
			if (ppc != null) {
				pc.setParentProductCategory(ppc);
			}
		}
		productCateogryRepository.save(pc);
		model.addAttribute("productCategory", new ProductCategoryDTO(pc.getId(), pc.getName(), pc.getDescription(),
				pc.getParentProductCategory() != null ? pc.getParentProductCategory().getId() : null));
		model.addAttribute("productCategoryList", productCateogryRepository.findAll());
		model.addAttribute("message", "ProductCateogry " + pc.getName() + " saved successfully");

		return "edit-product-category";
	}
	
	@RequestMapping("/listProducts")
	public String productsList(Model model) {
		model.addAttribute("products", productRepository.findAll());
		return "list-products";
	}
	
	@RequestMapping(value = "/editProduct", method = RequestMethod.GET)
	public String editProduct(@RequestParam(value = "id", required = false) Integer id, Model model) {
		productPageSetup(model, id);

		return "edit-product";
	}

	@RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
	public String ProductSave(ProductDTO productDTO, Model model) {
		Product product = new Product(productDTO);
		if (productDTO.getProductCategoryId() != null
				&& productDTO.getProductCategoryId() > 0) {
			ProductCategory ppc = productCateogryRepository.findOne(productDTO.getProductCategoryId());
			if (ppc != null) {
				product.setCategory(ppc);
			}
		}
		if (productDTO.getBrandId() != null
				&& productDTO.getBrandId() > 0) {
			Brand brand = brandRepository.findOne(productDTO.getBrandId());
			if (brand != null) {
				product.setBrand(brand);
			}
		}
		productRepository.save(product);
		productPageSetup(model, product.getId());

		model.addAttribute("message", "Product " + product.getName() + " saved successfully");

		return "edit-product";
	}
	
    @PostMapping("/addProductImage")
    public String handleProductFileUpload(@RequestParam("file") MultipartFile file, 
    		@RequestParam(value = "id", required = false) Integer id,
    		Model model) {
    	if (id != null) {
	        storageService.store(IMG_FOLDER, file, id+"_"+file.getOriginalFilename());
    		Product product = productRepository.findOne(id);
    		product.getImages().add(new Image(IMG_FOLDER+File.separator+id+"_"+file.getOriginalFilename(), product));
    		productRepository.save(product);
    		
    		model.addAttribute("messageImgUpload",
	                "You successfully uploaded " + file.getOriginalFilename() + "! for product:"+id);
    	}
    	productPageSetup(model, id);
        return "edit-product";
    }
    
    private void productPageSetup(Model model, Integer productId){
    	if (productId != null) {
			Product p = productRepository.findOne(productId);
			ProductDTO dto = new ProductDTO(p);
			model.addAttribute("product", dto);
		}
		model.addAttribute("productCategoryList", productCateogryRepository.findAll());
		model.addAttribute("brandList", brandRepository.findAll());
    }
    
	@RequestMapping("/listProductAttributeTypes")
	public String productAttributeTypesList(Model model) {
		model.addAttribute("productAttributeTypes", productAttributeTypeRepository.findAll());
		return "list-product-attribute-type";
	}

	@RequestMapping(value = "/editProductAttributeType", method = RequestMethod.GET)
	public String productAttributeTypesAdd(@RequestParam(value = "id", required = false) Integer id, Model model) {
		if (id != null) {
			ProductAttributeType productAttributeType = productAttributeTypeRepository.findOne(id);
			model.addAttribute("productAttributeType", productAttributeType);
		}
		return "edit-product-attribute-type";
	}

	@RequestMapping(value = "/saveProductAttributeType", method = RequestMethod.POST)
	public String productAttributeTypeSave(ProductAttributeType productAttributeType, Model model) {
		productAttributeTypeRepository.save(productAttributeType);
		model.addAttribute("productAttributeType", productAttributeType);
		model.addAttribute("message", "productAttributeType " + productAttributeType.getName() + " saved successfully");
		return "edit-product-attribute-type";
	}
	
	
	@RequestMapping("/listProductAttributes")
	public String productAttributeList(Model model) {
		model.addAttribute("productAttributes", productAttributeRepository.findAll());
		return "list-product-attribute";
	}

	@RequestMapping(value = "/editProductAttribute", method = RequestMethod.GET)
	public String productAttributeEdit(@RequestParam(value = "id", required = false) Integer id, Model model) {
		if (id != null) {
			ProductAttribute productAttribute = productAttributeRepository.findOne(id);
			model.addAttribute("productAttribute", new ProductAttributeDTO(productAttribute));
		}
		model.addAttribute("productAttributeTypeList", productAttributeTypeRepository.findAll());
		return "edit-product-attribute";
	}

	@RequestMapping(value = "/saveProductAttribute", method = RequestMethod.POST)
	public String productAttributeSave(ProductAttributeDTO dto, Model model) {
		ProductAttribute productAttribute = new ProductAttribute(dto);
		if(dto.getProductAttributeTypeId()!=null){
			productAttribute.setProductAttributeType(productAttributeTypeRepository.findOne(dto.getProductAttributeTypeId()));
		} 
		productAttributeRepository.save(productAttribute);
		model.addAttribute("productAttribute", new ProductAttributeDTO(productAttribute));
		model.addAttribute("message", "Product Attribute " + productAttribute.getName() + " saved successfully");
		model.addAttribute("productAttributeTypeList", productAttributeTypeRepository.findAll());
		return "edit-product-attribute";
	}

}
