package rocket.vanancy_management.modules.candidate.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rocket.vanancy_management.exceptions.JobNotFoundException;
import rocket.vanancy_management.exceptions.UserNotFoundException;
import rocket.vanancy_management.modules.candidates.entities.ApplyJobEntity;
import rocket.vanancy_management.modules.candidates.entities.CandidateEntity;
import rocket.vanancy_management.modules.candidates.repositories.ApplyJobRepository;
import rocket.vanancy_management.modules.candidates.repositories.CandidateRepository;
import rocket.vanancy_management.modules.candidates.services.ApplyJobCandidateService;
import rocket.vanancy_management.modules.company.entities.JobEntity;
import rocket.vanancy_management.modules.company.repositories.JobRepository;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateServiceTest {

    @InjectMocks
    private ApplyJobCandidateService applyJobCandidateService;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply for a job with a candidate not found")
    public void should_not_be_able_to_apply_job_with_candidate_not_found() {

        try {
            applyJobCandidateService.execute(null, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should not be able to apply for a job with a job not found")
    public void should_not_be_able_to_apply_job_with_job_not_found() {

        var idCandidate = UUID.randomUUID();
        var candidate = new CandidateEntity();
        candidate.setId(idCandidate);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        try {
            applyJobCandidateService.execute(idCandidate, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }

    @Test
    public void should_be_able_to_create_a_new_apply_job() {
        var idCandidate = UUID.randomUUID();
        var idJob = UUID.randomUUID();

        var applyJob = ApplyJobEntity.builder()
                .jobId(idJob)
                .candidateId(idCandidate)
                .build();

        var applyJobCreated = ApplyJobEntity.builder().id(UUID.randomUUID()).build();

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new CandidateEntity())); //WE NEED TO FORCE PUT A CANDIDATE ENTITY
        when(jobRepository.findById(idJob)).thenReturn(Optional.of(new JobEntity())); //WE NEED TO FORCE PUT A JOB ENTITY

        when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);

        var result = applyJobCandidateService.execute(idCandidate, idJob);

        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());
    }
}
