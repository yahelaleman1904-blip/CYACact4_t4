# Actividad 4: API REST con Spring Boot, JWT y Validaciones

**Autor:** Carlos Yahel Alemán Cruz  
**Materia:** Programación Web  
**Repositorio:** `CYACact4_t4`  

## 🌐 URL de la API en Producción (VPS)
La API se encuentra desplegada y funcionando en segundo plano en un servidor VPS Ubuntu.
- **Base URL:** `http://216.238.94.85:8090`

## 🛠️ Tecnologías Utilizadas
- **Backend:** Java 17, Spring Boot 3
- **Seguridad:** Spring Security, JSON Web Tokens (JJWT 0.11.5)
- **Base de Datos:** MySQL, Spring Data JPA (Hibernate)
- **Estructura y Validación:** DTOs (Data Transfer Objects), Jakarta Validation (`@Valid`, `@NotBlank`, etc.)
- **Herramientas:** Lombok, Maven
- **Pruebas de API:** Bruno (Colección incluida en la carpeta `/bruno`)
- **Despliegue:** Servidor VPS con Ubuntu, ejecución en segundo plano con `nohup` en el puerto 8090.

## 🚀 Endpoints Principales

### Autenticación (Públicos)
- `POST /api/auth/register` - Registro de nuevos usuarios.
- `POST /api/auth/login` - Inicio de sesión y generación de token JWT.

### CRUD de Productos (Protegidos - Requieren Token JWT)
- `GET /api/productos` - Lista productos (Paginado).
- `POST /api/productos` - Crea un producto (Valida campos DTO).
- `GET /api/productos/{id}` - Obtiene un producto específico.
- `PUT /api/productos/{id}` - Actualiza un producto.
- `DELETE /api/productos/{id}` - Elimina un producto.

## 📸 Evidencias de Funcionamiento

*(Nota: Arrastra aquí las capturas de pantalla de tus pruebas en Bruno)*

1. **Registro Exitoso y Login (Generación de JWT - 200 OK):**
<img width="1630" height="705" alt="Petición Registro de Usuario" src="https://github.com/user-attachments/assets/ce7edd0a-d28d-4506-ac88-dad5f566fa44" />

2. **Rechazo por Seguridad (Petición sin Token - 401/403):**
<img width="1621" height="738" alt="Petición Login de Usuario" src="https://github.com/user-attachments/assets/2ed66c3c-fd23-4ce1-bd50-9d57ef2d5d86" />

3. **Acceso Concedido (Creación y Listado de Productos usando Token):**
<img width="1607" height="825" alt="La Prueba de Fuego (Rechazo sin Token)" src="https://github.com/user-attachments/assets/8ae340e1-f53e-4415-b431-edabd931ac4c" />
