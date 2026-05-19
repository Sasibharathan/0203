package com.MosIC.MosIC_Office.customers.service;

import com.MosIC.MosIC_Office.customers.dto.CustomerDTO;

import java.util.List;

public interface CustomerServices {

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO getCustomerById(Long id);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);

    void deleteCustomer(Long id);
}