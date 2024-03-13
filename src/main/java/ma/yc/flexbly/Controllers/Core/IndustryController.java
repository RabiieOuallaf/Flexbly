package ma.yc.flexbly.Controllers.Core;

import ma.yc.flexbly.Models.DTO.Industry.IndustryRequestDTO;
import ma.yc.flexbly.Models.DTO.Industry.IndustryResponseDTO;
import ma.yc.flexbly.Models.Entities.IndustryEntity;
import ma.yc.flexbly.Models.Mappers.IndustryMapper;
import ma.yc.flexbly.Services.IndustryService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/industry")
public class IndustryController {
    @Autowired
    IndustryService industryService;
    @PostMapping("/create")
    public ResponseEntity<IndustryResponseDTO> createIndustry(@RequestBody IndustryRequestDTO industryRequestDTO) {
        if(industryRequestDTO.getName() == null) {
            return ResponseEntity.status(400).body(null);
        }
        IndustryEntity toBeCreatedIndustry = Mappers.getMapper(IndustryMapper.class).toEntity(industryRequestDTO);
        IndustryResponseDTO createdIndustry = industryService.createIndustry(toBeCreatedIndustry);
        return ResponseEntity.ok().body(createdIndustry);
    }
    @GetMapping("/get/{name}")
    public ResponseEntity<IndustryResponseDTO> getIndustryByName(@PathVariable String name) {
        IndustryResponseDTO foundIndustry = industryService.getIndustryByName(name);
        if(foundIndustry == null) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok().body(foundIndustry);
    }
    @PutMapping("/update")
    public ResponseEntity<IndustryResponseDTO> updateIndustry(@RequestBody IndustryRequestDTO industryRequestDTO) {
        if(industryRequestDTO.getName() == null) {
            return ResponseEntity.status(400).body(null);
        }
        IndustryEntity toBeUpdatedIndustry = Mappers.getMapper(IndustryMapper.class).toEntity(industryRequestDTO);
        IndustryResponseDTO updatedIndustry = industryService.updateIndustry(toBeUpdatedIndustry);
        return ResponseEntity.ok().body(updatedIndustry);
    }
    @GetMapping("/getAll")
    public ResponseEntity<Iterable<IndustryResponseDTO>> getAllIndustries() {
        Iterable<IndustryResponseDTO> allIndustries = industryService.getAllIndustries();
        return ResponseEntity.ok().body(allIndustries);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteIndustry(@PathVariable String name) {
        industryService.deleteIndustry(name);
        return ResponseEntity.ok().body("Industry with name: " + name + " deleted");
    }
}
