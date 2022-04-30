package com.estockmarket.company.query.api.handlers;

import com.estockmarket.company.core.events.CompanyRegisteredEvent;
import com.estockmarket.company.core.events.CompanyRemovedEvent;
import com.estockmarket.company.core.events.CompanyUpdatedEvent;
import com.estockmarket.company.query.api.repositories.CompanyRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ProcessingGroup("company-group")
public class CompanyEventHandlerImpl implements CompanyEventHandler{

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyEventHandlerImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    @EventHandler
    @Override
    public void on(CompanyRegisteredEvent event) {

        companyRepository.save(event.getCompany());
    }

    @EventHandler
    @Override
    public void on(CompanyUpdatedEvent event) {

        companyRepository.save(event.getCompany());
    }

    @EventHandler
    @Override
    public void on(CompanyRemovedEvent event) {
        companyRepository.deleteById(event.getId());
    }
}
