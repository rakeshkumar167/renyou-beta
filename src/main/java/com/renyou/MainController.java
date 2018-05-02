package com.renyou;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.renyou.db.BrandRepository;
import com.renyou.db.DesignerRepository;
import com.renyou.db.ProductCategoryRepository;
import com.renyou.db.ProductRepository;
import com.renyou.db.ProjectRepository;
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

	@Autowired
	private ProjectRepository projectRepository;
	
	@RequestMapping("/")
	public String welcome(Model model) {
		model.addAttribute("spaces", spaceRepository.findAll());
		model.addAttribute("designers", designerRepository.findAll());
		model.addAttribute("projects", projectRepository.findAll());

		return "index";
	}

	@RequestMapping("/admin")
	public String admin(Model model) {
		model.addAttribute("brands", brandRepository.count());
		model.addAttribute("productCategories", productCateogryRepository.count());
		model.addAttribute("spaces", spaceRepository.count());
		model.addAttribute("products", productRepository.count());
		model.addAttribute("designers", designerRepository.count());
		model.addAttribute("projects", projectRepository.count());

		return "admin";
	}
	
	@RequestMapping("/map")
	public String map(Model model) {
		model.addAttribute("brands", brandRepository.findAll());
		model.addAttribute("productCategories", productCateogryRepository.findAll());
		model.addAttribute("spaces", spaceRepository.findAll());
		model.addAttribute("products", productRepository.findAll());
		model.addAttribute("designers", designerRepository.findAll());
		model.addAttribute("projects", projectRepository.findAll());

		return "map";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Model model) {
		return "test";
	}

}
