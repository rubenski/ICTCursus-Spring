package nl.codebasesoftware.produx.domain;

import nl.codebasesoftware.produx.domain.dto.entity.DomainEntityDTO;

public interface DomainEntity {
    Long getId();

    <T extends DomainEntityDTO> T toDTO();
}
