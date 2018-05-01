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

import com.renyou.db.DesignerRepository;
import com.renyou.db.Image;
import com.renyou.db.Project;
import com.renyou.db.ProjectRepository;
import com.renyou.db.SpaceRepository;
import com.renyou.dto.ProjectDTO;
import com.renyou.dto.ProjectSpaceDTO;
import com.renyou.storage.StorageService;

@Controller
public class ProjectController {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private DesignerRepository designerRepository;
	
	@Autowired
	private SpaceRepository spaceRepository;
	
	@Autowired
	private StorageService storageService;
	
	private static final String IMG_FOLDER="project";
	
	
	@RequestMapping("/listProjects")
	public String projectList(Model model) {
		model.addAttribute("projects", projectRepository.findAll());
		return "list-project";
	}

	@RequestMapping(value = "/addProject", method = RequestMethod.GET)
	public String projectsAdd(@RequestParam(value = "id", required = false) Integer id, Model model) {
		if (id != null) {
			Project project = projectRepository.findOne(id);
			model.addAttribute("project", new ProjectDTO(project));
		}
		model.addAttribute("designerList", designerRepository.findAll());
		return "edit-project";
	}

	@RequestMapping(value = "/saveProject", method = RequestMethod.POST)
	public String ProjectSave(ProjectDTO project, Model model) {
		Project proj = new Project(project);
		proj.setDesigner(designerRepository.findOne(project.getDesignerId()));
		projectRepository.save(proj);
		model.addAttribute("project", project);
		model.addAttribute("designerList", designerRepository.findAll());
		model.addAttribute("message", "Project " + project.getName() + " saved successfully");
		return "edit-project";
	}
	
    @PostMapping("/addProjectImage")
    public String handleProductFileUpload(@RequestParam("file") MultipartFile file, 
    		@RequestParam(value = "id", required = false) Integer id,
    		Model model) {
    	if (id != null) {
	        storageService.store(IMG_FOLDER, file, id+"_"+file.getOriginalFilename());
    		Project project = projectRepository.findOne(id);
    		project.getImages().add(new Image(IMG_FOLDER+File.separator+id+"_"+file.getOriginalFilename(), project));
    		projectRepository.save(project);
    		model.addAttribute("project", new ProjectDTO(project));
    		model.addAttribute("designerList", designerRepository.findAll());
    		model.addAttribute("messageImgUpload",
	                "You successfully uploaded " + file.getOriginalFilename() + "! for project:"+id);
    	}
		
        return "edit-project";
    }
    
    @RequestMapping("/addProjectSpace")
    public String addProjectSpace(@RequestParam(value = "project_id", required = true) Integer productId,
    		@RequestParam(value = "project_space_id", required = false) Integer id,
    		Model model) {
    	if (productId != null) {
    		Project project = projectRepository.findOne(productId);

    		model.addAttribute("project", new ProjectDTO(project));
    		model.addAttribute("projectSpace", new ProjectSpaceDTO());

    	}
    	model.addAttribute("spaceList", spaceRepository.findAll());
		
        return "edit-project-space";
    }

}
