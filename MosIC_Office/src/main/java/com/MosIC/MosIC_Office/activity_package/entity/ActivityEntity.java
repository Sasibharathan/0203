package com.MosIC.MosIC_Office.activity_package.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "activity_index")
public class ActivityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ── Link to file_index ─────────────────────────────────────────────────────
    //  Many activities belong to one file.
    //  This adds a "file_id" FK column in activity_index.
    @Column(name = "ref_file_no",nullable = false)
    private String activityReferenceNo;


    @Column(name = "a_date")
    private String activityDate;

    @Column(name = "remarks")
    private String activityRemarks;


    // ── Blob reference (nullable — activity may have no blob) ─────────────────
    //  Stores the doc_blob_table.id as a Long.
    //  We keep doc_table for future polymorphic flexibility.
    @Column(name = "doc_id")
    private String activityDocId;// stores blob id as String (matches existing VARCHAR schema)

    @Column(name = "doc_table")
    private String activityDocTable;// always "doc_blob_table" when blob is attached

    @Column(name = "status")
    private String activityStatus;

}
