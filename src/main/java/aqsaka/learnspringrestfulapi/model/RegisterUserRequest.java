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

public class RegisterUserRequest {
    // ga boleh kosong
    // maksimal panjangnya 100
    @NotBlank
    @Size(max = 100)
    private String username;

    // ga boleh kosong
    // maksimal panjangnya 100
    @NotBlank
    @Size(max = 100)
    private String password;

    // ga boleh kosong
    // maksimal panjangnya 100
    @NotBlank
    @Size(max = 100)
    private String name;
}
