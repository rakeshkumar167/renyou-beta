package com.renyou;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.renyou.db.Designer;
import com.renyou.db.DesignerRepository;


@Controller
public class DesignerController {
	
	@Autowired
	private DesignerRepository designerRepository;
	
	
	@RequestMapping("/listDesigners")
	public String designerList(Model model) {
		model.addAttribute("designers", designerRepository.findAll());
		return "list-designer";
	}

	@RequestMapping(value = "/addDesigner", method = RequestMethod.GET)
	public String designersAdd(@RequestParam(value = "id", required = false) Integer id, Model model) {
		if (id != null) {
			Designer space = designerRepository.findOne(id);
			model.addAttribute("designer", space);
		}
		return "edit-designer";
	}

	@RequestMapping(value = "/saveDesigner", method = RequestMethod.POST)
	public String DesignerSave(Designer designer, Model model) {
		designerRepository.save(designer);
		model.addAttribute("designer", designer);
		model.addAttribute("message", "Designer " + designer.getName() + " saved successfully");
		return "edit-designer";
	}
}
