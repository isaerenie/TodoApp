package techcareertodo.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import techcareertodo.audit.AuditingAwareBaseEntity;

import java.util.Date;
// LOMBOK
@Getter
@Setter
@NoArgsConstructor

// JSON
@JsonIgnoreProperties(value = {"created_date,updated_date"},allowGetters = true) // Json'a emir veriyoruz bunları takip etme
@EntityListeners(AuditingEntityListener.class) // AUDITING
@MappedSuperclass
public class BaseEntity extends AuditingAwareBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date systemDate;
}
