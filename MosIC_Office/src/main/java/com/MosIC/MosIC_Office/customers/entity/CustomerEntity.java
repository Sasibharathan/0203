package com.MosIC.MosIC_Office.customers.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;
import com.MosIC.MosIC_Office.customers_party.entity.PartyEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_details")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "buyer_address_1")
    private String buyerAddress1;

    @Column(name = "buyer_address_2")
    private String buyerAddress2;

    @Column(name = "buyer_address_3")
    private String buyerAddress3;

    @Column(name = "shipping_address_1")
    private String shippingAddress1;

    @Column(name = "shipping_address_2")
    private String shippingAddress2;

    @Column(name = "shipping_address_3")
    private String shippingAddress3;

    @Column(name = "website")
    private String website;

    @Column(name = "gmailid")
    private String gmailId;

    @Column(name = "contact")
    private String contact;

    @Column(name = "cin")
    private String cin;

    @Column(name = "gst")
    private String gst;

    @Column(name = "pan")
    private String pan;

    @Column(name = "tan")
    private String tan;

    @Column(name = "bankaccholder")
    private String bankAccHolder;

    @Column(name = "bankname")
    private String bankName;

    @Column(name = "accnumber")
    private String accNumber;

    @Column(name = "micrcode")
    private String micrCode;

    @Column(name = "ifsccode", nullable = false)
    private String ifscCode;

    @Column(name = "c_swift_code")
    private String cSwiftCode;

    @Column(name = "c_bankcode")
    private String cBankCode;

    @Column(name = "c_iban")
    private String cIban;

    @Column(name = "c_bankbranch_add_1")
    private String cBankBranchAdd1;

    @Column(name = "c_bankbranch_add_2")
    private String cBankBranchAdd2;

    @Column(name = "c_bankbranch_add_3")
    private String cBankBranchAdd3;

    @Column(name = "cust_type")
    private String custType;

    @Column(name = "status")
    private Integer status;

    @Column(name = "c_gstlutno")
    private String cGstLutNo;

    @Column(name = "datecreated", insertable = false, updatable = false)
    private java.sql.Timestamp dateCreated; // let DB default handle it

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PartyEntity> parties;
}
