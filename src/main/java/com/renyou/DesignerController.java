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
import org.springframework.web.servlet.ModelAndView;

import com.renyou.db.Designer;
import com.renyou.db.Image;
import com.renyou.db.repository.DesignerRepository;
import com.renyou.storage.StorageService;


@Controller
public class DesignerController {
	
	@Autowired
	private DesignerRepository designerRepository;
	
	@Autowired
	private StorageService storageService;

	private static final String DESIGNER_IMG_FOLDER = "designer";
	
	
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
	
	@RequestMapping(value = "/promoteDesigner", method = RequestMethod.GET)
	public ModelAndView promoteProject(@RequestParam(value = "id", required = false) Integer id, Model model){
		Designer designer = designerRepository.findOne(id);
		designer.setPromoted(true);
		designerRepository.save(designer);
		return new ModelAndView("redirect:/listDesigners");
	}
	
	@RequestMapping(value = "/demoteDesigner", method = RequestMethod.GET)
	public ModelAndView demoteProject(@RequestParam(value = "id", required = false) Integer id, Model model){
		Designer designer = designerRepository.findOne(id);
		designer.setPromoted(false);
		designerRepository.save(designer);
		return new ModelAndView("redirect:/listDesigners");
	}
	/*
	@PostMapping("/addDesignerImage")
	public ModelAndView handleProductFileUpload(@RequestParam("file") MultipartFile file,
			@RequestParam(value = "id", required = false) Integer id, Model model) {
		if (id != null) {
			storageService.store(DESIGNER_IMG_FOLDER, file, id + "_" + file.getOriginalFilename());
			Designer designer = designerRepository.findOne(id);
			designer.getImages()
					.add(new Image(DESIGNER_IMG_FOLDER + File.separator + id + "_" + file.getOriginalFilename(), designer));
			designerRepository.save(designer);
		}
		return new ModelAndView(
				"redirect:/addDesigner?id=" + id);
	}*/
}
