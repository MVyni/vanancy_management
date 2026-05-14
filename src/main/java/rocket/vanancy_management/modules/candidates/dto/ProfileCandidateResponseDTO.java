package rocket.vanancy_management.modules.candidates.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileCandidateResponseDTO {

    @Schema(description = "Candidate name", example = "John Doe")
    private String name;

    @Schema(description = "Candidate username", example = "johndoe")
    private String username;

    @Schema(description = "Candidate email", example = "johndoe@email.com")
    private String email;

    @Schema(description = "Candidate description", example = "Experienced software developer with a passion for building scalable applications.")
    private String description;

    private UUID id;
}
