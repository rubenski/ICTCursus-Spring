package nl.codebasesoftware.produx.service;

import nl.codebasesoftware.produx.domain.UserProfile;

import java.util.List;

/**
 * User: rvanloen
 * Date: 16-7-12
 * Time: 23:00
 */
public interface UserProfileService {
    UserProfile findByEmail(String email);
    UserProfile findById(Long id);
    List<UserProfile> findAll();
    void removeUserProfile(String username);
    String generateRandomPassword();
    void update(UserProfile profile);
}
