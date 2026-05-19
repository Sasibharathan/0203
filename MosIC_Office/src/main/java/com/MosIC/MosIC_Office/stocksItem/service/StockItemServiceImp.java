package com.MosIC.MosIC_Office.stocksItem.service;

import com.MosIC.MosIC_Office.stocksItem.dto.StockItemDTO;
import com.MosIC.MosIC_Office.stocksItem.entity.StockItemEntity;
import com.MosIC.MosIC_Office.stocksItem.mapper.StockItemMapper;
import com.MosIC.MosIC_Office.stocksItem.repository.StockItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockItemServiceImp implements StockItemService {

    private final StockItemRepository stockItemRepository;

    @Override
    public StockItemDTO createStockItem(StockItemDTO stockItemDTO) {
        StockItemEntity entity = StockItemMapper.mapToEntity(stockItemDTO);
        if (entity.getStatus() == null) {
            entity.setStatus(1);
        }
        if (entity.getSmOpeningBalance() == null) {
            entity.setSmOpeningBalance(0);
        }
        StockItemEntity saved = stockItemRepository.save(entity);
        return StockItemMapper.mapToDTO(saved);
    }

    @Override
    public StockItemDTO getStockItemById(Long id) {
        StockItemEntity entity = stockItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock item not found with id: " + id));
        return StockItemMapper.mapToDTO(entity);
    }

    @Override
    public List<StockItemDTO> getAllStockItems() {
        return stockItemRepository.findAll()
                .stream()
                .map(StockItemMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StockItemDTO> getStockItemsByStatus(Integer status) {
        return stockItemRepository.findByStatus(status)
                .stream()
                .map(StockItemMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StockItemDTO> searchStockItemsByName(String productName) {
        return stockItemRepository.findByProductNameContainingIgnoreCase(productName)
                .stream()
                .map(StockItemMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StockItemDTO updateStockItem(Long id, StockItemDTO stockItemDTO) {
        StockItemEntity existing = stockItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock item not found with id: " + id));

        existing.setProductName(stockItemDTO.getProductName());
        existing.setOpeningDate(stockItemDTO.getOpeningDate());
        existing.setSmDescription(stockItemDTO.getSmDescription());
        existing.setSmUnit(stockItemDTO.getSmUnit());
        if (stockItemDTO.getSmOpeningBalance() != null) {
            existing.setSmOpeningBalance(stockItemDTO.getSmOpeningBalance());
        }
        if (stockItemDTO.getStatus() != null) {
            existing.setStatus(stockItemDTO.getStatus());
        }

        StockItemEntity updated = stockItemRepository.save(existing);
        return StockItemMapper.mapToDTO(updated);
    }

    @Override
    public void deleteStockItem(Long id) {
        StockItemEntity existing = stockItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock item not found with id: " + id));
        existing.setStatus(0);  // soft delete
        stockItemRepository.save(existing);
    }
}