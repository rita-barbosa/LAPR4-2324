package jobs4u.base.entitymanagement.domain.events;

import eapli.framework.domain.events.DomainEvent;
import eapli.framework.domain.events.DomainEventBase;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import jobs4u.base.entitymanagement.domain.CompanyName;
import jobs4u.base.entitymanagement.domain.CustomerCode;
import jobs4u.base.jobopeningmanagement.domain.Address;

public class NewCustomerUserRegisteredEvent extends DomainEventBase implements DomainEvent {
    private static final long serialVersionUID = 1L;
    private CompanyName companyName;
    private Address address;
    private CustomerCode customerCode;
    private SystemUser customerManager;
    private SystemUser customerUser;


    public NewCustomerUserRegisteredEvent(CompanyName companyName, Address address,
                                          CustomerCode customerCode, SystemUser customerManager, SystemUser customerUser) {
        this.companyName = companyName;
        this.address = address;
        this.customerCode = customerCode;
        this.customerManager = customerManager;
        this.customerUser = customerUser;
    }

    public CustomerCode customerCode() {
        return this.customerCode;
    }

    public CompanyName companyName() {
        return this.companyName;
    }

    public Address address() {
        return this.address;
    }

    public SystemUser customerManager() {
        return this.customerManager;
    }

    public SystemUser customerUser() {
        return customerUser;
    }
}
