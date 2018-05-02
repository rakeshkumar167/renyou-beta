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

import com.renyou.db.DesignerRepository;
import com.renyou.db.Image;
import com.renyou.db.ProductRepository;
import com.renyou.db.Project;
import com.renyou.db.ProjectRepository;
import com.renyou.db.ProjectSpace;
import com.renyou.db.ProjectSpaceRepository;
import com.renyou.db.SpaceRepository;
import com.renyou.dto.ProjectDTO;
import com.renyou.dto.ProjectSpaceDTO;
import com.renyou.storage.StorageService;

@Controller
public class ProjectController {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ProjectSpaceRepository projectSpaceRepository;

	@Autowired
	private DesignerRepository designerRepository;

	@Autowired
	private SpaceRepository spaceRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StorageService storageService;

	private static final String PROJECT_IMG_FOLDER = "project";
	
	private static final String PROJECT_SPACE_IMG_FOLDER = "project_space";


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
	public ModelAndView projectSave(ProjectDTO project, Model model) {
		Project proj = new Project(project);
		proj.setDesigner(designerRepository.findOne(project.getDesignerId()));
		proj = projectRepository.save(proj);
		return new ModelAndView("redirect:/addProject?id=" + proj.getId());
	}

	@PostMapping("/addProjectImage")
	public String handleProductFileUpload(@RequestParam("file") MultipartFile file,
			@RequestParam(value = "id", required = false) Integer id, Model model) {
		if (id != null) {
			storageService.store(PROJECT_IMG_FOLDER, file, id + "_" + file.getOriginalFilename());
			Project project = projectRepository.findOne(id);
			project.getImages()
					.add(new Image(PROJECT_IMG_FOLDER + File.separator + id + "_" + file.getOriginalFilename(), project));
			projectRepository.save(project);
			model.addAttribute("project", new ProjectDTO(project));
			model.addAttribute("designerList", designerRepository.findAll());
			model.addAttribute("messageImgUpload",
					"You successfully uploaded " + file.getOriginalFilename() + "! for project:" + id);
		}

		return "edit-project";
	}

	@RequestMapping("/addProjectSpace")
	public String addProjectSpace(@RequestParam(value = "project_id", required = true) Integer projectId,
			@RequestParam(value = "project_space_id", required = false) Integer id, Model model) {
		if (id != null) {
			ProjectSpace projectSpace = projectSpaceRepository.findOne(id);
			model.addAttribute("projectSpace", new ProjectSpaceDTO(projectSpace));
			Project project = projectRepository.findOne(projectId);
			model.addAttribute("project", new ProjectDTO(project));
		} else {
			if (projectId != null) {
				Project project = projectRepository.findOne(projectId);

				model.addAttribute("project", new ProjectDTO(project));
				ProjectSpaceDTO dto = new ProjectSpaceDTO();
				dto.setProjectId(project.getId());
				model.addAttribute("projectSpace", dto);
			}
		}
		model.addAttribute("productList", productRepository.findAll());
		model.addAttribute("spaceList", spaceRepository.findAll());

		return "edit-project-space";
	}

	@RequestMapping(value = "/saveProjectSpace", method = RequestMethod.POST)
	public ModelAndView ProjectSpaceSave(ProjectSpaceDTO projectSpace, Model model) {
		ProjectSpace proj = new ProjectSpace(projectSpace);
		Project project = projectRepository.findOne(projectSpace.getProjectId());
		proj.setProject(project);
		proj.setSpace(spaceRepository.findOne(projectSpace.getSpaceId()));
		proj = projectSpaceRepository.save(proj);
		return new ModelAndView(
				"redirect:/addProjectSpace?project_id=" + proj.getProject().getId() + "&project_space_id=" + proj.getId());
	}
	
	@PostMapping("/addProjectSpaceImage")
	public String handleProductSpaceFileUpload(@RequestParam("file") MultipartFile file,
			@RequestParam(value = "id", required = false) Integer id, Model model) {
		if (id != null) {
			storageService.store(PROJECT_SPACE_IMG_FOLDER, file, id + "_" + file.getOriginalFilename());
			ProjectSpace projectSpace = projectSpaceRepository.findOne(id);
			projectSpace.getImages()
					.add(new Image(PROJECT_SPACE_IMG_FOLDER + File.separator + id + "_" + file.getOriginalFilename(), projectSpace));
			projectSpace = projectSpaceRepository.save(projectSpace);
			model.addAttribute("productList", productRepository.findAll());
			model.addAttribute("spaceList", spaceRepository.findAll());
			model.addAttribute("messageImgUpload",
					"You successfully uploaded " + file.getOriginalFilename() + "! for project Space:" + id);
		}

		return "edit-project";
	}

}
