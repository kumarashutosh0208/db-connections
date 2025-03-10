package cyc.dataModel;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class AuditEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    @Version
    private Integer version;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String lastModifiedBy;
}
