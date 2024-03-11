package ma.yc.flexbly.Controllers.Auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.flexbly.Jwt.JwtUtil;
import ma.yc.flexbly.Models.DTO.Admin.AdminDTO;
import ma.yc.flexbly.Models.DTO.Auth.Login.LoginRequestDTO;
import ma.yc.flexbly.Models.DTO.Auth.Login.LoginResponseDTO;
import ma.yc.flexbly.Models.DTO.JobSeeker.JobSeekerDTO;
import ma.yc.flexbly.Models.Entities.AdminEntity;
import ma.yc.flexbly.Models.Entities.JobSeekerEntity;
import ma.yc.flexbly.Services.AdminService;
import ma.yc.flexbly.Services.JobSeekerService;
import ma.yc.flexbly.Services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
@RequiredArgsConstructor
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

            JobSeekerDTO jobSeekerDTO = jobSeekerService.getJobSeekerByEmail(email);
            if (jobSeekerDTO == null) {
                ResponseEntity.status(404).body("JobSeeker not found");
            }
            JobSeekerEntity jobSeekerEntity = new JobSeekerEntity().builder()
                    .email(jobSeekerDTO.getEmail())
                    .password(jobSeekerDTO.getPassword())
                    .role(jobSeekerDTO.getRole())
                    .build();
            String accessToken = jwtUtil.generateJobSeekerToken(jobSeekerEntity);
            LoginResponseDTO loginResponse = new LoginResponseDTO(email, accessToken);
            return ResponseEntity.ok(loginResponse);
        } else {
            log.warn("JobSeeker with email: " + loginRequest.getEmail() + " not found");
            return null;
        }

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
            String accessToken = jwtUtil.generateAdminToken(adminEntity);
            LoginResponseDTO loginResponse = new LoginResponseDTO(email, accessToken);
            return ResponseEntity.ok(loginResponse);
        } else {
            log.warn("JobSeeker with email: " + loginRequest.getEmail() + " not found");
            return null;
        }
    }
}
