package jobs4u.base.entitymanagement.application;
import eapli.framework.infrastructure.authz.domain.model.Username;
import jobs4u.base.entitymanagement.dto.EntityDTO;
import jobs4u.base.entitymanagement.domain.Entity;
import jobs4u.base.entitymanagement.repository.EntityRepository;
import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.jobopeningmanagement.application.EntityListDTOService;

import java.util.List;


public class EntityManagementService {

    private final EntityListDTOService service = new EntityListDTOService();

    private final EntityRepository entityRepository = PersistenceContext
            .repositories().entities();


    public List<EntityDTO> getAssignedCustomerCodesList(Username username) {
        List<Entity> customersList = entityRepository.getCustomersByUsername(username);
        return service.convertToDTO(customersList);
    }
}
