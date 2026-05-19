package com.MosIC.MosIC_Office.customers_party.service;

import com.MosIC.MosIC_Office.customers.entity.CustomerEntity;
import com.MosIC.MosIC_Office.customers.repository.CustomerRepository;
import com.MosIC.MosIC_Office.customers_party.dto.PartyDTO;
import com.MosIC.MosIC_Office.customers_party.entity.PartyEntity;
import com.MosIC.MosIC_Office.customers_party.mapper.PartyMapper;
import com.MosIC.MosIC_Office.customers_party.repository.PartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartyServiceImp implements PartyService {

    private final PartyRepository    partyRepository;
    private final CustomerRepository customerRepository;  // needed to resolve the FK

    // ── Helper: fetch CustomerEntity or throw ─────────────────────────────────
    private CustomerEntity findCustomer(Long customerId) {
        if (customerId == null) {
            throw new RuntimeException("Each party contact must belong to exactly one customer/company.");
        }

        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException(
                        "Customer (company/org) not found with id: " + customerId));
    }

    @Override
    public PartyDTO createParty(PartyDTO partyDTO) {
        CustomerEntity customer = findCustomer(partyDTO.getCustomerId());
        PartyEntity entity = PartyMapper.mapToEntity(partyDTO, customer);
        return PartyMapper.mapToDTO(partyRepository.save(entity));
    }

    @Override
    public PartyDTO getPartyById(Long id) {
        PartyEntity entity = partyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Party contact not found with id: " + id));
        return PartyMapper.mapToDTO(entity);
    }

    @Override
    public List<PartyDTO> getAllParties() {
        return partyRepository.findAll()
                .stream()
                .map(PartyMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PartyDTO> getPartiesByCustomerId(Long customerId) {
        findCustomer(customerId); // validate customer exists
        return partyRepository.findByCustomer_Id(customerId)
                .stream()
                .map(PartyMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PartyDTO updateParty(Long id, PartyDTO partyDTO) {
        PartyEntity existing = partyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Party contact not found with id: " + id));

        if (partyDTO.getCustomerId() != null &&
                !partyDTO.getCustomerId().equals(existing.getCustomer().getId())) {
            existing.setCustomer(findCustomer(partyDTO.getCustomerId()));
        }

        existing.setPartyName(partyDTO.getPartyName());
        existing.setPartyPhoneno(partyDTO.getPartyPhoneno());
        existing.setPartyEmail(partyDTO.getPartyEmail());
        existing.setStatus(partyDTO.getStatus());

        return PartyMapper.mapToDTO(partyRepository.save(existing));
    }

    @Override
    public void deleteParty(Long id) {
        PartyEntity existing = partyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Party contact not found with id: " + id));
        partyRepository.delete(existing);
    }
}
