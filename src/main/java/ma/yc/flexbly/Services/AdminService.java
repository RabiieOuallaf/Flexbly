package ma.yc.flexbly.Services;

import lombok.extern.slf4j.Slf4j;
import ma.yc.flexbly.Models.DTO.Admin.AdminDTO;
import ma.yc.flexbly.Models.Entities.AdminEntity;
import ma.yc.flexbly.Models.Mappers.AdminMapper;
import ma.yc.flexbly.Models.Repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public AdminDTO getAdminByEmail(String email) {
        AdminEntity foundAdminEntity = adminRepository.findByEmail(email);
        if(foundAdminEntity != null) {
            AdminDTO adminDTO = AdminMapper.adminMapper.toDTO(foundAdminEntity);
            return adminDTO;
        } else {
            log.warn("Admin with email: " + email + " not found");
            return null;
        }
    }
}
