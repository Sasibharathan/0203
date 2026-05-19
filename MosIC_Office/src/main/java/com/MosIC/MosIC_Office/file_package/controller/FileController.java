package com.MosIC.MosIC_Office.file_package.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MosIC.MosIC_Office.file_package.dto.FileDTO;
import com.MosIC.MosIC_Office.file_package.service.FileServices;

@RestController
@RequestMapping("/api/files")

public class FileController {

  private FileServices fileServices;

  // ✅ CONSTRUCTOR INJECTION (IMPORTANT)
  public FileController(FileServices fileServices) {
      this.fileServices = fileServices;
  }

    //build create file rest api
  @PostMapping
  public ResponseEntity<FileDTO> createFile(@RequestBody FileDTO fileDTO) {
      return ResponseEntity.ok(fileServices.createFile(fileDTO));

  }

  @GetMapping("{id}")
  //BUILD GET FILE REST API
  public ResponseEntity<FileDTO> getFileById(@PathVariable("id")  Long id) {
      FileDTO fileDTO = fileServices.getFileById(id);
      return new ResponseEntity<>(fileDTO, HttpStatus.OK);

  }

//build get all files REST API
  @GetMapping
  public ResponseEntity<?> getAllFiles() {
    List<FileDTO> files = fileServices.getAllFiles();
    if (files.isEmpty()) {
      return null;
    }
    return new ResponseEntity<>(files, HttpStatus.OK);
    // return ResponseEntity.ok(fileServices.getAllFiles());
  }

  //build update file rest api
  @PutMapping("{id}")
  public ResponseEntity<FileDTO> updateFile(@PathVariable("id") Long id, @RequestBody FileDTO fileDTO) {
    FileDTO updatedFile = fileServices.updateFile(id, fileDTO);
    return new ResponseEntity<>(updatedFile, HttpStatus.OK);
  }
    //build delete file rest api
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteFile(@PathVariable("id") Long id) {
        fileServices.deleteFile(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}