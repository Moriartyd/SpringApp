package ru.galeev.springapp.service;

import org.springframework.stereotype.Service;
import ru.galeev.springapp.persistence.repository.ContractRequestRepository;
import ru.galeev.springapp.persistence.repository.ContractorRepository;

@Service
public class ContractorService {

    private final ContractorRepository contractorRepository;
    private final ContractRequestRepository contractRequestRepository;

    public ContractorService(ContractorRepository contractorRepository, ContractRequestRepository contractRequestRepository) {
        this.contractorRepository = contractorRepository;
        this.contractRequestRepository = contractRequestRepository;
    }


}
