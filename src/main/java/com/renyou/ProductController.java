package com.renyou;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.renyou.db.Brand;
import com.renyou.db.Image;
import com.renyou.db.Product;
import com.renyou.db.ProductAttribute;
import com.renyou.db.ProductAttributeType;
import com.renyou.db.ProductCategory;
import com.renyou.db.ProductCategoryToProductAttributeRel;
import com.renyou.db.ProductToProductAttributeRel;
import com.renyou.db.repository.BrandRepository;
import com.renyou.db.repository.ProductAttributeRepository;
import com.renyou.db.repository.ProductAttributeTypeRepository;
import com.renyou.db.repository.ProductCategoryRepository;
import com.renyou.db.repository.ProductRepository;
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

	private static final String IMG_FOLDER = "product";

	@RequestMapping("/listProductCategory")
	public String productCategoryList(Model model) {
		model.addAttribute("productCategories", productCateogryRepository.findAll());

		return "list-product-category";
	}

	@RequestMapping(value = "/editProductCategory", method = RequestMethod.GET)
	public String editProductCategory(@RequestParam(value = "id", required = false) Integer id, Model model) {
		if (id != null) {
			ProductCategory pc = productCateogryRepository.findOne(id);
			model.addAttribute("productCategory", new ProductCategoryDTO(pc));
		} else {
			model.addAttribute("productCategory", new ProductCategoryDTO());
		}
		model.addAttribute("productCategoryList", productCateogryRepository.findAll());
		model.addAttribute("productAttributeList", productAttributeRepository.findAll());
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
		if (productCategoryDTO.getProductAttributeIds() != null
				&& productCategoryDTO.getProductAttributeIds().size() > 0) {
			Iterator<ProductCategoryToProductAttributeRel> it = pc.getProductCategoryToProductAttributeRel().iterator();
			// removing elements
			while (it.hasNext()) {
				boolean isPresent = false;
				for (Integer paId : productCategoryDTO.getProductAttributeIds()) {
					if (it.next().getProductAttribute().getId().equals(paId)) {
						isPresent = true;
						break;
					}
				}
				if (!isPresent) {
					it.remove();
				}
			}
			for (Integer paId : productCategoryDTO.getProductAttributeIds()) {
				ProductAttribute pa = productAttributeRepository.findOne(paId);
				boolean add = true;
				for (ProductCategoryToProductAttributeRel pcToPARel : pc.getProductCategoryToProductAttributeRel()) {
					if (pcToPARel.getProductAttribute().getId().equals(paId)) {
						add = false;
					}
				}
				if (add) {
					ProductCategoryToProductAttributeRel pcToPARel = new ProductCategoryToProductAttributeRel();
					pcToPARel.setProductAttribute(pa);
					pcToPARel.setProductCategory(pc);
					pc.getProductCategoryToProductAttributeRel().add(pcToPARel);
				}

			}
		}
		productCateogryRepository.save(pc);
		model.addAttribute("productCategories", productCateogryRepository.findAll());
		model.addAttribute("message", pc.getName() + " saved successfully.");

		return "list-product-category";
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
	public ModelAndView ProductSave(ProductDTO productDTO, Model model) {
		Product product = new Product(productDTO);
		if (productDTO.getProductCategoryId() != null && productDTO.getProductCategoryId() > 0) {
			ProductCategory ppc = productCateogryRepository.findOne(productDTO.getProductCategoryId());
			if (ppc != null) {
				product.setCategory(ppc);
			}
		}
		if (productDTO.getBrandId() != null && productDTO.getBrandId() > 0) {
			Brand brand = brandRepository.findOne(productDTO.getBrandId());
			if (brand != null) {
				product.setBrand(brand);
			}
		}
		productRepository.save(product);
		productPageSetup(model, product.getId());

		model.addAttribute("message", "Product " + product.getName() + " saved successfully");
		return new ModelAndView("redirect:/editProduct?id=" + product.getId());
	}

	@PostMapping("/saveProductAttributeValue")
	public ModelAndView productAttributeSave(@RequestParam Map<String, String> allRequestParams, Model model) {
		Integer productId = Integer.parseInt(allRequestParams.get("id"));
		Product product = productRepository.findOne(productId);
		for (String modelKey : allRequestParams.keySet()) {
			if (modelKey.startsWith("paId_")) {
				String[] idSplit = modelKey.split("_");
				ProductToProductAttributeRel rel = new ProductToProductAttributeRel();
				if (!idSplit[1].equals("null")) {
					Integer relId = Integer.parseInt(idSplit[1]);
					for (ProductToProductAttributeRel savedRel : product.getProductToProductAttributeRel()) {
						if (savedRel.getId().equals(relId)) {
							rel = savedRel;
							break;
						}
					}
					rel.setValue(allRequestParams.get(modelKey));
				} else {
					rel.setProduct(product);
					Integer productAttributeId = Integer.parseInt(modelKey.split("_")[2]);
					rel.setProductAttribute(productAttributeRepository.findOne(productAttributeId));
					rel.setValue(allRequestParams.get(modelKey));
					product.getProductToProductAttributeRel().add(rel);
				}
			}
			
		}
		productRepository.save(product);
		return new ModelAndView("redirect:/editProduct?id=" + 1);
	}

	@PostMapping("/addProductImage")
	public String handleProductFileUpload(@RequestParam("file") MultipartFile file,
			@RequestParam(value = "id", required = false) Integer id, Model model) {
		if (id != null) {
			storageService.store(IMG_FOLDER, file, id + "_" + file.getOriginalFilename());
			Product product = productRepository.findOne(id);
			product.getImages()
					.add(new Image(IMG_FOLDER + File.separator + id + "_" + file.getOriginalFilename(), product));
			productRepository.save(product);

			model.addAttribute("messageImgUpload",
					"You successfully uploaded " + file.getOriginalFilename() + "! for product:" + id);
		}
		productPageSetup(model, id);
		return "edit-product";
	}

	private void productPageSetup(Model model, Integer productId) {
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
		if (dto.getProductAttributeTypeId() != null) {
			productAttribute
					.setProductAttributeType(productAttributeTypeRepository.findOne(dto.getProductAttributeTypeId()));
		}
		productAttributeRepository.save(productAttribute);
		model.addAttribute("productAttribute", new ProductAttributeDTO(productAttribute));
		model.addAttribute("message", "Product Attribute " + productAttribute.getName() + " saved successfully");
		model.addAttribute("productAttributeTypeList", productAttributeTypeRepository.findAll());
		return "edit-product-attribute";
	}

}
