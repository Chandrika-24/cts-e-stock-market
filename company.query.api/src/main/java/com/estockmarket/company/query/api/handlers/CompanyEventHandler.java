package com.estockmarket.company.query.api.handlers;

import com.estockmarket.company.core.events.CompanyRegisteredEvent;
import com.estockmarket.company.core.events.CompanyRemovedEvent;
import com.estockmarket.company.core.events.CompanyUpdatedEvent;

public interface CompanyEventHandler {
    void on(CompanyRegisteredEvent event);
    void on(CompanyRemovedEvent event);
    void on(CompanyUpdatedEvent event);
}
