package uz.bprodevelopment.base.file_storage;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    FileStorage save(MultipartFile file);

    FileStorage get(String fileHashId);

}
