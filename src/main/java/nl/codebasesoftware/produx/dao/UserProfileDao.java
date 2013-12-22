package nl.codebasesoftware.produx.dao;

import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;
import nl.codebasesoftware.produx.domain.optionlists.RoleName;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface UserProfileDao extends GenericDao<UserProfile> {

    public UserProfile findByEmail(String email) throws EntityNotFoundException;

    List<UserProfile> findOthersInCompany(long companyId, long exludeProfileId);

    UserProfile findAuthorByArticle(long articleId);

    UserProfile findAuthorByPage(long pageId);

    UserProfile findCourseLockingUser(long courseId);

    UserProfile findWithCompany(Long id);

    List<UserProfile> findByRole(RoleName role);

    List<UserProfile> findForCompany(CompanyEntityDTO company);
}
