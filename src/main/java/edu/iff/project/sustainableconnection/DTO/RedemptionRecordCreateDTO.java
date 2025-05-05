package edu.iff.project.sustainableconnection.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RedemptionRecordCreateDTO(

    @NotBlank(message = "O e-mail não pode estar em branco")
    @Email(message = "O e-mail deve ser válido")
    String email,

    @NotNull(message = "O ID do item resgatado é obrigatório")
    Long rewardItemId
) {
}

