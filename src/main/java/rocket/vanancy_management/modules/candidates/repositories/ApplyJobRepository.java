package rocket.vanancy_management.modules.candidates.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rocket.vanancy_management.modules.candidates.entities.ApplyJobEntity;

import java.util.UUID;

public interface ApplyJobRepository  extends JpaRepository<ApplyJobEntity, UUID> {
}
