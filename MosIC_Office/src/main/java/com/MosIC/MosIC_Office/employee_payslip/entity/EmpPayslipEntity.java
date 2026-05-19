package com.MosIC.MosIC_Office.employee_payslip.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//  Added @Getter, @Setter, @NoArgsConstructor so the Mapper can call
//      entity.getX() / entity.setX() without compilation errors.
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "emp_payslip")
public class EmpPayslipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "emp_id")
    private Long empId;

    @Column(name = "emp_month")
    private String empMonth;

    @Column(name = "emp_year")
    private String empYear;

    @Column(name = "basic")
    private String basic;

    @Column(name = "hra")
    private String hra;

    // NOTE: column name intentionally kept as "allowancess" to match the DB schema
    @Column(name = "allowancess")
    private String allowancess;

    @Column(name = "total_gross")
    private String totalGross;

    @Column(name = "tds")
    private String tds;

    @Column(name = "pt")
    private String pt;

    @Column(name = "loan")
    private String loan;

    @Column(name = "total_deduction")
    private String totalDeduction;

    @Column(name = "status", nullable = false, columnDefinition = "TINYINT DEFAULT 1")
    private Integer status = 1;
}