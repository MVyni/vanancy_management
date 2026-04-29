package rocket.vanancy_management.modules.candidates.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rocket.vanancy_management.exceptions.UserAlreadyExists;
import rocket.vanancy_management.modules.candidates.CandidateEntity;
import rocket.vanancy_management.modules.candidates.CandidateRepository;



@Service
public class CreateCandidateService {

@Autowired
private CandidateRepository candidateRepository;

@Autowired
private PasswordEncoder passwordEncoder;

    public CandidateEntity execute (CandidateEntity candidateEntity) {

        this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent(user -> {
                    throw new UserAlreadyExists();
                });

        var password = passwordEncoder.encode(candidateEntity.getPassword());
        candidateEntity.setPassword(password);

        return this.candidateRepository.save(candidateEntity);
    }
}
