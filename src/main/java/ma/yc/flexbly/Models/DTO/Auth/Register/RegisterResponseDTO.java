package ma.yc.flexbly.Models.DTO.Auth.Register;

import lombok.Data;

@Data
public class RegisterResponseDTO {
    private String email;
    private String token;
}
