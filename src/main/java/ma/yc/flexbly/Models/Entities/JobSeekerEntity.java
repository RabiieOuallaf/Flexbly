package ma.yc.flexbly.Models.Entities;

import jakarta.persistence.*;
import lombok.*;
import ma.yc.flexbly.Models.Enumes.EducationLevelEnum;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobSeekerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String image;
    private LocalDate birthdate;
    @Column(unique = true)
    private String email;
    @Column(columnDefinition = "varchar(255) default 'jobSeeker'")
    private String role;

    private int industryId;
    private String password;
    private String resumeUrl;
    private String aboutMe;

    @Enumerated(EnumType.STRING)
    private EducationLevelEnum educationLevel;

    private int educationInstituteId;
    private int experienceId;

}
