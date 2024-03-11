package ma.yc.flexbly.Models.DTO.Auth.Login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private String email;
    private String token;
}
