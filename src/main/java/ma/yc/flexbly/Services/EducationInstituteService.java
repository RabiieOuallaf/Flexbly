package ma.yc.flexbly.Services;

import lombok.extern.slf4j.Slf4j;
import ma.yc.flexbly.Models.DTO.EducationInstitute.EducationInstituteResponseDTO;
import ma.yc.flexbly.Models.Entities.EducationInstituteEntity;
import ma.yc.flexbly.Models.Mappers.EducationInstituteMapper;
import ma.yc.flexbly.Models.Repositories.EducationInstituteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EducationInstituteService {
    @Autowired
    EducationInstituteRepository educationInstituteRepository;

    public EducationInstituteResponseDTO createEducationInstitute(EducationInstituteEntity educationInstituteEntity) {
        if (educationInstituteEntity.getName() == null || educationInstituteEntity.getAddress() == null || educationInstituteEntity.getPostalCode() == null) {
            log.warn("Education Institute name, address or postal code is null");
            return null;
        }

        EducationInstituteEntity foundEducationInstitute = educationInstituteRepository.findByName(educationInstituteEntity.getName());
        if (foundEducationInstitute != null) {
            log.warn("Education Institute with name: " + educationInstituteEntity.getName() + " already exists");
            return null;
        }
        EducationInstituteEntity createdEducationInstitute = educationInstituteRepository.save(educationInstituteEntity);
        EducationInstituteResponseDTO createdEducationInstituteDTO = EducationInstituteMapper.educationInstituteMapper.toDTO(createdEducationInstitute);
        return createdEducationInstituteDTO;
    }

    public EducationInstituteResponseDTO getEducationInstituteByName(String name) {
        EducationInstituteEntity foundEducationInstitute = educationInstituteRepository.findByName(name);
        if (foundEducationInstitute != null) {
            EducationInstituteResponseDTO foundEducationInstituteDTO = EducationInstituteMapper.educationInstituteMapper.toDTO(foundEducationInstitute);
            return foundEducationInstituteDTO;
        } else {
            log.warn("Education Institute with name: " + name + " not found");
            return null;
        }
    }

    public EducationInstituteResponseDTO updateEducationInstitute(EducationInstituteEntity educationInstituteEntity) {
        EducationInstituteEntity foundEducationInstitute = educationInstituteRepository.findByName(educationInstituteEntity.getName());
        if (foundEducationInstitute != null) {
            EducationInstituteEntity updatedEducationInstitute = educationInstituteRepository.save(educationInstituteEntity);
            EducationInstituteResponseDTO updatedEducationInstituteDTO = EducationInstituteMapper.educationInstituteMapper.toDTO(updatedEducationInstitute);
            return updatedEducationInstituteDTO;
        } else {
            log.warn("Education Institute with name: " + educationInstituteEntity.getName() + " not found");
            return null;
        }
    }

    public List<EducationInstituteResponseDTO> getAllEducationInstitutes() {
        List<EducationInstituteEntity> foundEducationInstituteEntities = educationInstituteRepository.findAll();
        if (!foundEducationInstituteEntities.isEmpty()) {
            List<EducationInstituteResponseDTO> foundEducationInstituteDTOs = new ArrayList<>();
            for (EducationInstituteEntity educationInstituteEntity : foundEducationInstituteEntities) {
                EducationInstituteResponseDTO educationInstituteDTO = EducationInstituteMapper.educationInstituteMapper.toDTO(educationInstituteEntity);
                foundEducationInstituteDTOs.add(educationInstituteDTO);
            }
            return foundEducationInstituteDTOs;
        } else {
            log.warn("No Education Institutes found");
            return null;
        }
    }
    public void deleteEducationInstituteByName(String name) {
        EducationInstituteEntity foundEducationInstitute = educationInstituteRepository.findByName(name);
        if (foundEducationInstitute != null) {
            educationInstituteRepository.delete(foundEducationInstitute);
        } else {
            log.warn("Education Institute with name: " + name + " not found");
        }
    }
}
