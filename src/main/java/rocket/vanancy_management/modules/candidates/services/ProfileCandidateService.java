package rocket.vanancy_management.modules.candidates.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rocket.vanancy_management.modules.candidates.CandidateEntity;
import rocket.vanancy_management.modules.candidates.CandidateRepository;
import rocket.vanancy_management.modules.candidates.dto.ProfileCandidateResponseDTO;

import java.util.UUID;

@Service
public class ProfileCandidateService {

    @Autowired
    CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID candidateId) {

        var candidate = this.candidateRepository.findById(candidateId).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found.");
        });

        var candidateDTO = ProfileCandidateResponseDTO.builder()
                .name(candidate.getName())
                .email(candidate.getEmail())
                .username(candidate.getUsername())
                .description(candidate.getDescription())
                .build();

        return candidateDTO;
    }
}
