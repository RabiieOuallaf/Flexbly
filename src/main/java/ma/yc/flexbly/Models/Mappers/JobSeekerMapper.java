package ma.yc.flexbly.Models.Mappers;

import ma.yc.flexbly.Models.DTO.JobSeeker.JobSeekerRequestDTO;
import ma.yc.flexbly.Models.DTO.JobSeeker.JobSeekerResponseDTO;
import ma.yc.flexbly.Models.Entities.JobSeekerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface JobSeekerMapper {
    JobSeekerMapper jobSeekerMapper = Mappers.getMapper(JobSeekerMapper.class);

    JobSeekerResponseDTO toDTO(JobSeekerEntity jobSeekerEntity);
    JobSeekerEntity toEntity(JobSeekerRequestDTO jobSeekerDTO);
}
