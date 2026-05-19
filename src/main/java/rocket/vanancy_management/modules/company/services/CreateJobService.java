package rocket.vanancy_management.modules.company.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocket.vanancy_management.exceptions.CompanyNotFoundException;
import rocket.vanancy_management.modules.company.entities.JobEntity;
import rocket.vanancy_management.modules.company.repositories.CompanyRepository;
import rocket.vanancy_management.modules.company.repositories.JobRepository;

@Service
public class CreateJobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity execute(JobEntity jobEntity) {

        companyRepository.findById(jobEntity.getCompanyId())
                .orElseThrow(() -> {
                    throw new CompanyNotFoundException();
                });

        return this.jobRepository.save(jobEntity);
    }
}
