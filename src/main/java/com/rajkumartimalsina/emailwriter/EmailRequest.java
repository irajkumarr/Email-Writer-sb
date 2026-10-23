package com.rajkumartimalsina.emailwriter;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest {

    @NotBlank(message = "Email content cannot be blank")
    private String emailContent;

    @NotBlank(message = "Email tone cannot be blank")
    private String tone = "professional";
}
