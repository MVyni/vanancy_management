package rocket.vanancy_management.modules.candidates.controllers;

import org.springframework.web.bind.annotation.*;
import rocket.vanancy_management.modules.candidates.CandidateEntity;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @PostMapping("/")
    public void create(@RequestBody CandidateEntity candidateEntity) {
        System.out.println("Candidate");
        System.out.println(candidateEntity.getEmail());
    }
}
