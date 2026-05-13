package rocket.vanancy_management.modules.company.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rocket.vanancy_management.modules.company.entities.JobEntity;

import java.util.List;
import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
    // CONTAINS - LIKE
    // SELECT * from job where description like %filter%
    List<JobEntity> findByDescriptionContainingIgnoreCase(String filter);
}
