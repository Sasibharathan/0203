package com.MosIC.MosIC_Office.stocks.service;

import com.MosIC.MosIC_Office.stocks.dto.StocksDTO;
import com.MosIC.MosIC_Office.stocks.entity.StocksEntity;
import com.MosIC.MosIC_Office.stocks.mapper.StocksMapper;
import com.MosIC.MosIC_Office.stocks.repository.StocksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StocksServiceImp implements StocksService {

    private final StocksRepository stocksRepository;

    @Override
    public StocksDTO createStock(StocksDTO stocksDTO) {
        StocksEntity entity = StocksMapper.mapToEntity(stocksDTO);
        if (entity.getStatus() == null) {
            entity.setStatus(1);
        }
        StocksEntity saved = stocksRepository.save(entity);
        return StocksMapper.mapToDTO(saved);
    }

    @Override
    public StocksDTO getStockById(Long id) {
        StocksEntity entity = stocksRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock not found with id: " + id));
        return StocksMapper.mapToDTO(entity);
    }

    @Override
    public List<StocksDTO> getAllStocks() {
        return stocksRepository.findAll()
                .stream()
                .map(StocksMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StocksDTO> getStocksByStatus(Integer status) {
        return stocksRepository.findByStatus(status)
                .stream()
                .map(StocksMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StocksDTO> getStocksByStockItemId(Long stockItemId) {
        return stocksRepository.findByStockItemId(stockItemId)
                .stream()
                .map(StocksMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StocksDTO> getStocksByMatPassId(Long matPassId) {
        return stocksRepository.findByMatPassId(matPassId)
                .stream()
                .map(StocksMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StocksDTO> getStocksByInOut(String stockInOut) {
        return stocksRepository.findByStockInOut(stockInOut)
                .stream()
                .map(StocksMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StocksDTO updateStock(Long id, StocksDTO stocksDTO) {
        StocksEntity existing = stocksRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock not found with id: " + id));

        existing.setStockItemId(stocksDTO.getStockItemId());
        existing.setStockDate(stocksDTO.getStockDate());
        existing.setStockDescription(stocksDTO.getStockDescription());
        existing.setStockInOut(stocksDTO.getStockInOut());
        existing.setStockQuantity(stocksDTO.getStockQuantity());
        existing.setStockReturnOrNonReturn(stocksDTO.getStockReturnOrNonReturn());
        existing.setStockParty(stocksDTO.getStockParty());
        existing.setMatPassId(stocksDTO.getMatPassId());
        if (stocksDTO.getStatus() != null) {
            existing.setStatus(stocksDTO.getStatus());
        }

        StocksEntity updated = stocksRepository.save(existing);
        return StocksMapper.mapToDTO(updated);
    }

    @Override
    public void deleteStock(Long id) {
        StocksEntity existing = stocksRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock not found with id: " + id));
        existing.setStatus(0);  // soft delete
        stocksRepository.save(existing);
    }
}