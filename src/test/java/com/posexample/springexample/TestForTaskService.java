package com.posexample.springexample;

import com.posexample.springexample.dto.TaskResponse;
import com.posexample.springexample.dto.taskRequest;
import com.posexample.springexample.model.Enum.TaskStatus;
import com.posexample.springexample.model.Project;
import com.posexample.springexample.model.Task;
import com.posexample.springexample.model.User;
import com.posexample.springexample.repository.projectRepository;
import com.posexample.springexample.repository.taskRepository;
import com.posexample.springexample.repository.userRepository;
import com.posexample.springexample.service.taskService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class TestForTaskService {


    @Mock
    private taskRepository repo1;

    @Mock
    private  projectRepository repo2;

    @Mock
    private  userRepository repo3;

    @InjectMocks
    private taskService service;


    @Test
    @DisplayName("Actualizar el status de tarea")
    void UpdateStatus() {
        Task output = Task.builder()
                .id(1L)
                .title("supa")
                .status(TaskStatus.DONE)
                .build();

        Mockito.when(repo1.findById(1L)).thenReturn(Optional.of(output));

        TaskResponse rpt = service.updateStatus(1L, "TODO");
        assertEquals("TODO", rpt.status());

    }

    @Test
    @DisplayName("Asignar tarea para proyecto")
    void AssingTaskForProyect() {
        Task task = Task.builder()
                .id(1L)
                .title("first")
                .description("The first")
                .createdAt(LocalDateTime.now())
                .build();
        Project project = Project.builder()
                .Id(1L)
                .name("Cama")
                .description("The cama")
                .build();

        Mockito.when(repo1.findById(1L)).thenReturn(Optional.of(task));
        Mockito.when(repo2.findById(1L)).thenReturn(Optional.of(project));

        TaskResponse rpt = service.assingTask(1L, 1L);


        assertEquals(1L,rpt.id());
        assertEquals("Cama", rpt.project().name());
        assertEquals("The cama", rpt.project().description());

    }


    @Test
    @DisplayName("Creando una tarea con datos del usuario")
    void CreateTaskWithDatesFromUser(){
        User user = User.builder()
                .Id(1L)
                .username("car")
                .build();

        Task rpt = Task.builder()
                .id(1L)
                .title("cama")
                .description("coro")
                .status(TaskStatus.DONE)
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();

        Mockito.when(repo1.save(any(Task.class))).thenReturn(rpt);

        Mockito.when(repo3.findByUsername(anyString())).thenReturn(user);

        TaskResponse result = service.createTask(new taskRequest("cama", "coro"), "car");

        assertEquals("car", result.user().username());



    }



}
