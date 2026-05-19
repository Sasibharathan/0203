package com.MosIC.MosIC_Office.purchase_items.service;

import com.MosIC.MosIC_Office.purchase_items.dto.PurchaseItemDTO;
import com.MosIC.MosIC_Office.purchase_items.entity.PurchaseItemEntity;
import com.MosIC.MosIC_Office.purchase_items.mapper.PurchaseItemMapper;
import com.MosIC.MosIC_Office.purchase_items.repository.PurchaseItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PurchaseItemServiceImp implements PurchaseItemService {

    private final PurchaseItemRepository purchaseItemRepository;

    @Override
    public PurchaseItemDTO createPurchaseItem(PurchaseItemDTO purchaseItemDTO) {
        purchaseItemDTO.setStatus(1); // active by default
        PurchaseItemEntity entity = PurchaseItemMapper.mapToEntity(purchaseItemDTO);
        PurchaseItemEntity saved = purchaseItemRepository.save(entity);
        return PurchaseItemMapper.mapToDTO(saved);
    }

    @Override
    public PurchaseItemDTO getPurchaseItemById(Long id) {
        PurchaseItemEntity entity = purchaseItemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Purchase item not found with id: " + id));
        return PurchaseItemMapper.mapToDTO(entity);
    }

    @Override
    public List<PurchaseItemDTO> getAllPurchaseItems() {
        return purchaseItemRepository.findByStatus(1)
            .stream()
            .map(PurchaseItemMapper::mapToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<PurchaseItemDTO> getPurchaseItemsByRefFileNo(Integer refFileNo) {
        return purchaseItemRepository.findByRefFileNoAndStatus(refFileNo, 1)
            .stream()
            .map(PurchaseItemMapper::mapToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public PurchaseItemDTO updatePurchaseItem(Long id, PurchaseItemDTO dto) {
        PurchaseItemEntity existing = purchaseItemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Purchase item not found with id: " + id));

        existing.setNameOfProductService(dto.getNameOfProductService());
        existing.setHsnAcs(dto.getHsnAcs());
        existing.setQuantity(dto.getQuantity());
        existing.setUnit(dto.getUnit());
        existing.setUnitRate(dto.getUnitRate());
        existing.setTaxableValue(dto.getTaxableValue());
        existing.setCgstRate(dto.getCgstRate());
        existing.setCgstAmount(dto.getCgstAmount());
        existing.setSgstRate(dto.getSgstRate());
        existing.setSgstAmount(dto.getSgstAmount());
        existing.setIgstRate(dto.getIgstRate());
        existing.setIgstAmount(dto.getIgstAmount());
        existing.setTotal(dto.getTotal());
        existing.setRefFileNo(dto.getRefFileNo());
        existing.setStatus(dto.getStatus());

        return PurchaseItemMapper.mapToDTO(purchaseItemRepository.save(existing));
    }

    @Override
    public void deletePurchaseItem(Long id) {
        PurchaseItemEntity entity = purchaseItemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Purchase item not found with id: " + id));
        entity.setStatus(0); // soft delete
        purchaseItemRepository.save(entity);
    }
}