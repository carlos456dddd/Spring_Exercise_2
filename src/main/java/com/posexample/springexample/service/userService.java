package com.posexample.springexample.service;
import com.posexample.springexample.dto.userRequest;
import com.posexample.springexample.dto.userResponse;
import com.posexample.springexample.model.User;
import com.posexample.springexample.repository.userRepository;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class userService {
    //    @Autowired
//    private userRepository userRepo;
    private final userRepository userRepo;

    public userService(userRepository userRepo) {
        this.userRepo = userRepo;
    }


//    public record userResponse(Long ,
//                               String username,
//                               LocalDateTime created_at) {
//    }

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long Id;
//    @Column(unique = true, nullable = false, length = 30)
//    private String username;
//
//    @Column(unique = true, nullable = false)
//    private String email;
//    @Column(nullable = false)
//    private String password;
//    private Boolean enable = true;
//
//    @CreationTimestamp
//    @Column(name = "created_at", updatable = false)
//    private LocalDateTime createdAt;
//
//    @UpdateTimestamp
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;
//
//    @Enumerated(EnumType.STRING)
//    private Role role;

    public userResponse createUser(userRequest user) {
        //Según lo que entiendo tendría ya comprobada la información por el DTO encargado de el request
        //Ya que tengo encargada la información del request y esta comprobado que se tiene los datos podría crear la clase User y mandarla
        //Para guardarla y tenerla en la base de datos
        User java = User.builder()
                .username(user.username())
                .email(user.email())
                .password(user.password()).build();

        User a = userRepo.save(java);
        return new userResponse(
                a.getId(),
                a.getUsername(),
                a.getEmail(),
                a.getCreatedAt()
        );
    }

    public List<userResponse> findAll() {
        //Tengo que transformar para que este como el dto -> userResponse
        List<userResponse> a = new ArrayList<>();

        //Mapper, tendría que estar, fue mas prueba no se realmente si existe una forma forma estandar de esto pero bueno, seguí mas mi instinto asi que salio en base a ayuda del ID y poco mas
        userRepo.findAll().iterator().forEachRemaining(e -> a.add(new userResponse(e.getId(), e.getUsername(), e.getEmail(), e.getCreatedAt())));

        //Supongo que la función si esta usando lo que se requiere
        return a;
    }

    public userResponse findById(Long id) {

        User d = userRepo.findById(id).orElseThrow();

        //Sería mejor un manejo de errores personalizados reo que la otro metodo tiene una opcion para eso
        return new userResponse(d.getId(), d.getUsername(), d.getEmail(), d.getCreatedAt());
    }


}
