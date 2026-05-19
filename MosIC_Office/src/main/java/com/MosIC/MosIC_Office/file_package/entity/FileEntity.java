package com.MosIC.MosIC_Office.file_package.entity;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "file_index")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "f_date")
    private String fileDate;

    @Column(name = "f_activity")
    private String fileActivity;

    @Column(name = "f_subject")
    private String fileSubject;

    @Column(name = "f_description")
    private String fileDescription;

    @Column(name = "status")
    private Integer fileStatus;

   // @Column(name = "datecreated", insertable = false, updatable = false)
   // private java.sql.Timestamp dateCreated; // let DB default handle it
}
