package ma.yc.flexbly.Models.Mappers;

import ma.yc.flexbly.Models.DTO.Industry.IndustryRequestDTO;
import ma.yc.flexbly.Models.DTO.Industry.IndustryResponseDTO;
import ma.yc.flexbly.Models.Entities.IndustryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IndustryMapper {
    IndustryMapper industryMapper = Mappers.getMapper(IndustryMapper.class);

    IndustryResponseDTO toResponseDTO(IndustryEntity industryEntity);
    IndustryRequestDTO toRequestDTO(IndustryEntity industryEntity);

    IndustryEntity toEntity(IndustryResponseDTO industryResponsesDTO);
    IndustryEntity toEntity(IndustryRequestDTO industryRequestDTO);


}
