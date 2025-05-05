package edu.iff.project.sustainableconnection.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DropOffPointDTO(

    @NotBlank(message = "O nome do ponto de entrega não pode estar em branco")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    String name,

    @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres")
    String description,

    @NotNull(message = "O ID do endereço é obrigatório")
    Long addressId
) {
}
