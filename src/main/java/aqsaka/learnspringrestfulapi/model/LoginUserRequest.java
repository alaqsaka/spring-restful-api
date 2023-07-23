package aqsaka.learnspringrestfulapi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class LoginUserRequest {
    // username tidak boleh kosong
    @NotBlank
    @Size(max = 100)
    private String username;

    // password tidak boleh koson
    @NotBlank
    @Size(max = 100)
    private String password;
}
