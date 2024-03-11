package ma.yc.flexbly.Models.DTO.Auth.Login;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private String password;
}
