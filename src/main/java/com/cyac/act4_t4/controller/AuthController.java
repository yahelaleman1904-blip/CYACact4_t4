package com.cyac.act4_t4.controller;

import com.cyac.act4_t4.dto.AuthRequestDTO;
import com.cyac.act4_t4.dto.AuthResponseDTO;
import com.cyac.act4_t4.model.Usuario;
import com.cyac.act4_t4.repository.UsuarioRepository;
import com.cyac.act4_t4.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ENDPOINT DE REGISTRO
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody AuthRequestDTO authRequest) {
        // Verificar si el usuario ya existe
        if (usuarioRepository.findByUsername(authRequest.getUsername()).isPresent()) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "El nombre de usuario ya está en uso");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        // Crear el nuevo usuario y encriptar su contraseña
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(authRequest.getUsername());
        nuevoUsuario.setPassword(passwordEncoder.encode(authRequest.getPassword())); // ¡Muy importante encriptar!
        
        usuarioRepository.save(nuevoUsuario);

        Map<String, String> exito = new HashMap<>();
        exito.put("mensaje", "Usuario registrado exitosamente");
        return ResponseEntity.status(HttpStatus.CREATED).body(exito);
    }

    // ENDPOINT DE LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthRequestDTO authRequest) {
        try {
            // Spring Security intenta autenticar con las credenciales
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "Credenciales incorrectas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error); // 401 Unauthorized
        }

        // Si las credenciales son correctas, generamos el JWT
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        // Regresamos el token en formato JSON
        return ResponseEntity.ok(new AuthResponseDTO(jwt));
    }
}