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
        if(foundJobSeekerEntity != null) {
            JobSeekerDTO jobSeekerDTO = JobSeekerMapper.jobSeekerMapper.toDTO(foundJobSeekerEntity);
            return jobSeekerDTO;
        } else {
            log.warn("JobSeeker with email: " + email + " not found");
            return null;
        }
    }

    public JobSeekerDTO createJobSeeker(JobSeekerDTO jobSeekerDTO) {
        JobSeekerEntity foundJobSeekerEntity = jobSeekerRepository.findByEmail(jobSeekerDTO.getEmail());
        if(foundJobSeekerEntity != null) {
            log.warn("JobSeeker with email: " + jobSeekerDTO.getEmail() + " already exists");
            return null;
        }
        JobSeekerEntity jobSeekerEntity = JobSeekerMapper.jobSeekerMapper.toEntity(jobSeekerDTO);
        JobSeekerEntity createdJobSeekerEntity = jobSeekerRepository.save(jobSeekerEntity);
        JobSeekerDTO createdJobSeekerDTO = JobSeekerMapper.jobSeekerMapper.toDTO(createdJobSeekerEntity);
        return createdJobSeekerDTO;
    }
}
