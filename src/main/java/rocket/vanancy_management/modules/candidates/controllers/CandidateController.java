package rocket.vanancy_management.modules.candidates.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rocket.vanancy_management.modules.candidates.CandidateEntity;
import rocket.vanancy_management.modules.candidates.services.CreateCandidateService;
import rocket.vanancy_management.modules.candidates.services.ListAllJobsByFilterService;
import rocket.vanancy_management.modules.candidates.services.ProfileCandidateService;
import rocket.vanancy_management.modules.company.entities.JobEntity;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateService createCandidateService;

    @Autowired
    private ProfileCandidateService profileCandidateService;

    @Autowired
    private ListAllJobsByFilterService listAllJobsByFilterService;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {

        try {

        var result = this.createCandidateService.execute(candidateEntity);
        return ResponseEntity.ok().body(result);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> get(HttpServletRequest request) {

        var candidateId = request.getAttribute("candidate_id");

        try {

            var profile = this.profileCandidateService.execute(UUID.fromString(candidateId.toString()));
            return ResponseEntity.ok().body(profile);

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    //SWAGGER CONFIGS
    @Tag(name = "Candidate", description = "Candidate infos")
    @Operation(summary = "List all jobs by filter", description = "List all jobs that contains the filter in the description")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = JobEntity.class)))
            }),
    })
    //TO USER AUTH
    @SecurityRequirement(name = "jwt_auth")
    public List<JobEntity> getAllJobsByFilter(@RequestParam String filter) {
        return this.listAllJobsByFilterService.execute(filter);
    }
}
