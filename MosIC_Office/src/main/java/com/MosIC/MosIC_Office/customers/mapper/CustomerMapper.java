package com.MosIC.MosIC_Office.customers.mapper;

import com.MosIC.MosIC_Office.customers.dto.CustomerDTO;
import com.MosIC.MosIC_Office.customers.entity.CustomerEntity;

public final class CustomerMapper {

    //private CustomerMapper() {
    //}

    public static CustomerDTO mapToDTO(CustomerEntity e) {
        if (e == null) {
            return null;
        }

        CustomerDTO dto = new CustomerDTO();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setBuyerAddress1(e.getBuyerAddress1());
        dto.setBuyerAddress2(e.getBuyerAddress2());
        dto.setBuyerAddress3(e.getBuyerAddress3());
        dto.setShippingAddress1(e.getShippingAddress1());
        dto.setShippingAddress2(e.getShippingAddress2());
        dto.setShippingAddress3(e.getShippingAddress3());
        dto.setWebsite(e.getWebsite());
        dto.setGmailId(e.getGmailId());
        dto.setContact(e.getContact());
        dto.setCin(e.getCin());
        dto.setGst(e.getGst());
        dto.setPan(e.getPan());
        dto.setTan(e.getTan());
        dto.setBankAccHolder(e.getBankAccHolder());
        dto.setBankName(e.getBankName());
        dto.setAccNumber(e.getAccNumber());
        dto.setMicrCode(e.getMicrCode());
        dto.setIfscCode(e.getIfscCode());
        dto.setCSwiftCode(e.getCSwiftCode());
        dto.setCBankCode(e.getCBankCode());
        dto.setCIban(e.getCIban());
        dto.setCBankBranchAdd1(e.getCBankBranchAdd1());
        dto.setCBankBranchAdd2(e.getCBankBranchAdd2());
        dto.setCBankBranchAdd3(e.getCBankBranchAdd3());
        dto.setCustType(e.getCustType());
        dto.setStatus(e.getStatus() != null ? e.getStatus() : 1);
        dto.setCGstLutNo(e.getCGstLutNo());
        return dto;
    }

    public static CustomerEntity mapToEntity(CustomerDTO d) {
        if (d == null) {
            return null;
        }

        CustomerEntity entity = new CustomerEntity();
        entity.setId(d.getId());
        entity.setName(d.getName());
        entity.setBuyerAddress1(d.getBuyerAddress1());
        entity.setBuyerAddress2(d.getBuyerAddress2());
        entity.setBuyerAddress3(d.getBuyerAddress3());
        entity.setShippingAddress1(d.getShippingAddress1());
        entity.setShippingAddress2(d.getShippingAddress2());
        entity.setShippingAddress3(d.getShippingAddress3());
        entity.setWebsite(d.getWebsite());
        entity.setGmailId(d.getGmailId());
        entity.setContact(d.getContact());
        entity.setCin(d.getCin());
        entity.setGst(d.getGst());
        entity.setPan(d.getPan());
        entity.setTan(d.getTan());
        entity.setBankAccHolder(d.getBankAccHolder());
        entity.setBankName(d.getBankName());
        entity.setAccNumber(d.getAccNumber());
        entity.setMicrCode(d.getMicrCode());
        entity.setIfscCode(d.getIfscCode());
        entity.setCSwiftCode(d.getCSwiftCode());
        entity.setCBankCode(d.getCBankCode());
        entity.setCIban(d.getCIban());
        entity.setCBankBranchAdd1(d.getCBankBranchAdd1());
        entity.setCBankBranchAdd2(d.getCBankBranchAdd2());
        entity.setCBankBranchAdd3(d.getCBankBranchAdd3());
        entity.setCustType(d.getCustType() != null ? d.getCustType() : "1");
        entity.setStatus(d.getStatus() != null ? d.getStatus() : 1);
        entity.setCGstLutNo(d.getCGstLutNo());
        return entity;
    }
}
