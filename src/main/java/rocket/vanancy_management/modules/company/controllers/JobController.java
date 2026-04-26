package rocket.vanancy_management.modules.company.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rocket.vanancy_management.modules.company.entities.JobEntity;
import rocket.vanancy_management.modules.company.services.JobService;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/")
    public JobEntity create(@Valid @RequestBody JobEntity jobEntity) {

        return this.jobService.execute(jobEntity);
    }
}
