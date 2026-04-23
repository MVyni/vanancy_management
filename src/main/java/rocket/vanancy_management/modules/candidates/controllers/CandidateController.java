package rocket.vanancy_management.modules.candidates.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rocket.vanancy_management.exceptions.UserAlreadyExists;
import rocket.vanancy_management.modules.candidates.CandidateEntity;
import rocket.vanancy_management.modules.candidates.CandidateRepository;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    CandidateRepository candidateRepository;

    @PostMapping("/")
    public CandidateEntity create(@Valid @RequestBody CandidateEntity candidateEntity) {

        this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent(user -> {
                    throw new UserAlreadyExists();
                });

        return this.candidateRepository.save(candidateEntity);
    }
}
