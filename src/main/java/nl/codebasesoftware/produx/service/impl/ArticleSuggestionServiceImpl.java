package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.ArticleDao;
import nl.codebasesoftware.produx.dao.ArticleSuggestionDao;
import nl.codebasesoftware.produx.dao.UserProfileDao;
import nl.codebasesoftware.produx.domain.Article;
import nl.codebasesoftware.produx.domain.ArticleSuggestion;
import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.domain.dto.entity.ArticleSuggestionEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.UserProfileEntityDTO;
import nl.codebasesoftware.produx.formdata.ArticleSuggestionFormData;
import nl.codebasesoftware.produx.service.ArticleSuggestionService;
import nl.codebasesoftware.produx.service.support.CurrentUser;
import nl.codebasesoftware.produx.util.collection.EntityCollectionConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * User: rvanloen
 * Date: 12-4-13
 * Time: 22:30
 */
@Service
public class ArticleSuggestionServiceImpl implements ArticleSuggestionService {

    private ArticleSuggestionDao articleSuggestionDao;
    private ArticleDao articleDao;
    private UserProfileDao userProfileDao;

    @Autowired
    public ArticleSuggestionServiceImpl(ArticleSuggestionDao articleSuggestionDao, ArticleDao articleDao, UserProfileDao userProfileDao) {
        this.articleSuggestionDao = articleSuggestionDao;
        this.articleDao = articleDao;
        this.userProfileDao = userProfileDao;
    }

    @Override
    @Transactional(readOnly = true)
    public ArticleSuggestionEntityDTO findById(long id) {
        ArticleSuggestion suggestion = articleSuggestionDao.find(id);
        if(suggestion == null){
            return null;
        }
        return suggestion.toDTO();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArticleSuggestionEntityDTO> findForUser(UserProfileEntityDTO user) {
        return new EntityCollectionConverter<ArticleSuggestion, ArticleSuggestionEntityDTO>().convert(articleSuggestionDao.findForUser(user.getId()));
    }

    @Override
    @Transactional(readOnly = false)
    public ArticleSuggestion insert(ArticleSuggestionFormData formData) {

        UserProfile currentUser = userProfileDao.find(CurrentUser.get().getId());

        ArticleSuggestion suggestion = null;

        if (formData.getId() != null) {
            suggestion = articleSuggestionDao.find(formData.getId());
        } else {
            suggestion = new ArticleSuggestion();
        }

        // If there is an article id in the form data, than set the article on the suggestion
        if (formData.getArticleId() != null) {
            Article article = articleDao.find(formData.getArticleId());
            suggestion.setArticle(article);
        }

        suggestion.setSuggester(currentUser);
        suggestion.setSuggestedTitle(formData.getTitle());
        suggestion.setSuggestionText(formData.getDescription());
        suggestion.setCreated(Calendar.getInstance());
        suggestion.setEmail(formData.getEmail());


        articleSuggestionDao.persist(suggestion);

        return suggestion;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArticleSuggestionEntityDTO> findAllDateSortedDesc() {
        return new EntityCollectionConverter<ArticleSuggestion, ArticleSuggestionEntityDTO>().convert(articleSuggestionDao.findAllDateSortedDesc());
    }

    @Override
    @Transactional(readOnly = true)
    public ArticleSuggestion findFull(Long id) {
        return articleSuggestionDao.findFull(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void setApproval(Long id, boolean approved) {
        ArticleSuggestion articleSuggestion = articleSuggestionDao.find(id);
        articleSuggestion.setApproved(approved);
    }
}
