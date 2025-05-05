package edu.iff.project.sustainableconnection.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthenticationDTO(

    @NotBlank(message = "O e-mail não pode estar em branco")
    @Email(message = "Formato de e-mail inválido")
    String login,

    @NotBlank(message = "A senha não pode estar em branco")
    @Size(min = 6, max = 100, message = "A senha deve ter entre 6 e 100 caracteres")
    String password
) {
}
