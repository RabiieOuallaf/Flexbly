package ma.yc.flexbly.Models.Repositories;

import ma.yc.flexbly.Models.DTO.Industry.IndustryRequestDTO;
import ma.yc.flexbly.Models.Entities.IndustryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustryRepository extends JpaRepository<IndustryEntity, Integer> {

    IndustryEntity findByName(String name);
}
