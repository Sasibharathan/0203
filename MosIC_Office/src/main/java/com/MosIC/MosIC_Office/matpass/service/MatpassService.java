package com.MosIC.MosIC_Office.matpass.service;

import com.MosIC.MosIC_Office.matpass.dto.MatpassDTO;

import java.util.List;

public interface MatpassService {

    MatpassDTO createMatpass(MatpassDTO matpassDTO);

    MatpassDTO getMatpassById(Long id);

    List<MatpassDTO> getAllMatpasses();

    List<MatpassDTO> getMatpassesByStatus(Integer status);

    List<MatpassDTO> getMatpassesByInOrOut(String inOrOut);

    MatpassDTO updateMatpass(Long id, MatpassDTO matpassDTO);

    void deleteMatpass(Long id);  // soft delete — sets status to 0
}