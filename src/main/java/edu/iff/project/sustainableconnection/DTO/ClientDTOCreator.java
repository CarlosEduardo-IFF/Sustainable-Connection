package edu.iff.project.sustainableconnection.DTO;

import edu.iff.project.sustainableconnection.model.Client;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record ClientDTOCreator (

    @NotBlank(message = "O nome não pode estar em branco")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    String name,

    @NotBlank(message = "O e-mail não pode estar em branco")
    @Email(message = "Formato de e-mail inválido")
    String login,

    @NotBlank(message = "A senha não pode estar em branco")
    @Size(min = 6, max = 100, message = "A senha deve ter entre 6 e 100 caracteres")
    String password,

    @NotBlank(message = "A matrícula não pode estar em branco")
    @Size(max = 50, message = "A matrícula deve ter no máximo 50 caracteres")
    String matriculation,

    @NotBlank(message = "O avatar não pode estar em branco")
    int avatar,

    @PositiveOrZero(message = "Os pontos não podem ser negativos")
    int points,

    @PositiveOrZero(message = "Os créditos não podem ser negativos")
    int credits

){

    public static ClientDTOCreator fromEntity(Client client) {
        return new ClientDTOCreator(
            client.getName(),
            client.getEmail(),
            client.getPassword(),
            client.getMatriculation(),
            client.getAvatar(),
            client.getPoints(),
            client.getCredits()
        );
    }
    
}
