package jobs4u.base.entitymanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.representations.dto.DTOable;
import jobs4u.base.entitymanagement.dto.EntityDTO;

public class Entity implements AggregateRoot<CustomerCode>, DTOable<EntityDTO> {
    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public int compareTo(CustomerCode other) {
        return AggregateRoot.super.compareTo(other);
    }

    @Override
    public CustomerCode identity() {
        return null;
    }

    @Override
    public boolean hasIdentity(CustomerCode id) {
        return AggregateRoot.super.hasIdentity(id);
    }

    @Override
    public EntityDTO toDTO() {
        return null;
    }
}
