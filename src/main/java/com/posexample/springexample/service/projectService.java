package com.posexample.springexample.service;

import com.posexample.springexample.dto.projectRequest;
import com.posexample.springexample.dto.projectResponse;
import com.posexample.springexample.model.Project;
import com.posexample.springexample.model.User;
import com.posexample.springexample.repository.projectRepository;
import com.posexample.springexample.repository.userRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@Transactional
public class projectService {

    private final projectRepository projectRepo;
    private final userRepository userRepo;

    public projectService(projectRepository projectRepo, userRepository userRepo) {
        this.projectRepo = projectRepo;
        this.userRepo = userRepo;

    }

    public projectResponse createProject(projectRequest projectReq) {

        User user = userRepo.findById(projectReq.idUser()).orElseThrow();

        Project project = Project.builder()
                .name(projectReq.name())
                .description(projectReq.description())
                .user(user)
                .build();

        Project a = projectRepo.save(project);
        return new projectResponse(a.getId(), a.getName(), a.getDescription(), a.getUser().getId(), a.getCreatedAt());
    }

    public projectResponse getProjectById(Long id) {

        Project a = projectRepo.findById(id).orElseThrow();

        //Faltaría logica para saber quien es el usuario
        return new projectResponse(a.getId(), a.getName(), a.getDescription(), a.getUser().getId(), a.getCreatedAt());
    }

    public List<projectResponse> getProjectByUserId(Long idUser) {

        User id = userRepo.findById(idUser).orElseThrow();
        List<projectResponse> bh = new ArrayList<>();

        projectRepo.findAll().forEach(a ->
                {

                    if (Objects.equals(a.getUser().getId(), idUser)) {
                        bh.add(
                                projectResponse.builder()
                                        .IdUser(a.getUser().getId())
                                        .Id(a.getId())
                                        .name(a.getName())
                                        .description(a.getDescription())
                                        .createdAt(a.getCreatedAt())
                                        .build()
                        );
                    }

                }


        );

        return bh;
        //Id del projecto -> id
////        Long h = 2;
//        User a = userRepo.findById(idUser).orElseThrow();
//        Project g = projectRepo.findById(idUser).orElseThrow();
//        userRepo.
//        g.getUser().getId()


    }


//    public List<projectResponse> probar_function(User user){
//        return projectRepo.getProjectsByUser(user);
//    }


}
