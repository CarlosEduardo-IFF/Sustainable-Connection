package edu.iff.project.sustainableconnection.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DiscardedItemDTO(

    @NotBlank(message = "O e-mail não pode estar em branco")
    @Email(message = "O e-mail deve ser válido")
    String email,

    @NotNull(message = "O ID do ponto de entrega é obrigatório")
    Long dropOffPointId,

    @NotNull(message = "O ID da categoria é obrigatório")
    Long categoryId
) {
}
