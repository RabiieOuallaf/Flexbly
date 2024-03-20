package ma.yc.flexbly.Services;


import lombok.RequiredArgsConstructor;
import ma.yc.flexbly.Models.DTO.Admin.AdminDTO;
import ma.yc.flexbly.Models.DTO.JobSeeker.JobSeekerResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private final JobSeekerService jobSeekerService;
    private final AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        JobSeekerResponseDTO jobSeekerDTO = jobSeekerService.getJobSeekerByEmail(email);
        AdminDTO adminDTO = adminService.getAdminByEmail(email);
        if(jobSeekerDTO != null) {
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(jobSeekerDTO.getEmail())
                    .password(jobSeekerDTO.getPassword())
                    .roles(jobSeekerDTO.getRole())
                    .authorities(
                            "apply"
                    )
                    .build();
            return userDetails;
        } else if(adminDTO != null) {
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(adminDTO.getEmail())
                    .password(adminDTO.getPassword())
                    .roles(adminDTO.getRole())
                    .authorities(
                            "manage"
                    )
                    .build();
            return userDetails;
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

    }
}
