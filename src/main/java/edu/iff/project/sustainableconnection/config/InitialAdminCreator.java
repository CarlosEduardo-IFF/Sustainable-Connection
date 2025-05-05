package edu.iff.project.sustainableconnection.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import edu.iff.project.sustainableconnection.DTO.RegisterAdmDTO;
import edu.iff.project.sustainableconnection.model.UserRole;
import edu.iff.project.sustainableconnection.service.UserService;

@Component
public class InitialAdminCreator implements ApplicationListener<ApplicationReadyEvent> {

    private final UserService userService;

    public InitialAdminCreator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        RegisterAdmDTO dto = new RegisterAdmDTO("Administrador", "admin@gmail.com", "admin123", UserRole.ADMIN);

        try {
            userService.registerAdm(dto);
            System.out.println("Usuário admin registrado automaticamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Admin já existe ou erro: " + e.getMessage());
        }
    }
}
