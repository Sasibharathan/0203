package com.MosIC.MosIC_Office.sales.service;

import com.MosIC.MosIC_Office.activity_package.entity.ActivityEntity;
import com.MosIC.MosIC_Office.activity_package.repository.ActivityRepository;
import com.MosIC.MosIC_Office.sales.dto.SalesDTO;
import com.MosIC.MosIC_Office.sales.entity.SalesEntity;
import com.MosIC.MosIC_Office.sales.mapper.SalesMapper;
import com.MosIC.MosIC_Office.sales.repository.SalesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.servlet.http.HttpServletResponse;
@Service
public class SalesServicesImp implements SalesServices {

    private final SalesRepository salesRepository;
    private final ActivityRepository activityRepository;

    public SalesServicesImp(SalesRepository salesRepository,
                             ActivityRepository activityRepository) {
        this.salesRepository = salesRepository;
        this.activityRepository = activityRepository;
    }

    // ── CREATE ────────────────────────────────────────────────────────────────
    @Override
    public SalesDTO createSales(SalesDTO salesDTO) {
        SalesEntity entity = SalesMapper.mapToEntity(salesDTO);
        entity.setId(null); // force null so IDENTITY generates it
        SalesEntity saved = salesRepository.save(entity);

        // ── Mirror desktop: insert a row into ACTIVITY_INDEX ─────────────────
        recordActivity(saved, "Sales record created");

        return SalesMapper.mapToDTO(saved);
    }

    // ── GET BY ID ─────────────────────────────────────────────────────────────
    @Override
    public SalesDTO getSalesById(Long id) {
        SalesEntity entity = salesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sales record not found with id: " + id));
        return SalesMapper.mapToDTO(entity);
    }

    // ── GET ALL ───────────────────────────────────────────────────────────────
    @Override
    public List<SalesDTO> getAllSales() {
        return salesRepository.findAll()
                .stream()
                .map(SalesMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────
    @Override
    public SalesDTO updateSales(Long id, SalesDTO salesDTO) {
        SalesEntity entity = salesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sales record not found with id: " + id));

        entity.setSalesDate(salesDTO.getSalesDate());
        entity.setSalesValidity(salesDTO.getSalesValidity());
        entity.setSalesFromParty(salesDTO.getSalesFromParty());
        entity.setSalesToParty(salesDTO.getSalesToParty());
        entity.setSalesEnquireDate(salesDTO.getSalesEnquireDate());
        entity.setSalesDeliveryTerms(salesDTO.getSalesDeliveryTerms());
        entity.setSalesPaymentTerms(salesDTO.getSalesPaymentTerms());
        entity.setSalesCurrency(salesDTO.getSalesCurrency());
        entity.setSalesDoctype(salesDTO.getSalesDoctype());
        entity.setSalesTxType(salesDTO.getSalesTxType());
        entity.setSalesDescription(salesDTO.getSalesDescription());
        entity.setSalesAddressedTo(salesDTO.getSalesAddressedTo());
        entity.setSalesFileRef(salesDTO.getSalesFileRef());
        entity.setSalesStatus(salesDTO.getSalesStatus());

        SalesEntity updated = salesRepository.save(entity);

        // ── Mirror desktop: insert a row into ACTIVITY_INDEX ─────────────────
        recordActivity(updated, "Sales record updated");

        return SalesMapper.mapToDTO(updated);
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    @Override
    public void deleteSales(Long id) {
        SalesEntity entity = salesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sales record not found with id: " + id));
        // ── Delete only the activity rows that belong to THIS sales record ────
        // Matches on doc_id = sales ID and doc_table = "sales_register"
        // so activity rows for other documents on the same file are untouched.
        activityRepository.deleteByActivityDocIdAndActivityDocTable(
                String.valueOf(id), "sales_register"
        );

        salesRepository.delete(entity);
    }

    // ── ACTIVITY_INDEX helper ─────────────────────────────────────────────────
    /**
     * Inserts one row into ACTIVITY_INDEX after a Sales record is saved.
     * Mirrors the desktop application's behaviour exactly:
     *   ref_file_no  = salesFileRef  (the file this document belongs to)
     *   a_date       = today's date
     *   remarks      = caller-supplied label
     *   doc_id       = saved record's generated ID (as String)
     *   doc_table    = "sales_register"
     *   status       = "1"
     */
    private void recordActivity(SalesEntity saved, String remarks) {
        ActivityEntity activity = new ActivityEntity();
        String ref = saved.getSalesFileRef(); // e.g. "12345-sasi"
        String idOnly = ref.split("-")[0];    // "12345"
        activity.setActivityReferenceNo(idOnly);
       // activity.setActivityReferenceNo(saved.getSalesFileRef());
        activity.setActivityDate(LocalDate.now().toString());
        activity.setActivityRemarks(remarks);
        activity.setActivityDocId(String.valueOf(saved.getId()));
        activity.setActivityDocTable("SALES_REGISTER");
        activity.setActivityStatus("1");
        activityRepository.save(activity);
    }
}