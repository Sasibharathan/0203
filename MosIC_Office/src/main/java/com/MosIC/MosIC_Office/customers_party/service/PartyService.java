package com.MosIC.MosIC_Office.customers_party.service;

import java.util.List;
import com.MosIC.MosIC_Office.customers_party.dto.PartyDTO;

public interface PartyService {

    PartyDTO createParty(PartyDTO partyDTO);
    PartyDTO getPartyById(Long id);
    List<PartyDTO> getAllParties();
    List<PartyDTO> getPartiesByCustomerId(Long customerId);  // all contacts for a company
    PartyDTO updateParty(Long id, PartyDTO partyDTO);
    void deleteParty(Long id);
}