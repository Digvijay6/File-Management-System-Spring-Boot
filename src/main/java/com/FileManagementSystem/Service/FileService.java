package com.FileManagementSystem.Service;
import com.FileManagementSystem.Entity.FileEntity;
import com.FileManagementSystem.Repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.FileManagementSystem.Util.FileIUtil;

import java.io.IOException;
import java.util.Optional;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    public String uploadFile(MultipartFile file) throws IOException {
        FileEntity fileData = fileRepository.save(FileEntity.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .fileData(FileIUtil.compressImage(file.getBytes())).build());

        if(fileData !=null){
            return "File uploaded successfully: "+file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadFile(String fileName){
        Optional<FileEntity> dbFileData=fileRepository.findByName(fileName);
        byte[] files=FileIUtil.decompressImage(dbFileData.get().getFileData());
        return files;
    }
}
