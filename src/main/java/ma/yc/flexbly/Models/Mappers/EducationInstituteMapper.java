package ma.yc.flexbly.Models.Mappers;

import ma.yc.flexbly.Models.DTO.EducationInstitute.EducationInstituteRequestDTO;
import ma.yc.flexbly.Models.DTO.EducationInstitute.EducationInstituteResponseDTO;
import ma.yc.flexbly.Models.Entities.EducationInstituteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EducationInstituteMapper {
    EducationInstituteMapper educationInstituteMapper = Mappers.getMapper(EducationInstituteMapper.class);

    EducationInstituteResponseDTO toDTO(EducationInstituteEntity educationInstituteEntity);

    EducationInstituteEntity toEntity(EducationInstituteResponseDTO educationInstituteResponseDTO);
    EducationInstituteEntity toEntity(EducationInstituteRequestDTO educationInstituteRequestDTO);
}