package ma.yc.flexbly.Services;

import lombok.extern.slf4j.Slf4j;
import ma.yc.flexbly.Models.DTO.JobSeeker.JobSeekerDTO;
import ma.yc.flexbly.Models.Entities.JobSeekerEntity;
import ma.yc.flexbly.Models.Mappers.JobSeekerMapper;
import ma.yc.flexbly.Models.Repositories.JobSeekerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JobSeekerService {
    @Autowired
    JobSeekerRepository jobSeekerRepository;

    public JobSeekerDTO getJobSeekerByEmail(String email) {
        JobSeekerEntity foundJobSeekerEntity = jobSeekerRepository.findByEmail(email);
        System.out.println(foundJobSeekerEntity + "<=================================");
        if(foundJobSeekerEntity != null) {
            JobSeekerDTO jobSeekerDTO = JobSeekerMapper.jobSeekerMapper.toDTO(foundJobSeekerEntity);
            return jobSeekerDTO;
        } else {
            log.warn("JobSeeker with email: " + email + " not found");
            return null;
        }
    }
}
