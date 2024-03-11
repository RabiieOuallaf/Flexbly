package ma.yc.flexbly.Controllers.Auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.flexbly.Jwt.JwtUtil;
import ma.yc.flexbly.Models.DTO.Auth.LoginRequest;
import ma.yc.flexbly.Models.DTO.Auth.LoginResponse;
import ma.yc.flexbly.Models.DTO.JobSeeker.JobSeekerDTO;
import ma.yc.flexbly.Models.Entities.JobSeekerEntity;
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
    private final UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/jobseeker")
    public ResponseEntity<LoginResponse> loginJobSeeker(@RequestBody LoginRequest loginRequest) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        if(Objects.equals(loginRequest.getPassword(), userDetails.getPassword())) {
            String email = userDetails.getUsername();

           JobSeekerDTO jobSeekerDTO = jobSeekerService.getJobSeekerByEmail(email);
           if(jobSeekerDTO != null) {
               JobSeekerEntity jobSeekerEntity = new JobSeekerEntity().builder()
                       .email(jobSeekerDTO.getEmail())
                       .password(jobSeekerDTO.getPassword())
                       .role(jobSeekerDTO.getRole())
                       .build();
               String accessToken = jwtUtil.generateToken(jobSeekerEntity);
               LoginResponse loginResponse = new LoginResponse(email, accessToken);
               return ResponseEntity.ok(loginResponse);
           }
        } else {
            log.warn("JobSeeker with email: " + loginRequest.getEmail() + " not found");
            return null;
        }
        return null;
    }
}
