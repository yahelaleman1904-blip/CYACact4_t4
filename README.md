# Actividad 4: API REST con Spring Boot, JWT y Validaciones
**Autor:** Carlos Yahel Alemán Cruz
**Materia:** Programación Web

## Descripción
API REST desarrollada en Spring Boot que implementa seguridad con JSON Web Tokens (JWT) y validación de datos (DTOs). El sistema protege un CRUD completo de Productos, exigiendo autenticación para su acceso.

## Evidencias de Funcionamiento (Bruno)

1. **Registro de Usuario (201 Created):**
<img width="1630" height="705" alt="Petición Registro de Usuario" src="https://github.com/user-attachments/assets/bdb6f8bd-f499-47db-9378-7c738eb5a4f7" />


2. **Login y Generación de JWT (200 OK):**
<img width="1621" height="738" alt="Petición Login de Usuario" src="https://github.com/user-attachments/assets/565013d7-df5a-47bc-af6a-06797cf29672" />


3. **Prueba de Seguridad - Petición sin Token (401/403):**
<img width="1607" height="825" alt="La Prueba de Fuego (Rechazo sin Token)" src="https://github.com/user-attachments/assets/14243015-bf87-4362-b6ea-6d0f3383380c" />


4. **Acceso al CRUD con Token - Listar/Crear Producto:**
<img width="1623" height="805" alt="Configurar las Peticiones Protegidas (Con Token)" src="https://github.com/user-attachments/assets/811a2029-142a-4aa5-bcfe-9be3bddebc7d" />
