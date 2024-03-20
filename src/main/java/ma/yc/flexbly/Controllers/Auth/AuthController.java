package ma.yc.flexbly.Controllers.Auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.flexbly.Jwt.JwtUtil;
import ma.yc.flexbly.Models.DTO.Admin.AdminDTO;
import ma.yc.flexbly.Models.DTO.Auth.Login.LoginRequestDTO;
import ma.yc.flexbly.Models.DTO.Auth.Login.LoginResponseDTO;
import ma.yc.flexbly.Models.DTO.JobSeeker.JobSeekerRequestDTO;
import ma.yc.flexbly.Models.DTO.JobSeeker.JobSeekerResponseDTO;
import ma.yc.flexbly.Models.Entities.AdminEntity;
import ma.yc.flexbly.Models.Entities.JobSeekerEntity;
import ma.yc.flexbly.Services.AdminService;
import ma.yc.flexbly.Services.JobSeekerService;
import ma.yc.flexbly.Services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private final AuthenticationManager authenticationManager;
    private final JobSeekerService jobSeekerService;
    private final AdminService adminService;
    private final UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login/jobseeker")
    public ResponseEntity<LoginResponseDTO> loginJobSeeker(@RequestBody LoginRequestDTO loginRequest) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        String email = userDetails.getUsername();

        if(Objects.equals(loginRequest.getPassword(), userDetails.getPassword())) {

            JobSeekerResponseDTO jobSeekerDTO = jobSeekerService.getJobSeekerByEmail(email);
            if (jobSeekerDTO == null) {
                ResponseEntity.status(404).body("JobSeeker not found");
            }
            JobSeekerEntity jobSeekerEntity = new JobSeekerEntity().builder()
                    .email(jobSeekerDTO.getEmail())
                    .password(jobSeekerDTO.getPassword())
                    .role(jobSeekerDTO.getRole())
                    .build();
            String accessToken = jwtUtil.generateJobSeekerAccessToken(jobSeekerEntity);
            String refreshToken = jwtUtil.generateJobSeekerRefreshToken(jobSeekerEntity);

            LoginResponseDTO loginResponse = new LoginResponseDTO(email, accessToken,refreshToken);
            return ResponseEntity.ok(loginResponse);
        } else {
            log.warn("JobSeeker with email: " + loginRequest.getEmail() + " not found");
            return null;
        }

    }
    @PostMapping("/register/jobseeker")
    public ResponseEntity<JobSeekerResponseDTO> registerJobSeeker(@RequestBody JobSeekerRequestDTO jobSeekerDTO) {
        if (jobSeekerDTO == null) {
            return ResponseEntity.status(400).body(null);
        }
        JobSeekerResponseDTO createdJobSeeker = jobSeekerService.createJobSeeker(jobSeekerDTO);

        return ResponseEntity.ok().body(createdJobSeeker);
    }


    @PostMapping("/login/admin")
    public ResponseEntity<LoginResponseDTO> loginAdmin(@RequestBody LoginRequestDTO loginRequest) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        String email = userDetails.getUsername();

        if(Objects.equals(loginRequest.getPassword(), userDetails.getPassword())) {

            AdminDTO adminDTO = adminService.getAdminByEmail(email);
            if (adminDTO == null) {
                ResponseEntity.status(404).body("Admin not found");
            }
            AdminEntity adminEntity = new AdminEntity().builder()
                    .email(adminDTO.getEmail())
                    .password(adminDTO.getPassword())
                    .role(adminDTO.getRole())
                    .build();
            String accessToken = jwtUtil.generateAdminAccessToken(adminEntity);
            String refreshToken = jwtUtil.generateAdminRefreshToken(adminEntity);

            LoginResponseDTO loginResponse = new LoginResponseDTO(email, accessToken,refreshToken);
            return ResponseEntity.ok(loginResponse);
        } else {
            log.warn("JobSeeker with email: " + loginRequest.getEmail() + " not found");
            return null;
        }
    }
}
