package edu.iff.project.sustainableconnection.controller;

import edu.iff.project.sustainableconnection.DTO.AuthenticationDTO;
import edu.iff.project.sustainableconnection.DTO.LoginResponseDTO;
import edu.iff.project.sustainableconnection.DTO.RegisterDTO;
import edu.iff.project.sustainableconnection.model.User;
import edu.iff.project.sustainableconnection.security.TokenService;
import edu.iff.project.sustainableconnection.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO data){
        try {
            userService.register(data);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authHeader) {
        try {
            // Remove o prefixo "Bearer " do token, se houver
            String token = authHeader.replace("Bearer ", "").trim();

            // Valida o token
            String subject = tokenService.validateToken(token);

            if (!subject.isEmpty()) {
                return ResponseEntity.ok().build(); // 200 OK se o token for válido
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro ao validar token.");
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String authHeader) {
        try {
            // Remove o prefixo "Bearer " do token, se houver
            String token = authHeader.replace("Bearer ", "").trim();

            // Valida o token e pega o subject (usuário) associado ao token
            String subject = tokenService.validateToken(token);

            if (!subject.isEmpty()) {
                // O token é válido, agora você pode buscar o usuário pelo subject (ID ou email)
                UserDetails user = userService.findByEmail(subject); // Supondo que você tenha um método para buscar o usuário
                return ResponseEntity.ok(user); // Retorna os dados do usuário
            } else {
                // Token inválido
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a requisição.");
        }
    }
}
