package com.renyou;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.renyou.db.Brand;
import com.renyou.db.BrandRepository;
import com.renyou.dto.BrandDTO;

@Controller
public class BrandController {
	@Autowired
	private BrandRepository brandRepository;
	

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

}
