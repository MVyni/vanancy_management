package rocket.vanancy_management.modules.candidates.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rocket.vanancy_management.modules.candidates.entities.CandidateEntity;

import java.util.Optional;
import java.util.UUID;

    public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
        Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);
        Optional<CandidateEntity> findByUsername(String username);
    }
