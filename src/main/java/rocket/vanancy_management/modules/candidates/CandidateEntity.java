package rocket.vanancy_management.modules.candidates;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Data
public class CandidateEntity {

    private UUID id;
    private String name;

    @NotBlank()
    @Pattern(regexp = "\\S+", message = "Usernames should not contain spaces.")
    private String username;

    @Email(message = "Please enter a valid email address.")
    private String email;

    @Length(min = 6, max = 20)
    private String password;
    private String description;
    private String curriculum;
}
