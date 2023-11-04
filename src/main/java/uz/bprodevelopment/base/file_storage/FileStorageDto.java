package uz.bprodevelopment.base.file_storage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileStorageDto{

    private Long id;
    private String fileHashId;

    public FileStorage toEntity(){
        FileStorage fileStorage = new FileStorage();
        fileStorage.setId(id);
        fileStorage.setFileHashId(fileHashId);
        return fileStorage;
    }

}
