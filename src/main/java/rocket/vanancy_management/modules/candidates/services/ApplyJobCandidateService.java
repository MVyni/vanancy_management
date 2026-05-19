package rocket.vanancy_management.modules.candidates.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocket.vanancy_management.exceptions.JobNotFoundException;
import rocket.vanancy_management.exceptions.UserNotFoundException;
import rocket.vanancy_management.modules.candidates.entities.ApplyJobEntity;
import rocket.vanancy_management.modules.candidates.repositories.ApplyJobRepository;
import rocket.vanancy_management.modules.candidates.repositories.CandidateRepository;
import rocket.vanancy_management.modules.company.repositories.JobRepository;

import java.util.UUID;

@Service
public class ApplyJobCandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID candidateId, UUID jobId) {
        //To valid if candidate exist
        this.candidateRepository.findById(candidateId).orElseThrow(() -> new UserNotFoundException());

        //To valid if job exist
        this.jobRepository.findById(jobId).orElseThrow(() -> new JobNotFoundException());

        //Candidate can enroll in a job
        var applyJob = ApplyJobEntity.builder()
                .candidateId(candidateId)
                .jobId(jobId)
                .build();

        return applyJobRepository.save(applyJob);
    }
}
