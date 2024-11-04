package com.FileManagementSystem.Repository;

import com.FileManagementSystem.Entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<FileEntity,Long> {

    Optional<FileEntity> findByName(String fileName);
}
