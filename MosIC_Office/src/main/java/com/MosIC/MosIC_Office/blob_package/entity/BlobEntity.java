package com.MosIC.MosIC_Office.blob_package.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "DOC_BLOB_TABLE")

public class BlobEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "file_name", nullable = false, length = 300)
  private String fileName;

  @Column(name = "file_size", nullable = false)
  private Long fileSize;

  @Column(name = "file_type")
  private String fileType;

  //@Lob
  //@Basic(fetch = FetchType.LAZY)
  @Column(name = "file_data" , columnDefinition = "bytea")//postgre sql need to add the col def so the blob will File upload failed and for h2 we dont need the col def and it will work fine without it
  private byte[] fileData;

  @Column(name = "description")
  private String description;

  @Column(name = "status", insertable = false)
  private Integer status;

}
