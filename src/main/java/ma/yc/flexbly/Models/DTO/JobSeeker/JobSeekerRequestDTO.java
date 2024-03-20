package ma.yc.flexbly.Models.DTO.JobSeeker;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JobSeekerRequestDTO {
    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String birthdate;
    private String education_level;
    private String education_institute_id;
    private String role;
    private String password;
}
