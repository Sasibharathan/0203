package com.MosIC.MosIC_Office.file_package.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.MosIC.MosIC_Office.file_package.dto.FileDTO;
import com.MosIC.MosIC_Office.file_package.entity.FileEntity;
import com.MosIC.MosIC_Office.file_package.exception.ResourceNotFound;
import com.MosIC.MosIC_Office.file_package.mapper.FileMapper;
import com.MosIC.MosIC_Office.file_package.repository.FileRepository;

@Service
public class FileServiceImpl implements FileServices {

  private FileRepository fileRepository;

   // ✅ CONSTRUCTOR INJECTION (IMPORTANT)
    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

  @Override
  public FileDTO createFile(FileDTO fileDTO) {
     FileEntity entity = new FileEntity();
        entity.setFileDate(fileDTO.getFileDate());
        entity.setFileActivity(fileDTO.getFileActivity());
        entity.setFileSubject(fileDTO.getFileSubject());
        entity.setFileDescription(fileDTO.getFileDescription());
        entity.setFileStatus(fileDTO.getFileStatus());

        FileEntity saved = fileRepository.save(entity);

        fileDTO.setId(saved.getId());
        return fileDTO;
  }

  @Override
  public FileDTO getFileById(Long id) {
    FileEntity fileEntity = fileRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFound("File not found with id: " + id));
            System.out.println("FileEntity found: " + fileEntity);
    return FileMapper.mapToDTO(fileEntity);
  }

  @Override
  public List<FileDTO> getAllFiles() {
    List<FileEntity> fileEntities = fileRepository.findAll();
    return fileEntities.stream()
            .map(FileMapper::mapToDTO)
            .collect(Collectors.toList());
  }

  @Override
  public FileDTO updateFile(Long id, FileDTO fileDTO) {
    FileEntity fileEntity = fileRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFound("File not exit with id: " + id));

            fileEntity.setFileDate(fileDTO.getFileDate());
            fileEntity.setFileActivity(fileDTO.getFileActivity());
            fileEntity.setFileSubject(fileDTO.getFileSubject());
            fileEntity.setFileDescription(fileDTO.getFileDescription());
            fileEntity.setFileStatus(fileDTO.getFileStatus());
            FileEntity updatedFile = fileRepository.save(fileEntity);
            return FileMapper.mapToDTO(updatedFile);


  }

  @Override
  public void deleteFile(Long id) {
     FileEntity fileEntity = fileRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFound("File not found with id: " + id));
      fileRepository.deleteById(id);

  }

}
