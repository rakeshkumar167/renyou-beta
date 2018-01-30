package com.renyou;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.renyou.db.Brand;
import com.renyou.db.BrandRepository;
import com.renyou.db.Product;
import com.renyou.db.ProductCategory;
import com.renyou.db.ProductCategoryRepository;
import com.renyou.db.ProductRepository;
import com.renyou.db.Space;
import com.renyou.db.SpaceRepository;
import com.renyou.db.SpaceType;
import com.renyou.db.SpaceTypeRepository;
import com.renyou.dto.BrandDTO;
import com.renyou.dto.ProductCategoryDTO;
import com.renyou.dto.ProductDTO;
import com.renyou.dto.SpaceDTO;
import com.renyou.storage.StorageService;

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
	private SpaceTypeRepository spaceTypeRepository;
	
	@Autowired
	private StorageService storageService;

	@RequestMapping("/")
	public String welcome(Model model) {
		model.addAttribute("brands", brandRepository.count());
		model.addAttribute("productCategories", productCateogryRepository.count());
		model.addAttribute("spaces", spaceRepository.count());
		return "index";
	}

	@RequestMapping("/listBrands")
	public String brandsList(Model model) {
		model.addAttribute("brands", brandRepository.findAll());
		return "list-brand";
	}

	@RequestMapping(value = "/editBrand", method = RequestMethod.GET)
	public String brandsAdd(@RequestParam(value = "id", required = false) Integer id, Model model) {
		if (id != null) {
			Brand brand = brandRepository.findOne(id);
			model.addAttribute("brand", new BrandDTO(brand.getId(), brand.getName(), brand.getDescription()));
		}
		return "edit-brand";
	}

	@RequestMapping(value = "/saveBrand", method = RequestMethod.POST)
	public String brandsSave(BrandDTO brand, Model model) {
		brandRepository.save(new Brand(brand));
		model.addAttribute("brand", brand);
		model.addAttribute("message", "Brand " + brand.getName() + " saved successfully");
		return "edit-brand";
	}
	
	@RequestMapping("/listSpaces")
	public String spacesList(Model model) {
		model.addAttribute("spaces", spaceRepository.findAll());
		return "list-space";
	}

	@RequestMapping(value = "/editSpace", method = RequestMethod.GET)
	public String spacesEdit(@RequestParam(value = "id", required = false) Integer id, Model model) {
		if (id != null) {
			Space space = spaceRepository.findOne(id);
			

			model.addAttribute("space", new SpaceDTO(space));
		}
		model.addAttribute("spaceTypeList", spaceTypeRepository.findAll());
		return "edit-space";
	}

	@RequestMapping(value = "/saveSpace", method = RequestMethod.POST)
	public String spacesSave(SpaceDTO dto, Model model) {
		Space space = new Space(dto);
		if(dto.getSpaceTypeId()!=null){
			space.setSpaceType(spaceTypeRepository.findOne(dto.getSpaceTypeId()));
		}
		spaceRepository.save(space);
		model.addAttribute("space", new SpaceDTO(space));
		model.addAttribute("message", "Space " + space.getName() + " saved successfully");
		model.addAttribute("spaceTypeList", spaceTypeRepository.findAll());
		return "edit-space";
	}
	
	@RequestMapping("/listSpaceTypes")
	public String spacesTypesList(Model model) {
		model.addAttribute("spaceTypes", spaceTypeRepository.findAll());
		return "list-space-type";
	}

	@RequestMapping(value = "/editSpaceType", method = RequestMethod.GET)
	public String spaceTypesAdd(@RequestParam(value = "id", required = false) Integer id, Model model) {
		if (id != null) {
			SpaceType space = spaceTypeRepository.findOne(id);
			model.addAttribute("spaceType", space);
		}
		return "edit-space-type";
	}

	@RequestMapping(value = "/saveSpaceType", method = RequestMethod.POST)
	public String spaceTypeSave(SpaceType space, Model model) {
		spaceTypeRepository.save(space);
		model.addAttribute("spaceType", space);
		model.addAttribute("message", "spaceType " + space.getName() + " saved successfully");
		return "edit-space-type";
	}

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
		if (id != null) {
			Product p = productRepository.findOne(id);
			model.addAttribute("product", new ProductDTO(p.getId(), p.getName(), p.getDescription(),
					p.getCategory() != null ? p.getCategory().getId() : null,
					p.getBrand() != null? p.getBrand().getId():null));
		}
		model.addAttribute("productCategoryList", productCateogryRepository.findAll());
		model.addAttribute("brandList", brandRepository.findAll());

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
		model.addAttribute("product", new ProductDTO(product.getId(), product.getName(), product.getDescription(),
				product.getCategory() != null ? product.getCategory().getId() : null, 
				product.getBrand() != null? product.getBrand().getId():null));
		model.addAttribute("productCategoryList", productCateogryRepository.findAll());
		model.addAttribute("message", "Product " + product.getName() + " saved successfully");

		return "edit-product";
	}
	
	@RequestMapping("/upload")
	public String upload(Model model) {
		return "upload";
	}
	
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "upload";
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
