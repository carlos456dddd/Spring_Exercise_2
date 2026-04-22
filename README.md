# 🚀 Project Management System (Spring Boot Backend)

¡Bienvenido al repositorio del **Project Management System**! Este es un sistema backend en desarrollo para la gestión de proyectos y tareas. El proyecto está diseñado como un ejercicio práctico y formativo enfocado en ilustrar buenas prácticas en el desarrollo backend utilizando **Spring Boot**, modelado de dominio, seguridad y arquitecturas en capas.

---

## 📌 Descripción del Proyecto

El sistema está concebido como una API REST que permite gestionar la relación entre **Usuarios**, **Proyectos** y **Tareas**. 

Actualmente, el esqueleto del proyecto define que:
- Los **Usuarios** pueden registrarse y acceder al sistema.
- Los usuarios pueden crear **Proyectos** propios.
- Dentro de cada Proyecto se pueden organizar múltiples **Tareas**.
- Las tareas tienen estados, prioridades y pueden ser asignadas a un usuario específico.
- La distinción de permisos y roles en el sistema se realiza de forma simple mediante el uso de **Enums** (`ADMIN`, `MANAGER`, `USER`).

---

## 🧠 Objetivos de Aprendizaje

Este proyecto fue estructurado para reforzar y demostrar habilidades en:
- **Modelado de datos y relaciones SQL** mediante **Spring Data JPA** e **Hibernate**.
- Implementación de una **Arquitectura en capas (Controller, Service, Repository)**.
- Diseño de APIs RESTful limpias.
- Integración y configuración inicial de **Spring Security**.
- Mantenimiento y organización del código usando **Lombok**.

---

## ⚙️ Tecnologías y Stack

- **Java 21**
- **Spring Boot 3.x**
  - **Spring Web** (Desarrollo de API REST)
  - **Spring Data JPA** (Persistencia con Hibernate)
  - **Spring Security** (Autenticación y Autorización)
- **Base de Datos:** MySQL
- **Dependencias adicionales:** Lombok (Para reducir boilerplate code)
- **Construcción:** Maven

---

## 🏗️ Arquitectura del Sistema

El proyecto sigue una estructura clásica basada en capas para promover la separación de responsabilidades:

```text
com.posexample.springexample
├── controller/     # Controladores REST (Manejo de peticiones HTTP y rutas)
├── service/        # Lógica de negocio y validación de reglas
├── repository/     # Interfaces JPA para la persistencia de datos (DAO)
├── model/          # Entidades (JPA) y Enums
├── dto/            # Data Transfer Objects (transferencia de información clara)
└── security/       # Configuraciones de seguridad, filtros, JWT
```

---

## 🗄️ Modelo de Dominio

### Entidades Principales
1. **User (Usuario):** Representa a un individuo dentro de la plataforma. Puede crear múltiples proyectos.
2. **Project (Proyecto):** Creado y gestionado por un usuario en particular, actúa como un contenedor de varias tareas.
3. **Task (Tarea):** La unidad básica de trabajo. Pertenecen a un proyecto y se le asignan a un usuario.

### Decisiones de Diseño: Roles y Estados como Enums
Para simplificar la complejidad de bases de datos iniciales y evitar crear tablas adicionales, optamos por usar Enums.
- **Roles (`Role`):** `ADMIN`, `MANAGER`, `USER`.
- **Estados de Tarea (`TaskStatus`):** `TODO`, `IN_PROGRESS`, `DONE`.
- **Prioridad (`TaskPriority`):** `LOW`, `MEDIUM`, `HIGH`.

> **Nota sobre escalabilidad:** Esta es una solución ideal para simplificar la gestión actual. En proyectos donde los roles son personalizables e ilimitados, lo ideal habría sido crear una entidad separada para `Role` en la base de datos. Sin embargo, para este modelo, los Enumerables cumplen con creces los requisitos.

---

## 🚀 Estado Actual del Proyecto

Actualmente, el proyecto está en sus fases iniciales de desarrollo (**WIP - Work in Progress**). Las bases han sido cimentadas para que la lógica de negocio se expanda sobre ellas.

### ✅ Lo que ya está hecho
- Estructuración inicial del proyecto Spring Boot 3 con las carpetas acordes.
- Configuración de dependencias Maven (JPA, Security, MySQL Connector, Lombok, Web).
- Configuración en el archivo `application.properties` con autogeneración de esquema de DB (`update`).
- Creación del modelo de entidades (`User`, `Project`, `Task`) con sus respectivas anotaciones JPA/Hibernate y relaciones funcionales (`@OneToMany`, `@ManyToOne`).
- Creación de Enums para estandarizar Roles, Estados y Prioridades.
- Controladores básicos en `IndexController` para testear el server y accesibilidad básica.

### 🚧 Lo que se está desarrollando (En progreso)
- Desarrollo e integración completa de las capas **Repository** para las entidades.
- Desarrollo de las clases **Service** integrando las respectivas reglas del negocio.
- Uso de **DTOs** (Data Transfer Objects) y **Mappers** para aislar las entidades de la base de datos de las respuestas del API HTTP.

### 🔜 Próximos Pasos y Futuro
- **Seguridad (Spring Security):** Implementar Autenticación basada en **JWT (JSON Web Tokens)** y validación de roles en las rutas (Ej. `@PreAuthorize("hasRole('ADMIN')")`).
- **Validaciones:** Añadir validación de datos a nivel de DTO (`@Valid`, `@NotNull`, `@NotBlank`).
- **Manejo global de excepciones:** Desarrollo de un `@ControllerAdvice` estandarizado para gestionar excepciones de la API de manera uniforme, en vez de mandar stack traces gigantes al cliente.
- **Controladores (Endpoints CRUD):** Exponer las operaciones de creación, edición, borrado y lectura de usuarios, proyectos y tareas.
- **Frontend (Futuro):** Construcción de un panel de control con interfaz y cliente desarrollado que consuma y muestre gráfica los datos desde esta API.

---

## 🧪 Cómo ejecutar este proyecto localmente

### Prerrequisitos
- Tener **Java 21** o superior instalado en el equipo.
- Tener **MySQL Server** en ejecución.

### Pasos a seguir

1. **Clonar el repositorio:**
   ```bash
   git clone <repo-url>
   cd springexample
   ```

2. **Configurar la base de datos:**
   Asegúrate de crear una base de datos en MySQL llamada `spring_tables` (o con el nombre que prefieras si modificas la configuración). Edita la configuración en `src/main/resources/application.properties` en caso que debas ingresar otra cuenta local:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost/spring_tables
   spring.datasource.username=root
   spring.datasource.password=tu_contrasena
   ```

3. **Ejecutar la aplicación:**
   Puedes utilizar el wrapper incluido de Maven (`mvnw`) para iniciar el servidor de Spring Boot:
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Verificar que la aplicación funciona:**
   Visita en tu explorador o a través de Postman una petición HTTP `GET` a las rutas del `IndexController`: 
   - `http://localhost:8080/`
   - `http://localhost:8080/api/public`
   - `http://localhost:8080/api/admin/capa`

---

## 📄 Notas adicionales
Este repositorio refleja el backend del sistema, aplicando y documentando prácticas utilizadas comúnmente en la industria de desarrollo de software con Java/Spring.

*MIT License*
