package rocket.vanancy_management;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Vacancy Management API",
                version = "1.0",
                description = "API for managing job vacancies, companies, and candidates."
        )
)
public class VanancyManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(VanancyManagementApplication.class, args);
    }

}
