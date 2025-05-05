package edu.iff.project.sustainableconnection.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DiscardedItemCategoryDTO(

    @NotBlank(message = "O nome da categoria não pode estar em branco")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    String name,

    @Min(value = 0, message = "Os pontos por item devem ser zero ou mais")
    int pointsPerItem
) {
}