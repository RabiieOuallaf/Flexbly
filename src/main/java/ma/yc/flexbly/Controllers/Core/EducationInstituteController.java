package ma.yc.flexbly.Controllers.Core;

import ma.yc.flexbly.Models.DTO.EducationInstitute.EducationInstituteRequestDTO;
import ma.yc.flexbly.Models.DTO.EducationInstitute.EducationInstituteResponseDTO;
import ma.yc.flexbly.Models.Entities.EducationInstituteEntity;
import ma.yc.flexbly.Models.Mappers.EducationInstituteMapper;
import ma.yc.flexbly.Services.EducationInstituteService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/education-institute")
@CrossOrigin("*")
public class EducationInstituteController {
    @Autowired
    EducationInstituteService educationInstituteService;

    @PostMapping("/create")
    public ResponseEntity<EducationInstituteResponseDTO> createEducationInstitute(@RequestBody EducationInstituteRequestDTO educationInstituteRequestDTO) {
        if (educationInstituteRequestDTO.getName() == null || educationInstituteRequestDTO.getAddress() == null || educationInstituteRequestDTO.getPostalCode() == null) {
            return ResponseEntity.status(400).body(null);
        }
        EducationInstituteEntity toBeCreatedEducationInstitute = Mappers.getMapper(EducationInstituteMapper.class).toEntity(educationInstituteRequestDTO);
        EducationInstituteResponseDTO createdEducationInstitute = educationInstituteService.createEducationInstitute(toBeCreatedEducationInstitute);
        return ResponseEntity.ok().body(createdEducationInstitute);
    }
    @PutMapping("/update")
    public ResponseEntity<EducationInstituteResponseDTO> updateEducationInstitute(@RequestBody EducationInstituteRequestDTO educationInstituteRequestDTO) {
        if (educationInstituteRequestDTO.getName() == null || educationInstituteRequestDTO.getAddress() == null || educationInstituteRequestDTO.getPostalCode() == null) {
            return ResponseEntity.status(400).body(null);
        }
        EducationInstituteEntity toBeUpdatedEducationInstitute = Mappers.getMapper(EducationInstituteMapper.class).toEntity(educationInstituteRequestDTO);
        EducationInstituteResponseDTO updatedEducationInstitute = educationInstituteService.updateEducationInstitute(toBeUpdatedEducationInstitute);
        return ResponseEntity.ok().body(updatedEducationInstitute);
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<EducationInstituteResponseDTO> getEducationInstituteByName(@PathVariable String name) {
        EducationInstituteResponseDTO foundEducationInstitute = educationInstituteService.getEducationInstituteByName(name);
        if (foundEducationInstitute == null) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok().body(foundEducationInstitute);
    }
    @GetMapping("/getAll")
    public ResponseEntity<Iterable<EducationInstituteResponseDTO>> getAllEducationInstitutes() {
        Iterable<EducationInstituteResponseDTO> allEducationInstitutes = educationInstituteService.getAllEducationInstitutes();
        return ResponseEntity.ok().body(allEducationInstitutes);
    }
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteEducationInstitute(@PathVariable String name) {
        educationInstituteService.deleteEducationInstituteByName(name);
        return ResponseEntity.ok().body("Education Institute with name: " + name + " deleted");
    }

}
