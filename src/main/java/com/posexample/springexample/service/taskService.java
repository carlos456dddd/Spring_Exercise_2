package com.posexample.springexample.service;

import com.posexample.springexample.dto.TaskResponse;
import com.posexample.springexample.dto.projectResponse;
import com.posexample.springexample.dto.taskRequest;
import com.posexample.springexample.dto.userResponse;
import com.posexample.springexample.model.Enum.TaskStatus;
import com.posexample.springexample.model.Project;
import com.posexample.springexample.model.Task;
import com.posexample.springexample.model.User;
import com.posexample.springexample.repository.projectRepository;
import com.posexample.springexample.repository.taskRepository;
import com.posexample.springexample.repository.userRepository;
import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public TaskResponse createTask(taskRequest task, String name) {
        TaskResponse taskResponse = null;

        try {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            if (authentication != null && authentication.isAuthenticated()) {
                User user_response = userRepo.findByUsername(name);

                Task rpt = taskRepo.save(Task.builder()
                        .title(task.title())
                        .description(task.description())
                        .user(user_response)
                        .build());

                taskResponse = toTaskRepospose(rpt).user(userResponse.builder()
                        .Id(rpt.getUser().getId())
                        .username(rpt.getUser().getUsername())
                        .build()).build();
//            }
        } catch (Exception e) {
            throw new RuntimeException("No se crea la tarea");
        }

        return taskResponse;

    }

    public TaskResponse assingTask(Long id_project, Long id_task) {
        Task a = taskRepo.findById(id_task).orElseThrow();
        Project b = projectRepo.findById(id_project).orElseThrow();
        a.setProject(b);
        taskRepo.save(a);

        return toTaskRepospose(a).project(toProjectResponse(a.getProject())).build();

    }

    public TaskResponse updateStatus(Long id, String status) {

        Task rpt = taskRepo.findById(id).orElseThrow(() -> new RuntimeException("No existe"));
        rpt.setStatus(TaskStatus.valueOf(status.toUpperCase()));
        taskRepo.save(rpt);

        return toTaskRepospose(rpt).build();
    }


    private TaskResponse.TaskResponseBuilder toTaskRepospose(@NonNull Task a) {

        return TaskResponse.builder()
                .id(a.getId())
                .title(a.getTitle())
                .description(a.getDescription())
                .status(a.getStatus().name())
                .created_at(a.getCreatedAt());
    }

    private projectResponse toProjectResponse(Project a) {
        return projectResponse.builder()
                .name(a.getName())
                .description(a.getDescription())
                .build();
    }


}
