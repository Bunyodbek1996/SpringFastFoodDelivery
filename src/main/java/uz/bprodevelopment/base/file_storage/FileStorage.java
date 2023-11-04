package uz.bprodevelopment.base.file_storage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.bprodevelopment.base.config.audit_config.AuditEntity;

import static jakarta.persistence.GenerationType.IDENTITY;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FileStorage extends AuditEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String fileName;

    private String fileExtension;

    private Long fileSize;

    private String fileHashId;

    private String fileContentType;

    private String fileUploadedPath;

    private Boolean deletable = false;

    public FileStorageDto toDto() {
        FileStorageDto dto = new FileStorageDto();
        dto.setId(id);
        dto.setFileHashId(fileHashId);
        return dto;
    }

}
