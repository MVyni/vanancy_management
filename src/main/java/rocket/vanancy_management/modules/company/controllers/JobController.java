package rocket.vanancy_management.modules.company.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rocket.vanancy_management.modules.company.dto.CreateJobDTO;
import rocket.vanancy_management.modules.company.entities.JobEntity;
import rocket.vanancy_management.modules.company.services.JobService;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_COMPANY')")
    @Tag(name = "Company Job", description = "Endpoints for managing company job vacancies.")
    @Operation(summary = "Create Job Vacancy", description = "Allows a company to create a new job vacancy.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                @Content(schema = @Schema(implementation = JobEntity.class))
    }),
    })
    public JobEntity create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {

        var companyId = request.getAttribute("company_id");

        var jobEntity = JobEntity.builder()
                .benefits(createJobDTO.getBenefits())
                .companyId(UUID.fromString(companyId.toString()))
                .description(createJobDTO.getDescription())
                .level(createJobDTO.getLevel())
                .build();

        return this.jobService.execute(jobEntity);
    }
}
