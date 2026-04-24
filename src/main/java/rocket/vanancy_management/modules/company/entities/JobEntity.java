package rocket.vanancy_management.modules.company.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "job")
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String description;
    private String level;
    private String benefits;

    @ManyToOne()
    @JoinColumn(name = "company_id")
    private CompanyEntity companyEntity;

    @Column(name = "company_id", insertable = false, updatable = false)
    private UUID companyId;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
