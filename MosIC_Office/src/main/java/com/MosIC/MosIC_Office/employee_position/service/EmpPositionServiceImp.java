package com.MosIC.MosIC_Office.employee_position.service;

import com.MosIC.MosIC_Office.employee_position.dto.EmpPositionDTO;
import com.MosIC.MosIC_Office.employee_position.entity.EmpPositionEntity;
import com.MosIC.MosIC_Office.employee_position.mapper.EmpPositionMapper;
import com.MosIC.MosIC_Office.employee_position.repository.EmpPositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpPositionServiceImp implements EmpPositionService {

    private final EmpPositionRepository empPositionRepository;

    @Override
    public EmpPositionDTO createEmpPosition(EmpPositionDTO dto) {
        EmpPositionEntity entity = EmpPositionMapper.toEntity(dto);
        entity.setActiveStatus(1);
        entity.setStatus(1);
        EmpPositionEntity saved = empPositionRepository.save(entity);
        return EmpPositionMapper.toDTO(saved);
    }

    @Override
    public EmpPositionDTO getEmpPositionById(Long id) {
        EmpPositionEntity entity = empPositionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EmpPosition not found with id: " + id));
        return EmpPositionMapper.toDTO(entity);
    }

    @Override
    public List<EmpPositionDTO> getAllEmpPositions() {
        return empPositionRepository.findAll()
                .stream()
                .map(EmpPositionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmpPositionDTO> getEmpPositionsByEmpId(Long empId) {
        return empPositionRepository.findByEmpId(empId)
                .stream()
                .map(EmpPositionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmpPositionDTO updateEmpPosition(Long id, EmpPositionDTO dto) {
        EmpPositionEntity existing = empPositionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EmpPosition not found with id: " + id));

        existing.setEmpId(dto.getEmpId());
        existing.setEpDate(dto.getEpDate());
        existing.setEpEfficientDate(dto.getEpEfficientDate());
        existing.setPosition(dto.getPosition());
        existing.setDepartment(dto.getDepartment());
        existing.setRole(dto.getRole());
        existing.setReportingTo(dto.getReportingTo());
        existing.setEmpBasic(dto.getEmpBasic());
        existing.setEmpHra(dto.getEmpHra());
        existing.setEmpAllowance(dto.getEmpAllowance());
        existing.setEmpMonthGross(dto.getEmpMonthGross());
        existing.setEmpCtc(dto.getEmpCtc());
        existing.setEmpTds(dto.getEmpTds());
        existing.setEmpPt(dto.getEmpPt());
        existing.setEmpLoans(dto.getEmpLoans());
        if (dto.getStatus() != null) {
            existing.setStatus(Integer.parseInt(dto.getStatus()));
        }
        // ✅ FIX: also persist activeStatus changes (needed for reactivation via Edit)
        if (dto.getActiveStatus() != null) {
            existing.setActiveStatus(Integer.parseInt(dto.getActiveStatus()));
        }

        EmpPositionEntity updated = empPositionRepository.save(existing);
        return EmpPositionMapper.toDTO(updated);
    }

    @Override
    public void deleteEmpPosition(Long id) {
        if (!empPositionRepository.existsById(id)) {
            throw new RuntimeException("EmpPosition not found with id: " + id);
        }
        empPositionRepository.deleteById(id);
    }

    @Override
    public void softDeleteEmpPosition(Long id) {
        EmpPositionEntity entity = empPositionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EmpPosition not found with id: " + id));
        entity.setActiveStatus(0);
        empPositionRepository.save(entity);
    }

    // ✅ NEW: mirror of softDelete — sets activeStatus back to 1
    @Override
    public void reactivateEmpPosition(Long id) {
        EmpPositionEntity entity = empPositionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EmpPosition not found with id: " + id));
        entity.setActiveStatus(1);
        empPositionRepository.save(entity);
    }
}