package nl.codebasesoftware.produx.dao;

import nl.codebasesoftware.produx.domain.UserProfile;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface UserProfileDao extends GenericDao<UserProfile> {

	public UserProfile findByEmail(String email) throws EntityNotFoundException;

    List<UserProfile> findByCompany(long companyId);
}
