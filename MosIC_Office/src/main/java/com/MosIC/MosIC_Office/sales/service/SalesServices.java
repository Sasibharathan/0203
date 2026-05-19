package com.MosIC.MosIC_Office.sales.service;

import java.util.List;
import com.MosIC.MosIC_Office.sales.dto.SalesDTO;

public interface SalesServices {

  SalesDTO createSales(SalesDTO salesDTO);

  SalesDTO getSalesById(Long id);

  List<SalesDTO> getAllSales();

  SalesDTO updateSales(Long id, SalesDTO salesDTO);

  void deleteSales(Long id);


}
