package edu.iff.project.sustainableconnection.DTO;

import jakarta.validation.constraints.NotBlank;

public record LoginResponseDTO(

    @NotBlank(message = "O token não pode estar em branco")
    String token
) {
}
