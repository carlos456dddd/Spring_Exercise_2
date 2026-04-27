package com.posexample.springexample.service;

import com.posexample.springexample.dto.taskResponse;
import com.posexample.springexample.dto.userResponse;
import com.posexample.springexample.model.Enum.TaskStatus;
import com.posexample.springexample.model.Project;
import com.posexample.springexample.model.Task;
import com.posexample.springexample.model.User;
import com.posexample.springexample.repository.projectRepository;
import com.posexample.springexample.repository.taskRepository;
import com.posexample.springexample.repository.userRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class taskService {

    private final taskRepository taskRepo;
    private final projectRepository projectRepo;
    private final userRepository userRepo;

    public taskService(taskRepository taskRepo, projectRepository projectRepo, userRepository userRepo) {
        this.taskRepo = taskRepo;
        this.projectRepo = projectRepo;
        this.userRepo = userRepo;

    }

    public taskResponse createTask(Task task) {
        //Voy a suponer que ya se que estoy autenticado

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 2. Verificar si está autenticado

        if (authentication != null && authentication.isAuthenticated()) {
            // El principal suele ser un UserDetails (o tu entidad de usuario personalizada)
            Object principal = authentication.getPrincipal();
            authentication.getName();
            User user_response = userRepo.findByUsername(authentication.getName());
            task.setUser(user_response);
            Task a = taskRepo.save(task);
            return new taskResponse(a.getId(), a.getTitle(), a.getDescription(), a.getStatus().name(), a.getCreatedAt());
        }
        //Por el momento lo manejare acá, hasta crear los contoller, esta logica de autenticacion será para otro lado
        return null;
        //Como se quien es el usuario?
        //Diria que se harían comprobaciones de quien es y que esta logeado para poder incluirlo


    }

    public taskResponse assingTask(Long id_project, Long id_task) {
        Task a = taskRepo.findById(id_task).orElseThrow();
        Project b = projectRepo.findById(id_project).orElseThrow();
        a.setProject(b);
        taskRepo.save(a);
        return new taskResponse(a.getId(), a.getTitle(), a.getDescription(), a.getStatus().name(), a.getCreatedAt());
    }

    public taskResponse updateStatus(Long id, TaskStatus status) {

        Task a = taskRepo.findById(id).orElseThrow();
        a.setStatus(status);
        return new taskResponse(a.getId(), a.getTitle(), a.getDescription(), a.getStatus().name(), a.getCreatedAt());
    }

}
