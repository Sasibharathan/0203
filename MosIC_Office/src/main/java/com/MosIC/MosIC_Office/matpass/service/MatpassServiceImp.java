package com.MosIC.MosIC_Office.matpass.service;

import com.MosIC.MosIC_Office.activity_package.entity.ActivityEntity;
import com.MosIC.MosIC_Office.activity_package.repository.ActivityRepository;
import com.MosIC.MosIC_Office.matpass.dto.MatpassDTO;
import com.MosIC.MosIC_Office.matpass.entity.MatpassEntity;
import com.MosIC.MosIC_Office.matpass.mapper.MatpassMapper;
import com.MosIC.MosIC_Office.matpass.repository.MatpassRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatpassServiceImp implements MatpassService {

    private final MatpassRepository matpassRepository;
    private final ActivityRepository activityRepository;

    // Explicit constructor replaces @RequiredArgsConstructor so we can include
    // ActivityRepository without touching the Lombok annotation.
    public MatpassServiceImp(MatpassRepository matpassRepository,
                              ActivityRepository activityRepository) {
        this.matpassRepository = matpassRepository;
        this.activityRepository = activityRepository;
    }

    @Override
    public MatpassDTO createMatpass(MatpassDTO matpassDTO) {
        MatpassEntity entity = MatpassMapper.mapToEntity(matpassDTO);
        if (entity.getStatus() == null) {
            entity.setStatus(1);
        }
        MatpassEntity saved = matpassRepository.save(entity);

        // ── Mirror desktop: insert a row into ACTIVITY_INDEX ─────────────────
        recordActivity(saved, "Material Pass created");

        return MatpassMapper.mapToDTO(saved);
    }

    @Override
    public MatpassDTO getMatpassById(Long id) {
        MatpassEntity entity = matpassRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matpass not found with id: " + id));
        return MatpassMapper.mapToDTO(entity);
    }

    @Override
    public List<MatpassDTO> getAllMatpasses() {
        return matpassRepository.findAll()
                .stream()
                .map(MatpassMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MatpassDTO> getMatpassesByStatus(Integer status) {
        return matpassRepository.findByStatus(status)
                .stream()
                .map(MatpassMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MatpassDTO> getMatpassesByInOrOut(String inOrOut) {
        return matpassRepository.findByInOrOut(inOrOut)
                .stream()
                .map(MatpassMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MatpassDTO updateMatpass(Long id, MatpassDTO matpassDTO) {
        MatpassEntity existing = matpassRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matpass not found with id: " + id));

        existing.setInOrOut(matpassDTO.getInOrOut());
        existing.setParty(matpassDTO.getParty());
        existing.setDate(matpassDTO.getDate());
        existing.setContactPerson(matpassDTO.getContactPerson());
        existing.setDiscription(matpassDTO.getDiscription());
        existing.setFileRef(matpassDTO.getFileRef());
        if (matpassDTO.getStatus() != null) {
            existing.setStatus(matpassDTO.getStatus());
        }

        MatpassEntity updated = matpassRepository.save(existing);

        // ── Mirror desktop: insert a row into ACTIVITY_INDEX ─────────────────
        recordActivity(updated, "Material Pass updated");

        return MatpassMapper.mapToDTO(updated);
    }

    @Override
    public void deleteMatpass(Long id) {
        MatpassEntity existing = matpassRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matpass not found with id: " + id));
        existing.setStatus(0); // soft delete
        matpassRepository.save(existing);
    }

    // ── ACTIVITY_INDEX helper ─────────────────────────────────────────────────
    /**
     * Inserts one row into ACTIVITY_INDEX after a MatPass record is saved.
     * Mirrors the desktop application's behaviour exactly:
     *   ref_file_no  = fileRef  (the file this material pass belongs to)
     *   a_date       = today's date
     *   remarks      = caller-supplied label
     *   doc_id       = saved record's generated ID (as String)
     *   doc_table    = "MATERIAL_PASS"
     *   status       = "1"
     */
    private void recordActivity(MatpassEntity saved, String remarks) {
        ActivityEntity activity = new ActivityEntity();
        String ref = saved.getFileRef();               // e.g. "12345-sasi"
        String idOnly = ref.split("-")[0];
        activity.setActivityReferenceNo(idOnly);
        activity.setActivityDate(LocalDate.now().toString());
        activity.setActivityRemarks(remarks);
        activity.setActivityDocId(String.valueOf(saved.getId()));
        activity.setActivityDocTable("MATERIAL_PASS");
        activity.setActivityStatus("1");
        activityRepository.save(activity);
    }
}