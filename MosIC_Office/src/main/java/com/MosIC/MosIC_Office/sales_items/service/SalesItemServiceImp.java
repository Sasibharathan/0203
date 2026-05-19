package com.MosIC.MosIC_Office.sales_items.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MosIC.MosIC_Office.sales_items.dto.SalesItemDTO;
import com.MosIC.MosIC_Office.sales_items.entity.SalesItemEntity;
import com.MosIC.MosIC_Office.sales_items.mapper.SalesItemMapper;
import com.MosIC.MosIC_Office.sales_items.repository.SalesItemRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SalesItemServiceImp implements SalesItemService {

    private final SalesItemRepository salesItemRepository;

    // ─── CREATE ────────────────────────────────────────────────────────────────

    @Override
    public SalesItemDTO createSalesItem(SalesItemDTO salesItemDTO) {
        SalesItemEntity entity = SalesItemMapper.mapToEntity(salesItemDTO);
        entity.setId(null); // force null so IDENTITY generates it — prevents id=0 stale-state error
        if (entity.getStatus() == null) {
            entity.setStatus(1);
        }
        SalesItemEntity saved = salesItemRepository.save(entity);
        return SalesItemMapper.mapToDTO(saved);
    }

    // ─── READ (single) ─────────────────────────────────────────────────────────

    @Override
    public SalesItemDTO getSalesItemById(Long id) {
        SalesItemEntity entity = salesItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sales Item not found with id: " + id));
        return SalesItemMapper.mapToDTO(entity);
    }

    // ─── READ (all items under a Sales document) ───────────────────────────────

    @Override
    public List<SalesItemDTO> getSalesItemsByRefFileNo(Integer refFileNo) {
        return salesItemRepository.findByRefFileNo(refFileNo)
                .stream()
                .map(SalesItemMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    // ─── READ (all) ────────────────────────────────────────────────────────────

    @Override
    public List<SalesItemDTO> getAllSalesItems() {
        return salesItemRepository.findAll()
                .stream()
                .map(SalesItemMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    // ─── UPDATE ────────────────────────────────────────────────────────────────

    @Override
    public SalesItemDTO updateSalesItem(Long id, SalesItemDTO salesItemDTO) {
        SalesItemEntity existing = salesItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sales Item not found with id: " + id));

        existing.setNameOfProductService(salesItemDTO.getNameOfProductService());
        existing.setHsnAcs(salesItemDTO.getHsnAcs());
        existing.setQuantity(salesItemDTO.getQuantity());
        existing.setUnit(salesItemDTO.getUnit());
        existing.setUnitRate(salesItemDTO.getUnitRate());
        existing.setTaxableValue(salesItemDTO.getTaxableValue());
        existing.setCgstRate(salesItemDTO.getCgstRate());
        existing.setCgstAmount(salesItemDTO.getCgstAmount());
        existing.setSgstRate(salesItemDTO.getSgstRate());
        existing.setSgstAmount(salesItemDTO.getSgstAmount());
        existing.setIgstRate(salesItemDTO.getIgstRate());
        existing.setIgstAmount(salesItemDTO.getIgstAmount());
        existing.setTotal(salesItemDTO.getTotal());
        existing.setRefFileNo(salesItemDTO.getRefFileNo());
        existing.setStatus(salesItemDTO.getStatus());

        SalesItemEntity updated = salesItemRepository.save(existing);
        return SalesItemMapper.mapToDTO(updated);
    }

    // ─── DELETE (single) ──────────────────────────────────────────────────────

    @Override
    public void deleteSalesItem(Long id) {
        SalesItemEntity entity = salesItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sales Item not found with id: " + id));
        salesItemRepository.delete(entity);
    }

    // ─── DELETE (all items under a Sales document) ────────────────────────────

    @Override
    @Transactional
    public void deleteSalesItemsByRefFileNo(Integer refFileNo) {
        salesItemRepository.deleteByRefFileNo(refFileNo);
    }

}