package com.MosIC.MosIC_Office.employee.service;

import com.MosIC.MosIC_Office.employee.dto.EmpDTO;
import com.MosIC.MosIC_Office.employee.entity.EmpEntity;
import com.MosIC.MosIC_Office.employee.mapper.EmpMapper;
import com.MosIC.MosIC_Office.employee.repository.EmpRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmpServiceImp implements EmpService {

    private final EmpRepository empRepository;

    @Override
    public EmpDTO createEmp(EmpDTO empDTO) {
        EmpEntity entity = EmpMapper.mapToEntity(empDTO);
        entity.setId(null); // ← force null so Hibernate always INSERTs
        EmpEntity saved = empRepository.save(entity);
        return EmpMapper.mapToDTO(saved);
    }

    @Override
    public EmpDTO getEmpById(Long id) {
        EmpEntity entity = empRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        return EmpMapper.mapToDTO(entity);
    }

    @Override
    public List<EmpDTO> getAllEmps() {
        return empRepository.findAll()
            .stream()
            .map(EmpMapper::mapToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public EmpDTO updateEmp(Long id, EmpDTO empDTO) {
        EmpEntity existing = empRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        existing.setEmpName(empDTO.getEmpName());
        existing.setEmpLastName(empDTO.getEmpLastName());
        existing.setEmpDob(empDTO.getEmpDob());
        existing.setEmpPh(empDTO.getEmpPh());
        existing.setEmpMail(empDTO.getEmpMail());
        existing.setEmpPan(empDTO.getEmpPan());
        existing.setEmpAdhar(empDTO.getEmpAdhar());
        existing.setEmpAccNo(empDTO.getEmpAccNo());
        existing.setEmpBankName(empDTO.getEmpBankName());
        existing.setEmpAccName(empDTO.getEmpAccName());
        existing.setEmpIfscCode(empDTO.getEmpIfscCode());
        existing.setEmpAddress1(empDTO.getEmpAddress1());
        existing.setEmpAddress2(empDTO.getEmpAddress2());
        existing.setEmpAddress3(empDTO.getEmpAddress3());
        existing.setEmpDoj(empDTO.getEmpDoj());
        existing.setStatus(empDTO.getStatus());

        EmpEntity updated = empRepository.save(existing);
        return EmpMapper.mapToDTO(updated);
    }

    @Override
    public void deleteEmp(Long id) {
        EmpEntity existing = empRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        existing.setStatus("0");
        empRepository.save(existing);
    }
}