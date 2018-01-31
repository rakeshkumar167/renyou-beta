package com.renyou;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.renyou.db.Space;
import com.renyou.db.SpaceRepository;
import com.renyou.db.SpaceType;
import com.renyou.db.SpaceTypeRepository;
import com.renyou.dto.SpaceDTO;

@Controller
public class SpaceController {
	
	@Autowired
	private SpaceRepository spaceRepository;
	
	@Autowired
	private SpaceTypeRepository spaceTypeRepository;
	
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
}
