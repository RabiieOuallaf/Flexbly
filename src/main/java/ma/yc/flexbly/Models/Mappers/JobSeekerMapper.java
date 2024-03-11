package ma.yc.flexbly.Models.Mappers;

import ma.yc.flexbly.Models.DTO.JobSeeker.JobSeekerDTO;
import ma.yc.flexbly.Models.Entities.JobSeekerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface JobSeekerMapper {
    JobSeekerMapper jobSeekerMapper = Mappers.getMapper(JobSeekerMapper.class);

    JobSeekerDTO toDTO(JobSeekerEntity jobSeekerEntity);
    JobSeekerEntity toEntity(JobSeekerDTO jobSeekerDTO);
}
