package rocket.vanancy_management.modules.candidates.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocket.vanancy_management.modules.company.entities.JobEntity;
import rocket.vanancy_management.modules.company.repositories.JobRepository;

import java.util.List;

@Service
public class ListAllJobsByFilterService {

    @Autowired
    private JobRepository jobRepository;

    public List<JobEntity> execute(String filter) {

        return this.jobRepository.findByDescriptionContainingIgnoreCase(filter);
    }
}
