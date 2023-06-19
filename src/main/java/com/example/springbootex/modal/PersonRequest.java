package com.example.springbootex.modal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequest {
    @NotEmpty(message = "Person Name cannot be null or empty")
    private String personName;

    @NotBlank(message = "countryCode cannot be null or empty")
    private String countryCode;

    private boolean isValid;

}