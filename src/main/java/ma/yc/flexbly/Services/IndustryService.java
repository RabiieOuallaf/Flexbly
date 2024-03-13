package ma.yc.flexbly.Services;

import lombok.extern.slf4j.Slf4j;
import ma.yc.flexbly.Models.DTO.Industry.IndustryResponseDTO;
import ma.yc.flexbly.Models.Entities.IndustryEntity;
import ma.yc.flexbly.Models.Mappers.IndustryMapper;
import ma.yc.flexbly.Models.Repositories.IndustryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class IndustryService {
    @Autowired
    IndustryRepository industryRepository;


    public IndustryResponseDTO createIndustry(IndustryEntity industryEntity) {
        if(industryEntity.getName() != null) {
            IndustryEntity foundIndustry = industryRepository.findByName(industryEntity.getName());
            if(foundIndustry != null) {
                log.warn("Industry with name: " + industryEntity.getName() + " already exists");
                return null;
            }
            IndustryEntity foundIndustryByName = industryRepository.findByName(industryEntity.getName());
            if(foundIndustryByName != null) {
                log.warn("Industry with name: " + industryEntity.getName() + " already exists");
                return null;
            }
            IndustryEntity createdIndustry = industryRepository.save(industryEntity);
            IndustryResponseDTO createdIndustryDTO = IndustryMapper.industryMapper.toResponseDTO(createdIndustry);
            return createdIndustryDTO;
        } else {
            log.warn("Industry name is null");
            return null;
        }
    }

    public IndustryResponseDTO getIndustryByName(String name) {
        IndustryEntity foundIndustry = industryRepository.findByName(name);
        if(foundIndustry != null) {
            IndustryResponseDTO foundIndustryDTO = IndustryMapper.industryMapper.toResponseDTO(foundIndustry);
            return foundIndustryDTO;
        } else {
            log.warn("Industry with name: " + name + " not found");
            return null;
        }
    }

    public IndustryResponseDTO updateIndustry(IndustryEntity industryEntity) {
        IndustryEntity foundIndustry = industryRepository.findByName(industryEntity.getName());
        if (foundIndustry != null) {

            IndustryEntity updatedIndustry = industryRepository.save(industryEntity);
            IndustryResponseDTO updatedIndustryDTO = IndustryMapper.industryMapper.toResponseDTO(updatedIndustry);
            return updatedIndustryDTO;
        } else {
            log.warn("Industry with name: " + industryEntity.getName() + " not found");
            return null;
        }
    }

    public List<IndustryResponseDTO> getAllIndustries() {
        List<IndustryEntity> foundIndustries = industryRepository.findAll();
        if(!foundIndustries.isEmpty()) {
            List<IndustryResponseDTO> foundIndustriesDTO = new ArrayList<>();
            for (IndustryEntity industryEntity : foundIndustries) {
                IndustryResponseDTO industryResponseDTO = IndustryMapper.industryMapper.toResponseDTO(industryEntity);
                foundIndustriesDTO.add(industryResponseDTO);
            }
            return foundIndustriesDTO;
        } else {
            log.warn("No industries found");
            return Collections.emptyList();
        }
    }
    public void deleteIndustry(String name) {
        IndustryEntity foundIndustry = industryRepository.findByName(name);
        if (foundIndustry != null) {
            industryRepository.delete(foundIndustry);
        } else {
            log.warn("Industry with name: " + name + " not found");
        }
    }
}


