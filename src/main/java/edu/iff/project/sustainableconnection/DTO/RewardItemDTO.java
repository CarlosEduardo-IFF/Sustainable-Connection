package edu.iff.project.sustainableconnection.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RewardItemDTO(

    @NotBlank(message = "O nome do item é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    String name,

    @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres")
    String description,

    @Min(value = 1, message = "O custo em pontos deve ser de pelo menos 1 ponto")
    int costInPoints,

    @Min(value = 0, message = "A quantidade não pode ser negativa")
    int quantity

) {
}
