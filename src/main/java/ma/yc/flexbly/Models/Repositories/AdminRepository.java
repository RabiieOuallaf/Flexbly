package ma.yc.flexbly.Models.Repositories;

import ma.yc.flexbly.Models.Entities.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {
    AdminEntity findByEmail(String email);
    void deleteByEmail(String email);

}
