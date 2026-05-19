package com.MosIC.MosIC_Office.customers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/***
 * Matches table CUSTOMER_DETAILS
 */
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private Long id; // wrapper type allows null
    private String name;
    private String buyerAddress1;
    private String buyerAddress2;
    private String buyerAddress3;
    private String shippingAddress1;
    private String shippingAddress2;
    private String shippingAddress3;
    private String website;
    private String gmailId;
    private String contact;
    private String cin;
    private String gst;
    private String pan;
    private String tan;
    private String bankAccHolder;
    private String bankName;
    private String accNumber;
    private String micrCode;
    private String ifscCode;
    @JsonProperty("cSwiftCode")
    private String cSwiftCode;//
    @JsonProperty("cBankCode")
    private String cBankCode;
    @JsonProperty("cIban")
    private String cIban;
    @JsonProperty("cBankBranchAdd1")
    private String cBankBranchAdd1;
    @JsonProperty("cBankBranchAdd2")
    private String cBankBranchAdd2;
    @JsonProperty("cBankBranchAdd3")
    private String cBankBranchAdd3;
    private String custType;
    private Integer status;     // must be Integer to match TINYINT
    @JsonProperty("cGstLutNo")
    private String cGstLutNo;  // nullable
}
