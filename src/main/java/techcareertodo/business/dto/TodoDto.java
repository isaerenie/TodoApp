package techcareertodo.business.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import techcareertodo.audit.AuditingAwareBaseDto;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class TodoDto extends AuditingAwareBaseDto implements Serializable {
    public static final Long serialVersionUID = 1L;
    @Column(name = "subject",columnDefinition = "varchar(255) default 'Konu girmediniz'")
    private String subject;

    @Column(name = "isDone")
    private boolean isDone;
}

