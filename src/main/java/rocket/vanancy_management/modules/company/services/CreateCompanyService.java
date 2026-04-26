package rocket.vanancy_management.modules.company.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocket.vanancy_management.exceptions.UserAlreadyExists;
import rocket.vanancy_management.modules.company.entities.CompanyEntity;
import rocket.vanancy_management.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity companyEntity) {

        this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent(user -> {
                    throw new UserAlreadyExists();
                });

        return this.companyRepository.save(companyEntity);
    }
}
