package edu.iff.project.sustainableconnection.DTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RedemptionRecordDTOResponse(

    @NotBlank(message = "O nome do item não pode estar em branco")
    String itemName,

    @NotBlank(message = "A descrição do item não pode estar em branco")
    String itemDescription,

    @NotNull(message = "O custo do item não pode ser nulo")
    int itemCostInPoints,

    @NotNull(message = "A data de resgate não pode ser nula")
    LocalDateTime redemptionDate,

    @NotNull(message = "O id do item nao pode ser nulo não pode ser nulo")
    Long idItem
) {}
