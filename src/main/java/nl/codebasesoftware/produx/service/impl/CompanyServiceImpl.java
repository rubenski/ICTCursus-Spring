package nl.codebasesoftware.produx.service.impl;

import nl.codebasesoftware.produx.dao.CompanyDao;
import nl.codebasesoftware.produx.dao.CourseDao;
import nl.codebasesoftware.produx.domain.Company;
import nl.codebasesoftware.produx.domain.Course;
import nl.codebasesoftware.produx.domain.dto.entity.ArticleEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.CourseEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.UserProfileEntityDTO;
import nl.codebasesoftware.produx.formdata.BindableCompany;
import nl.codebasesoftware.produx.formdata.BindableFileUpload;
import nl.codebasesoftware.produx.formdata.CompanySettingsFormData;
import nl.codebasesoftware.produx.service.CompanyService;
import nl.codebasesoftware.produx.service.SolrService;
import nl.codebasesoftware.produx.service.support.CurrentUser;
import nl.codebasesoftware.produx.util.ImageUtil;
import nl.codebasesoftware.produx.properties.Properties;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * User: rvanloen
 * Date: 6-11-12
 * Time: 2:36
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyDao companyDao;
    private Properties properties;
    private SolrService solrService;



    @Autowired
    public CompanyServiceImpl(CompanyDao companyDao, Properties properties, SolrService solrService) {
        this.properties = properties;
        this.solrService = solrService;
        this.companyDao = companyDao;
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyEntityDTO findByUserProfile(UserProfileEntityDTO userProfile) {
        return userProfile.getCompany();
    }

    @Override
    @Transactional(readOnly = true)
    public Company findByArticle(ArticleEntityDTO article) {
        return companyDao.findByArticle(article);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(BindableCompany bindableCompany) {
        CompanyEntityDTO companyDTO = getCurrentlyLoggedInCompany();
        Company company = companyDao.find(companyDTO.getId());
        bindableCompanyToCompany(bindableCompany, company);
        solrService.addOrUpdate(asCourseEntities(company.getCourses()));
        companyDao.persist(company);
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyEntityDTO getCurrentlyLoggedInCompany() {
        UserProfileEntityDTO user = CurrentUser.get();
        if (user == null) {
            return null;
        }
        CompanyEntityDTO company = user.getCompany();
        if(company == null){
            return null;
        }
        return companyDao.find(company.getId()).toDTO();
    }

    @Override
    @Transactional(readOnly = true)
    public CompanySettingsFormData getCompanySettingsForCurrentCompany() {
        CompanyEntityDTO company = getCurrentlyLoggedInCompany();
        CompanySettingsFormData dto = new CompanySettingsFormData();
        dto.setCourseRequestEmailAddress(company.getCourseRequestEmailAddress());
        dto.setAllCoursesDeactivated(company.isAllCoursesDeactivated());
        dto.setBudgetTriggerAmount(company.getBudgetTriggerAmount());
        dto.setCompanyId(company.getId());
        return dto;
    }

    @Override
    @Transactional(readOnly = false)
    public void saveSettings(CompanySettingsFormData settingsDto) {
        CompanyEntityDTO companyDTO = getCurrentlyLoggedInCompany();
        Company company = companyDao.find(companyDTO.getId());
        company.setAllCoursesDeactivated(settingsDto.isAllCoursesDeactivated());
        company.setBudgetTriggerAmount(settingsDto.getBudgetTriggerAmount());
        company.setCourseRequestEmailAddress(settingsDto.getCourseRequestEmailAddress());
    }

    private void bindableCompanyToCompany(BindableCompany bindableCompany, Company company) {
        try {
            BeanUtils.copyProperties(company, bindableCompany);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    @Override
    @Transactional(readOnly = true)
    public CompanyEntityDTO findById(Long companyId) {
        return companyDao.find(companyId).toDTO();
    }


    @Transactional(readOnly = true)
    public CompanyEntityDTO findByPrefix(String prefix){
        Company company = companyDao.findByPrefix(prefix);
        if(company != null){
            return company.toDTO();
        }
        return null;
    }


    @Override
    @Transactional(readOnly = false)
    public void updateLogo(BindableFileUpload bindableFileUpload) {

        String normalLengthString = properties.getProperty("logo.normal.widthheight");
        String smallLengthString = properties.getProperty("logo.small.widthheight");
        int normalLength = Integer.parseInt(normalLengthString);
        int smallLength = Integer.parseInt(smallLengthString);

        byte[] normalImageBytes = null;
        byte[] smallImageBytes = null;

        CommonsMultipartFile fileData = bindableFileUpload.getFileData();
        try {
            normalImageBytes = ImageUtil.resizeWithImgScalr(fileData.getInputStream(), normalLength);
            smallImageBytes = ImageUtil.resizeWithImgScalr(fileData.getInputStream(), smallLength);
        } catch (IOException e) {
            e.printStackTrace();
        }

        CompanyEntityDTO companyEntityDTO = getCurrentlyLoggedInCompany();
        Company company = companyDao.find(companyEntityDTO.getId());
        company.setNormalLogo(normalImageBytes);
        company.setSmallLogo(smallImageBytes);
    }

    @Override
    @Transactional(readOnly = false)
    public void removeLogo(long companyId) {
        Company company = companyDao.find(companyId);
        company.setNormalLogo(null);
        company.setSmallLogo(null);
    }

    public List<CourseEntityDTO> asCourseEntities(Collection<Course> courses) {
        List<CourseEntityDTO> courseEntities = new ArrayList<>();
        for (Course course : courses) {
            courseEntities.add(course.toDTO());
        }
        return courseEntities;
    }
}



