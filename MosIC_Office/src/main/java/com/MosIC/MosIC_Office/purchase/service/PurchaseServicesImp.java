package com.MosIC.MosIC_Office.purchase.service;

import com.MosIC.MosIC_Office.activity_package.entity.ActivityEntity;
import com.MosIC.MosIC_Office.activity_package.repository.ActivityRepository;
import com.MosIC.MosIC_Office.purchase.dto.PurchaseDTO;
import com.MosIC.MosIC_Office.purchase.entity.PurchaseEntity;
import com.MosIC.MosIC_Office.purchase.mapper.PurchaseMapper;
import com.MosIC.MosIC_Office.purchase.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseServicesImp implements PurchaseServices {

    private final PurchaseRepository purchaseRepository;
    private final ActivityRepository activityRepository;

    // Replaced @AllArgsConstructor with explicit constructor so we can inject
    // ActivityRepository without touching the Lombok annotation on the class.
    public PurchaseServicesImp(PurchaseRepository purchaseRepository,
                                ActivityRepository activityRepository) {
        this.purchaseRepository = purchaseRepository;
        this.activityRepository = activityRepository;
    }

    @Override
    public PurchaseDTO createPurchase(PurchaseDTO purchaseDTO) {
        purchaseDTO.setPurchaseStatus("1"); // active by default
        PurchaseEntity entity = PurchaseMapper.mapToEntity(purchaseDTO);
        entity.setId(null); // force null so IDENTITY generates it — prevents id=0 stale-state error
        PurchaseEntity saved = purchaseRepository.save(entity);

        // ── Mirror desktop: insert a row into ACTIVITY_INDEX ─────────────────
        recordActivity(saved, "Purchase record created");

        return PurchaseMapper.mapToDTO(saved);
    }

    @Override
    public PurchaseDTO getPurchaseById(Long id) {
        PurchaseEntity entity = purchaseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Purchase not found with id: " + id));
        return PurchaseMapper.mapToDTO(entity);
    }

    @Override
    public List<PurchaseDTO> getAllPurchases() {
        return purchaseRepository.findByPurchaseStatus(1)
            .stream()
            .map(PurchaseMapper::mapToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public PurchaseDTO updatePurchase(Long id, PurchaseDTO purchaseDTO) {
        PurchaseEntity existing = purchaseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Purchase not found with id: " + id));

        existing.setPurchaseDate(purchaseDTO.getPurchaseDate());
        existing.setPurchaseValidity(purchaseDTO.getPurchaseValidity());
        existing.setPurchaseFromParty(purchaseDTO.getPurchaseFromParty());
        existing.setPurchaseToParty(purchaseDTO.getPurchaseToParty());
        existing.setPurchaseEnquireDate(purchaseDTO.getPurchaseEnquireDate());
        existing.setPurchaseDeliveryTerms(purchaseDTO.getPurchaseDeliveryTerms());
        existing.setPurchasePaymentTerms(purchaseDTO.getPurchasePaymentTerms());
        existing.setPurchaseCurrency(purchaseDTO.getPurchaseCurrency());
        existing.setPurchaseDoctype(Integer.parseInt(purchaseDTO.getPurchaseDoctype()));
        existing.setPurchaseTxType(purchaseDTO.getPurchaseTxType());
        existing.setPurchaseDescription(purchaseDTO.getPurchaseDescription());
        existing.setPurchaseAddressedTo(purchaseDTO.getPurchaseAddressedTo());
        existing.setPurchaseFileRef(purchaseDTO.getPurchaseFileRef());
        existing.setPurchaseStatus(Integer.parseInt(purchaseDTO.getPurchaseStatus()));

        PurchaseEntity updated = purchaseRepository.save(existing);

        // ── Mirror desktop: insert a row into ACTIVITY_INDEX ─────────────────
        recordActivity(updated, "Purchase record updated");

        return PurchaseMapper.mapToDTO(updated);
    }

    @Override
    public void deletePurchase(Long id) {
        PurchaseEntity entity = purchaseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Purchase not found with id: " + id));
        entity.setPurchaseStatus(0); // soft delete
        purchaseRepository.save(entity);
    }

    // ── ACTIVITY_INDEX helper ─────────────────────────────────────────────────
    /**
     * Inserts one row into ACTIVITY_INDEX after a Purchase record is saved.
     * Mirrors the desktop application's behaviour exactly:
     *   ref_file_no  = purchaseFileRef  (the file this document belongs to)
     *   a_date       = today's date
     *   remarks      = caller-supplied label
     *   doc_id       = saved record's generated ID (as String)
     *   doc_table    = "PURCHASE_REGISTER"
     *   status       = "1"
     */
    private void recordActivity(PurchaseEntity saved, String remarks) {
        ActivityEntity activity = new ActivityEntity();
        String ref = saved.getPurchaseFileRef();       // e.g. "12345-sasi"
        String idOnly = ref.split("-")[0];
        activity.setActivityReferenceNo(idOnly);
        activity.setActivityDate(LocalDate.now().toString());
        activity.setActivityRemarks(remarks);
        activity.setActivityDocId(String.valueOf(saved.getId()));
        activity.setActivityDocTable("PURCHASE_REGISTER");
        activity.setActivityStatus("1");
        activityRepository.save(activity);
    }
}