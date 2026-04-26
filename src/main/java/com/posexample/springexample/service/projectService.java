package com.posexample.springexample.service;

import com.posexample.springexample.dto.projectResponse;
import com.posexample.springexample.model.Project;
import com.posexample.springexample.repository.projectRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class projectService {

    private final projectRepository projectRepo;

    public projectService(projectRepository projectRepo) {
        this.projectRepo = projectRepo;
    }

    public projectResponse createProject(Project projectReq) {
        Project a = projectRepo.save(projectReq);
        return new projectResponse(a.getId(), a.getName(), a.getDescription(), a.getCreatedAt(), a.getUser().getId());
    }

    public projectResponse getProjectById(Long id) {

        Project a = projectRepo.findById(id).orElseThrow();

        return new projectResponse(a.getId(), a.getName(), a.getDescription(), a.getCreatedAt(), a.getUser().getId());
    }


//    public List<projectResponse> probar_function(User user){
//        return projectRepo.getProjectsByUser(user);
//    }



}
