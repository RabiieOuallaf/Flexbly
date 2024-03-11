package ma.yc.flexbly.Services;


import lombok.RequiredArgsConstructor;
import ma.yc.flexbly.Models.DTO.JobSeeker.JobSeekerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private final JobSeekerService jobSeekerService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        JobSeekerDTO jobSeekerDTO = jobSeekerService.getJobSeekerByEmail(email);

        if(jobSeekerDTO != null) {
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(jobSeekerDTO.getEmail())
                    .password(jobSeekerDTO.getPassword())
                    .roles("JobSeeker")
                    .authorities(
                            "Apply"

                    )
                    .build();
            return userDetails;
        } else {
            return null;
        }

    }
}
