package ma.yc.flexbly.Models.Repositories;

import ma.yc.flexbly.Models.Entities.JobSeekerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeekerEntity, Integer> {
    JobSeekerEntity findByEmail(String email);
    void deleteByEmail(String email);
}
