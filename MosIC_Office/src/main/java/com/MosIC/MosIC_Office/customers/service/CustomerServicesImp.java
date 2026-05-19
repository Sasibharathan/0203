package com.MosIC.MosIC_Office.customers.service;

import com.MosIC.MosIC_Office.customers.dto.CustomerDTO;
import com.MosIC.MosIC_Office.customers.entity.CustomerEntity;
import com.MosIC.MosIC_Office.customers.mapper.CustomerMapper;
import com.MosIC.MosIC_Office.customers.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServicesImp implements CustomerServices {

    private final CustomerRepository customerRepository;

    public CustomerServicesImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Null-safe helper
    private static String s(String v) { return v != null ? v : ""; }

    // CREATE
    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        CustomerEntity entity = CustomerMapper.mapToEntity(customerDTO);
        entity.setId(null);          // force IDENTITY generation
        coerceNulls(entity);         // "" instead of null for NOT NULL cols
        return CustomerMapper.mapToDTO(customerRepository.save(entity));
    }

    // GET BY ID
    @Override
    public CustomerDTO getCustomerById(Long id) {
        CustomerEntity entity = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        return CustomerMapper.mapToDTO(entity);
    }

    // GET ALL
    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    // UPDATE
    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        CustomerEntity entity = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        // map all fields safely
        entity.setName(s(customerDTO.getName()));
        entity.setBuyerAddress1(s(customerDTO.getBuyerAddress1()));
        entity.setBuyerAddress2(s(customerDTO.getBuyerAddress2()));
        entity.setBuyerAddress3(s(customerDTO.getBuyerAddress3()));
        entity.setShippingAddress1(s(customerDTO.getShippingAddress1()));
        entity.setShippingAddress2(s(customerDTO.getShippingAddress2()));
        entity.setShippingAddress3(s(customerDTO.getShippingAddress3()));
        entity.setWebsite(s(customerDTO.getWebsite()));
        entity.setGmailId(s(customerDTO.getGmailId()));
        entity.setContact(s(customerDTO.getContact()));
        entity.setCin(s(customerDTO.getCin()));
        entity.setGst(s(customerDTO.getGst()));
        entity.setPan(s(customerDTO.getPan()));
        entity.setTan(s(customerDTO.getTan()));
        entity.setBankAccHolder(s(customerDTO.getBankAccHolder()));
        entity.setBankName(s(customerDTO.getBankName()));
        entity.setAccNumber(s(customerDTO.getAccNumber()));
        entity.setMicrCode(s(customerDTO.getMicrCode()));
        entity.setIfscCode(s(customerDTO.getIfscCode()));
        entity.setCSwiftCode(s(customerDTO.getCSwiftCode()));
        entity.setCBankCode(s(customerDTO.getCBankCode()));
        entity.setCIban(s(customerDTO.getCIban()));
        entity.setCBankBranchAdd1(s(customerDTO.getCBankBranchAdd1()));
        entity.setCBankBranchAdd2(s(customerDTO.getCBankBranchAdd2()));
        entity.setCBankBranchAdd3(s(customerDTO.getCBankBranchAdd3()));
        entity.setCustType(customerDTO.getCustType() != null ? customerDTO.getCustType() : "1");
        entity.setStatus(customerDTO.getStatus() != null ? customerDTO.getStatus() : 1);
        entity.setCGstLutNo(s(customerDTO.getCGstLutNo()));

        return CustomerMapper.mapToDTO(customerRepository.save(entity));
    }

    // DELETE
    @Override
    public void deleteCustomer(Long id) {
        CustomerEntity entity = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        customerRepository.delete(entity);
    }

    // Coerce all string fields to "" if null
    private void coerceNulls(CustomerEntity e) {
        e.setName(s(e.getName()));
        e.setBuyerAddress1(s(e.getBuyerAddress1()));
        e.setBuyerAddress2(s(e.getBuyerAddress2()));
        e.setBuyerAddress3(s(e.getBuyerAddress3()));
        e.setShippingAddress1(s(e.getShippingAddress1()));
        e.setShippingAddress2(s(e.getShippingAddress2()));
        e.setShippingAddress3(s(e.getShippingAddress3()));
        e.setWebsite(s(e.getWebsite()));
        e.setGmailId(s(e.getGmailId()));
        e.setContact(s(e.getContact()));
        e.setCin(s(e.getCin()));
        e.setGst(s(e.getGst()));
        e.setPan(s(e.getPan()));
        e.setTan(s(e.getTan()));
        e.setBankAccHolder(s(e.getBankAccHolder()));
        e.setBankName(s(e.getBankName()));
        e.setAccNumber(s(e.getAccNumber()));
        e.setMicrCode(s(e.getMicrCode()));
        e.setIfscCode(s(e.getIfscCode()));
        e.setCSwiftCode(s(e.getCSwiftCode()));
        e.setCBankCode(s(e.getCBankCode()));
        e.setCIban(s(e.getCIban()));
        e.setCBankBranchAdd1(s(e.getCBankBranchAdd1()));
        e.setCBankBranchAdd2(s(e.getCBankBranchAdd2()));
        e.setCBankBranchAdd3(s(e.getCBankBranchAdd3()));
        if (e.getCustType() == null) e.setCustType("1");
        if (e.getStatus() == null) e.setStatus(1);
        e.setCGstLutNo(s(e.getCGstLutNo()));
    }
}
