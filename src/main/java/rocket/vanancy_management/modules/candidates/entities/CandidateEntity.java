package rocket.vanancy_management.modules.candidates.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(description = "The candidate's full name", example = "John Doe", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotBlank()
    @Pattern(regexp = "\\S+", message = "Usernames should not contain spaces.") //without spaces
    @Schema(description = "The candidate's username, which should be unique and contain no spaces", example = "john_doe123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @Email(message = "Please enter a valid email address.")
    @Schema(description = "The candidate's email address, which should be valid", example = "johndoe@email.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Length(min = 6)
    @Schema(example = "password123", description = "The candidate's password, which should be at least 6 characters long", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @Schema(description = "A brief description of the candidate's", example = "Experienced software developer with a strong background in Java and Spring Boot.", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createAt;
}
