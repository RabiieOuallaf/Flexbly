package ma.yc.flexbly.Models.DTO.Auth.Register;

import lombok.Data;
import ma.yc.flexbly.Models.Enumes.EducationLevelEnum;
import java.time.LocalDate;

@Data
public class RegisterRequestDTO {
    private String firstName;
    private String lastName;
    private String image;
    private LocalDate birthdate;
    private String email;
    private int industryId;
    private String password;
    private String resumeUrl;
    private String aboutMe;
    private EducationLevelEnum educationLevel;
    private int educationInstituteId;
}
