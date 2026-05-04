package com.posexample.springexample.controller;

import com.posexample.springexample.dto.projectRequest;
import com.posexample.springexample.dto.projectResponse;
import com.posexample.springexample.model.Project;
import com.posexample.springexample.service.projectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/projects")
public class ProjectController {
    private final projectService projectService;

    public ProjectController(projectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/all/{id}")
    public List<projectResponse> getAllProjectsForUser(@PathVariable Long id) {
        return projectService.getProjectByUserId(id);
    }
    @GetMapping("/{id}")
    public projectResponse getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @PostMapping
    public projectResponse postProjectInUser(@RequestBody projectRequest projectRequest){
        return projectService.createProject(projectRequest);
    }


}
