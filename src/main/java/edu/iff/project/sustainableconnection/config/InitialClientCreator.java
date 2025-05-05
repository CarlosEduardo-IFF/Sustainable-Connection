package edu.iff.project.sustainableconnection.config;

import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import edu.iff.project.sustainableconnection.DTO.ClientDTOCreator;
import edu.iff.project.sustainableconnection.service.UserService;

@Component
public class InitialClientCreator implements ApplicationListener<ApplicationReadyEvent> {

    private final UserService userService;

    public InitialClientCreator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        List<ClientDTOCreator> clients = List.of(
            new ClientDTOCreator("Ana Maria", "anaReviraClient@gmail.com", "Revira1@1234", "111111111", 1, 12500, 2000),
            new ClientDTOCreator("Carlos Silva", "carlosReviraClient@gmail.com", "Revira2@1234", "222222222", 5, 10000, 1500),
            new ClientDTOCreator("Beatriz Souza", "beatrizReviraClient@gmail.com", "Revira3@1234", "333333333", 2, 15000, 2500),
            new ClientDTOCreator("Daniel Rocha", "danielReviraClient@gmail.com", "Revira4@1234", "444444444", 6, 11000, 1800),
            new ClientDTOCreator("Fernanda Lima", "fernandaReviraClient@gmail.com", "Revira5@1234", "555555555", 3, 9000, 1000),
            new ClientDTOCreator("Eduardo Alves", "eduReviraClient@gmail.com", "Revira6@1234", "666666666", 7, 13000, 2200),
            new ClientDTOCreator("Juliana Mendes", "julianaReviraClient@gmail.com", "Revira7@1234", "777777777", 1, 16000, 3000),
            new ClientDTOCreator("Roberto Dias", "robertoReviraClient@gmail.com", "Revira8@1234", "888888888", 5, 9500, 1200),
            new ClientDTOCreator("Patrícia Castro", "patriciaReviraClient@gmail.com", "Revira9@1234", "999999999", 2, 14000, 2700),
            new ClientDTOCreator("Marcelo Torres", "marceloReviraClient@gmail.com", "Revira10@1234", "101010101", 1, 10500, 1900)
        );

        for (ClientDTOCreator dto : clients) {
            try {
                userService.registerCreator(dto);
                System.out.println("Cliente criado: " + dto.login());
            } catch (IllegalArgumentException e) {
                System.out.println("Erro ao criar cliente " + dto.login() + ": " + e.getMessage());
            }
        }
    }
}
