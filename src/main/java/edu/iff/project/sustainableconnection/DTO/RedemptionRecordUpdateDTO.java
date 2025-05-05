package edu.iff.project.sustainableconnection.DTO;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record RedemptionRecordUpdateDTO(

    @NotNull(message = "O ID do usuário é obrigatório")
    Long userId,

    @NotNull(message = "O ID do item resgatado é obrigatório")
    Long rewardItemId,

    @NotNull(message = "A data de resgate é obrigatória")
    LocalDateTime redemptionDate

) {
}
