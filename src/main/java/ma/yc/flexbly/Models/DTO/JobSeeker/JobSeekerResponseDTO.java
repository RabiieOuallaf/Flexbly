package ma.yc.flexbly.Models.DTO.JobSeeker;

import lombok.Data;

@Data
public class JobSeekerResponseDTO {
    private Integer id;
    private String email;
    private String role;
    private String password;
}

