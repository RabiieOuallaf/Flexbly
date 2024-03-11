package ma.yc.flexbly.Models.DTO.Admin;

import lombok.Data;

@Data
public class AdminDTO {
    private Integer id;
    private String email;
    private String role;
    private String password;
}
