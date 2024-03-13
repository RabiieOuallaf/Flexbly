package ma.yc.flexbly.Models.Repositories;

import ma.yc.flexbly.Models.Entities.EducationInstituteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationInstituteRepository extends JpaRepository<EducationInstituteEntity, Integer> {
    EducationInstituteEntity findByName(String name);
}
