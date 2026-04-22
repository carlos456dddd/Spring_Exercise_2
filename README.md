# 🚀 Project Management System (Spring Boot)

Sistema backend para gestión de proyectos y tareas, enfocado en demostrar buenas prácticas en desarrollo backend con Spring Boot, modelado de dominio y seguridad.

---

# 📌 Descripción

Este proyecto implementa un sistema donde:

* Usuarios pueden crear proyectos
* Los proyectos contienen tareas
* Las tareas pueden asignarse a usuarios
* Se gestionan permisos mediante enums (roles)
* Se aplican reglas de negocio básicas

---

# 🧠 Objetivos de Aprendizaje

Este proyecto está orientado a reforzar:

* Modelado de entidades y relaciones
* Arquitectura en capas
* Uso de enums como estrategia simple de autorización
* Desarrollo de APIs REST
* Separación de responsabilidades
* Organización de código en proyectos reales

---

# 🏗️ Arquitectura

Se utiliza una **arquitectura en capas (Layered Architecture)**:

```text
Controller → Service → Repository → Database
```

---

## 📌 Responsabilidades

### 🎯 Controller

* Maneja las peticiones HTTP
* Define endpoints REST

### 🧠 Service

* Contiene la lógica de negocio
* Valida reglas del sistema

### 🗄️ Repository

* Acceso a la base de datos mediante JPA

### 🧩 Model

* Entidades del sistema
* Enums (roles, estados, prioridades)

---

# 📦 Estructura del proyecto

```text
com.posexample.springexample

├── controller
│   └── IndexController
│
├── dto
│
├── model
│   ├── Enum
│   │   ├── Role
│   │   ├── TaskPriority
│   │   └── TaskStatus
│   │
│   ├── Project
│   ├── Task
│   └── User
│
├── repository
│
├── security
│   └── SecurityConfig
│
├── service
│
└── SpringexampleApplication
```

---

# 🧠 Decisión de diseño: Roles como Enum

En lugar de crear una entidad `Role`, se utiliza un enum:

```java
public enum Role {
    ADMIN,
    MANAGER,
    USER
}
```

---

## 📌 ¿Por qué esta decisión?

* Simplifica el modelo
* Reduce complejidad innecesaria
* Ideal para proyectos pequeños o de aprendizaje

---

## ⚠️ Limitación

* No es escalable si los roles cambian dinámicamente
* No permite persistencia en base de datos

---

# 🗄️ Modelo del sistema

## Entidades principales:

* User
* Project
* Task

---

## 🔗 Relaciones

* Un **User** puede crear muchos **Project**
* Un **Project** tiene muchas **Task**
* Una **Task** pertenece a un **Project**
* Una **Task** puede estar asignada a un **User**

---

# 🔐 Seguridad

Actualmente:

* Configuración básica en `SecurityConfig`
* Preparado para integrar JWT en el futuro

---

## 🧠 Estrategia actual

* Uso de roles mediante enum
* Control de acceso a nivel de lógica (service o controller)

---

# ⚙️ Tecnologías utilizadas

* Java
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* Lombok (opcional)

---

# 📏 Reglas de negocio

* Un proyecto debe tener un usuario creador
* Una tarea debe pertenecer a un proyecto
* Una tarea puede asignarse a un usuario existente
* Estados de tarea:

  * TODO
  * IN_PROGRESS
  * DONE

---

# 🚀 Estado del proyecto

## ✅ Implementado

* Entidades
* Relaciones
* Repositorios
* Estructura base de servicios
* Configuración básica de seguridad

---

## 🚧 En progreso

* Lógica de negocio completa en services
* DTOs
* Mappers

---

## 🔜 Planeado

* Autenticación con JWT
* Autorización por roles
* Validaciones avanzadas
* Manejo global de errores
* Historial de tareas
* Soft delete

---

# 🌐 Frontend (Planeado)

Se planea implementar un frontend con:

* React

---

## 🎯 Funcionalidades esperadas

* Login
* Dashboard de proyectos
* Gestión de tareas
* Cambio de estado
* Asignación de tareas

---

# 🧪 Ejecución del proyecto

```bash
# Clonar repositorio
git clone <repo-url>

# Entrar al proyecto
cd project

# Ejecutar aplicación
./mvnw spring-boot:run
```

---

# 📬 Endpoints (base)

```text
GET    /
```

*(Endpoints adicionales en desarrollo)*

---

# 🧠 Mejoras recomendadas (nivel profesional)

* Separar `model` en:

  * `entity`
  * `enum`
* Agregar capa `mapper`
* Implementar DTOs completamente
* Introducir manejo global de errores (@ControllerAdvice)
* Agregar tests (JUnit)
* Migrar a arquitectura más limpia (hexagonal opcional)

---

# 🎯 Lo que demuestra este proyecto

* Comprensión de Spring Boot
* Organización de código backend
* Modelado de datos
* Uso de enums para simplificar diseño
* Base para escalar a proyectos más complejos

---

# 📄 Licencia

## MIT

