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
import rocket.vanancy_management.modules.candidates.entities.ApplyJobEntity;
import rocket.vanancy_management.modules.candidates.entities.CandidateEntity;
import rocket.vanancy_management.modules.candidates.dto.ProfileCandidateResponseDTO;
import rocket.vanancy_management.modules.candidates.services.ApplyJobCandidateService;
import rocket.vanancy_management.modules.candidates.services.CreateCandidateService;
import rocket.vanancy_management.modules.candidates.services.ListAllJobsByFilterService;
import rocket.vanancy_management.modules.candidates.services.ProfileCandidateService;
import rocket.vanancy_management.modules.company.entities.JobEntity;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidate", description = "Candidate infos")
public class CandidateController {

    @Autowired
    private CreateCandidateService createCandidateService;

    @Autowired
    private ProfileCandidateService profileCandidateService;

    @Autowired
    private ListAllJobsByFilterService listAllJobsByFilterService;

    @Autowired
    private ApplyJobCandidateService applyJobCandidateService;

    @PostMapping("/")
    @Operation(summary = "Create a new candidate", description = "Create a new candidate with the provided information")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = CandidateEntity.class))
            }),

            @ApiResponse(responseCode = "400", description = "User already exists."),
    })
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
    @Operation(summary = "Get candidate profile", description = "Get candidate profile by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProfileCandidateResponseDTO.class))
            }),

            @ApiResponse(responseCode = "400", description = "User not found."),
    })
    //TO USER AUTH
    @SecurityRequirement(name = "jwt_auth")
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

    @PostMapping("/job/apply")
    @PreAuthorize("hasRole('CANDIDATE')")
    @SecurityRequirement(name = "jwt_auth")
    @Operation(summary = "Apply for a job", description = "Apply for a job with the provided job id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ApplyJobEntity.class))
            })
    })
    public ResponseEntity<Object> applyJob(HttpServletRequest request, @RequestBody UUID idJob) {

        var idCandidate =  request.getAttribute("candidate_id");

        try {
            var result = this.applyJobCandidateService.execute(UUID.fromString(idCandidate.toString()), idJob);
            return ResponseEntity.ok().body(result);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
