package com.renyou;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.renyou.db.Designer;
import com.renyou.db.repository.BrandRepository;
import com.renyou.db.repository.DesignerRepository;
import com.renyou.db.repository.ProductCategoryRepository;
import com.renyou.db.repository.ProductRepository;
import com.renyou.db.repository.ProjectRepository;
import com.renyou.db.repository.SpaceRepository;
import com.renyou.dto.DesignerDTO;

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
		List<DesignerDTO> dtos = new ArrayList<DesignerDTO>();
		for(Designer des:designerRepository.findByPromoted(true)){
			dtos.add(new DesignerDTO(des));
		}
		model.addAttribute("designers", dtos);
		model.addAttribute("projects", projectRepository.findByPromoted(true));

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

}
