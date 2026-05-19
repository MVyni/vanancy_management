package rocket.vanancy_management.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobDTO {

    @Schema(description = "Description of the job vacancy", example = "We are looking for a skilled Java developer to join our team.")
    private String description;

    @Schema(description = "Benefits offered for the job vacancy", example = "Health insurance, remote work, flexible hours")
    private String benefits;

    @Schema(description = "Level of the job vacancy", example = "Junior, Mid, Senior")
    private String level;
}
