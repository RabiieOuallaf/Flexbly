package ma.yc.flexbly.Services;

import lombok.extern.slf4j.Slf4j;
import ma.yc.flexbly.Models.DTO.JobSeeker.JobSeekerRequestDTO;
import ma.yc.flexbly.Models.DTO.JobSeeker.JobSeekerResponseDTO;
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

    public JobSeekerResponseDTO getJobSeekerByEmail(String email) {
        JobSeekerEntity foundJobSeekerEntity = jobSeekerRepository.findByEmail(email);
        if(foundJobSeekerEntity != null) {
            JobSeekerResponseDTO jobSeekerDTO = JobSeekerMapper.jobSeekerMapper.toDTO(foundJobSeekerEntity);
            return jobSeekerDTO;
        } else {
            log.warn("JobSeeker with email: " + email + " not found");
            return null;
        }
    }

    public JobSeekerResponseDTO createJobSeeker(JobSeekerRequestDTO jobSeekerDTO) {
        JobSeekerEntity foundJobSeekerEntity = jobSeekerRepository.findByEmail(jobSeekerDTO.getEmail());
        if(foundJobSeekerEntity != null) {
            log.warn("JobSeeker with email: " + jobSeekerDTO.getEmail() + " already exists");
            return null;
        }
        JobSeekerEntity jobSeekerEntity = JobSeekerMapper.jobSeekerMapper.toEntity(jobSeekerDTO);
        JobSeekerEntity createdJobSeekerEntity = jobSeekerRepository.save(jobSeekerEntity);
        JobSeekerResponseDTO createdJobSeekerDTO = JobSeekerMapper.jobSeekerMapper.toDTO(createdJobSeekerEntity);
        return createdJobSeekerDTO;
    }
}
