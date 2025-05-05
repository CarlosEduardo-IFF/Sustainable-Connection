package edu.iff.project.sustainableconnection.DTO;

import edu.iff.project.sustainableconnection.model.Client;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

public record ClientDTO(

    @NotBlank(message = "O avatar não pode estar em branco")
    int avatar,

    @NotBlank(message = "O nome não pode estar em branco")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    String name,

    @NotBlank(message = "A matrícula não pode estar em branco")
    @Size(max = 50, message = "A matrícula deve ter no máximo 50 caracteres")
    String matriculation,

    @NotBlank(message = "O e-mail não pode estar em branco")
    @Email(message = "O e-mail deve ser válido")
    String email,

    @Min(value = 0, message = "A pontuação deve ser zero ou positiva")
    int points,

    @Min(value = 0, message = "Os créditos devem ser zero ou positivos")
    int credits

) {

    public static ClientDTO fromEntity(Client client) {
        return new ClientDTO(
            client.getAvatar(),
            client.getName(),
            client.getMatriculation(),
            client.getEmail(),
            client.getPoints(),
            client.getCredits()
        );
    }
}