package com.MosIC.MosIC_Office.activity_package.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.MosIC.MosIC_Office.activity_package.dto.ActivityDTO;
import com.MosIC.MosIC_Office.activity_package.entity.ActivityEntity;

class ActivityMapperTest {

    @Test
    void mapToEntity_shouldPreserveReferenceAndDateFields() {
        ActivityDTO dto = new ActivityDTO(
                7L,
                "REF-2026-001",
                "2026-03-05",
                "sample remarks",
                "DOC-9",
                "doc_blob_table",
                "OPEN"
        );

        ActivityEntity entity = ActivityMapper.mapToEntity(dto);

        assertEquals(7L, entity.getId());
        assertEquals("REF-2026-001", entity.getActivityReferenceNo());
        assertEquals("2026-03-05", entity.getActivityDate());
        assertEquals("sample remarks", entity.getActivityRemarks());
        assertEquals("DOC-9", entity.getActivityDocId());
        assertEquals("doc_blob_table", entity.getActivityDocTable());
        assertEquals("OPEN", entity.getActivityStatus());
    }

    @Test
    void mapToDto_shouldPreserveReferenceAndDateFields() {
        ActivityEntity entity = new ActivityEntity(
                8L,
                "REF-2026-002",
                "2026-03-06",
                "entity remarks",
                "DOC-10",
                "activity_index",
                "DONE"
        );

        ActivityDTO dto = ActivityMapper.mapToDTO(entity);

        assertEquals(8L, dto.getId());
        assertEquals("REF-2026-002", dto.getActivityReferenceNo());
        assertEquals("2026-03-06", dto.getActivityDate());
        assertEquals("entity remarks", dto.getActivityRemarks());
        assertEquals("DOC-10", dto.getActivityDocId());
        assertEquals("activity_index", dto.getActivityDocTable());
        assertEquals("DONE", dto.getActivityStatus());
    }
}
