package edu.iff.project.sustainableconnection.DTO;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressDTO(

    @NotBlank(message = "O nome da rua não pode estar em branco")
    @Size(max = 255, message = "A rua deve ter no máximo 255 caracteres")
    String street,

    @NotBlank(message = "A cidade não pode estar em branco")
    @Size(max = 100, message = "A cidade deve ter no máximo 100 caracteres")
    String city,

    @NotBlank(message = "O estado não pode estar em branco")
    @Size(max = 2, message = "O estado deve conter exatamente 2 caracteres")
    String state,

    @NotBlank(message = "O CEP não pode estar em branco")
    @Pattern(regexp = "\\d{5}-?\\d{3}", message = "CEP inválido. Use o formato 00000-000 ou 00000000")
    String zipCode,

    @NotNull(message = "A latitude é obrigatória")
    @DecimalMin(value = "-90.0", inclusive = true, message = "Latitude mínima é -90")
    @DecimalMax(value = "90.0", inclusive = true, message = "Latitude máxima é 90")
    Double latitude,

    @NotNull(message = "A longitude é obrigatória")
    @DecimalMin(value = "-180.0", inclusive = true, message = "Longitude mínima é -180")
    @DecimalMax(value = "180.0", inclusive = true, message = "Longitude máxima é 180")
    Double longitude
) {
}