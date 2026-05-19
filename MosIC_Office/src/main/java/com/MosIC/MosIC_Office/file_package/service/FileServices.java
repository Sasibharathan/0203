package com.MosIC.MosIC_Office.file_package.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.MosIC.MosIC_Office.file_package.dto.FileDTO;

@Service
public interface FileServices {
  FileDTO createFile(FileDTO fileDTO);

  FileDTO getFileById(Long id);

  List<FileDTO> getAllFiles();

  FileDTO updateFile(Long id, FileDTO fileDTO);

  void deleteFile(Long id);

}
