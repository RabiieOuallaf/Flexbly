package ma.yc.flexbly.Models.Mappers;

import ma.yc.flexbly.Models.DTO.Admin.AdminDTO;
import ma.yc.flexbly.Models.Entities.AdminEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")

public interface AdminMapper {
    AdminMapper adminMapper = Mappers.getMapper(AdminMapper.class);
    AdminDTO toDTO(AdminEntity adminEntity);
    AdminEntity toEntity(AdminDTO adminDTO);
}
