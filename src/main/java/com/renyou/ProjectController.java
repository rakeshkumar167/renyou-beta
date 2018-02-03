package com.renyou;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.renyou.db.Project;
import com.renyou.db.ProjectRepository;

@Controller
public class ProjectController {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	
	@RequestMapping("/listProjects")
	public String projectList(Model model) {
		model.addAttribute("projects", projectRepository.findAll());
		return "list-project";
	}

	@RequestMapping(value = "/addProject", method = RequestMethod.GET)
	public String projectsAdd(@RequestParam(value = "id", required = false) Integer id, Model model) {
		if (id != null) {
			Project space = projectRepository.findOne(id);
			model.addAttribute("project", space);
		}
		return "edit-project";
	}

	@RequestMapping(value = "/saveProject", method = RequestMethod.POST)
	public String ProjectSave(Project project, Model model) {
		projectRepository.save(project);
		model.addAttribute("project", project);
		model.addAttribute("message", "Project " + project.getName() + " saved successfully");
		return "edit-project";
	}

}
