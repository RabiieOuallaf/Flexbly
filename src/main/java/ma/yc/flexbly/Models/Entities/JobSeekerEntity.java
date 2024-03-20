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

    private String first_name;
    private String last_name;
    private LocalDate birthdate;
    @Column(unique = true)
    private String email;
    @Column(columnDefinition = "varchar(255) default 'jobSeeker'")
    private String role;

    private int industry_id;
    private String password;

    @Enumerated(EnumType.STRING)
    private EducationLevelEnum education_level;

    private int education_institute_id;


}
