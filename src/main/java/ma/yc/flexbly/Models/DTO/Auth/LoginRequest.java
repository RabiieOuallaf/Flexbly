package ma.yc.flexbly.Models.DTO.Auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
