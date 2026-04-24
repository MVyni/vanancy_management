package rocket.vanancy_management.modules.candidates.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rocket.vanancy_management.modules.candidates.CandidateEntity;
import rocket.vanancy_management.modules.candidates.services.CreateCandidateService;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateService createCandidateService;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {

        try {

        var result = this.createCandidateService.execute(candidateEntity);
        return ResponseEntity.ok().body(result);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
