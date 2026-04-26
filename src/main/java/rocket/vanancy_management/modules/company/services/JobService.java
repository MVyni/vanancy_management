package rocket.vanancy_management.modules.company.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocket.vanancy_management.modules.company.entities.JobEntity;
import rocket.vanancy_management.modules.company.repositories.JobRepository;

@Service
public class JobService {

    @Autowired
    JobRepository jobRepository;

    public JobEntity execute(JobEntity jobEntity) {

        return this.jobRepository.save(jobEntity);
    }
}
