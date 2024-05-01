package jobs4u.base.customermanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;
import jobs4u.base.customermanagement.dto.CustomerDTO;
import jobs4u.base.jobopeningmanagement.domain.Address;

@Entity
@Table(name = "T_CUSTOMER")
public class Customer implements AggregateRoot<CustomerCode>, DTOable<CustomerDTO> {

    private static final long serialVersionUID = 1L;
    @Version
    private Long version;
    @Id
    private CustomerCode code;
    private CompanyName name;
    private Address address;
    @OneToOne
    private SystemUser customerManager;
    @ManyToOne
    private SystemUser customerUser;

    public Customer() {
        //for ORM
    }

    public Customer(CompanyName companyName, Address address, CustomerCode customerCode, SystemUser customerManager, SystemUser customerUser) {
        Preconditions.noneNull(companyName, address, customerCode, customerManager, customerUser);

        this.name = companyName;
        this.address = address;
        this.code = customerCode;
        this.customerManager = customerManager;
        this.customerUser = customerUser;
    }


    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public int compareTo(CustomerCode other) {
        return AggregateRoot.super.compareTo(other);
    }

    @Override
    public CustomerCode identity() {
        return this.code;
    }

    @Override
    public boolean hasIdentity(CustomerCode id) {
        return AggregateRoot.super.hasIdentity(id);
    }

    @Override
    public CustomerDTO toDTO() {
        return new CustomerDTO(name, code);
    }

    public CustomerCode customerCode() {
        return this.identity();
    }

}
