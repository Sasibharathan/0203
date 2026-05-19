package com.MosIC.MosIC_Office.employee_payslip.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;                          // FIX: missing import
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;       // FIX: missing import

import com.MosIC.MosIC_Office.employee_payslip.dto.EmpPayslipDTO;
import com.MosIC.MosIC_Office.employee_payslip.entity.EmpPayslipEntity;
import com.MosIC.MosIC_Office.employee_payslip.mapper.EmpPayslipMapper;
import com.MosIC.MosIC_Office.employee_payslip.repository.EmpPayslipRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpPayslipServiceImp implements EmpPayslipService {

    private final EmpPayslipRepository empPayslipRepository;

    /**
     * 1. CREATE
     * Converts DTO → Entity, forces status = 1 (active), saves, returns DTO.
     */
    @Override
    public EmpPayslipDTO createEmpPayslip(EmpPayslipDTO empPayslipDTO) {
        EmpPayslipEntity entity = EmpPayslipMapper.toEntity(empPayslipDTO);
        entity.setStatus(1); // default active
        EmpPayslipEntity saved = empPayslipRepository.save(entity);
        return EmpPayslipMapper.toDTO(saved);
    }

    /**
     * 2. READ BY ID
     * Throws 404 if not found.
     */
    @Override
    public EmpPayslipDTO getEmpPayslipById(Long id) {
        EmpPayslipEntity entity = empPayslipRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Payslip not found with id: " + id));
        return EmpPayslipMapper.toDTO(entity);
    }

    /**
     * 3. LIST ALL (active only)
     * FIX: findAllByStatus(1) now declared in the repository.
     */
    @Override
    public List<EmpPayslipDTO> getAllEmpPayslips() {
        return empPayslipRepository.findAllByStatus(1)
                .stream()
                .map(EmpPayslipMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * 4. LIST BY EMPLOYEE (active only)
     * FIX: findByEmpIdAndStatus(empId, 1) now declared in the repository.
     */
    /*
    @Override
    public List<EmpPayslipDTO> getPayslipsByEmpId(Long empId) {
        List<EmpPayslipEntity> payslips = empPayslipRepository.findByEmpIdAndStatus(empId, 1);
        if (payslips.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No active payslips found for employee id: " + empId);
        }
        return payslips.stream()
                .map(EmpPayslipMapper::toDTO)
                .collect(Collectors.toList());
    }
    */
    @Override
    public List<EmpPayslipDTO> getPayslipsByEmpId(Long empId) {
        return empPayslipRepository.findByEmpIdAndStatus(empId, 1)
                .stream()
                .map(EmpPayslipMapper::toDTO)
                .collect(Collectors.toList());
    }


    /**
     * 5. UPDATE
     * FIX: setAllowances() / getAllowances() → setAllowancess() / getAllowancess()
     *      to match the actual field name defined in the Entity and DTO.
     */
    @Override
    public EmpPayslipDTO updateEmpPayslip(Long id, EmpPayslipDTO empPayslipDTO) {
        EmpPayslipEntity existing = empPayslipRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Payslip not found with id: " + id));

        existing.setEmpId(empPayslipDTO.getEmpId());
        existing.setEmpMonth(empPayslipDTO.getEmpMonth());
        existing.setEmpYear(empPayslipDTO.getEmpYear());
        existing.setBasic(empPayslipDTO.getBasic());
        existing.setHra(empPayslipDTO.getHra());
        existing.setAllowancess(empPayslipDTO.getAllowancess()); // FIX: was setAllowances/getAllowances
        existing.setTotalGross(empPayslipDTO.getTotalGross());
        existing.setTds(empPayslipDTO.getTds());
        existing.setPt(empPayslipDTO.getPt());
        existing.setLoan(empPayslipDTO.getLoan());
        existing.setTotalDeduction(empPayslipDTO.getTotalDeduction());
        existing.setStatus(empPayslipDTO.getStatus());

        EmpPayslipEntity updated = empPayslipRepository.save(existing);
        return EmpPayslipMapper.toDTO(updated);
    }

    /**
     * 6. SOFT DELETE
     * Sets status = 0 instead of removing the record, preserving history.
     */
    @Override
    public void deleteEmpPayslip(Long id) {
        EmpPayslipEntity entity = empPayslipRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Payslip not found with id: " + id));
        entity.setStatus(0); // soft delete
        empPayslipRepository.save(entity);
    }
}