package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.dto.entity.ArticleEntityDTO;
import nl.codebasesoftware.produx.domain.dto.entity.CompanyEntityDTO;
import nl.codebasesoftware.produx.exception.ResourceNotFoundException;
import nl.codebasesoftware.produx.service.ArticleService;
import nl.codebasesoftware.produx.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: rvanloen
 * Date: 21-12-13
 * Time: 19:13
 */
@Controller
public class FileServingController {


    private CompanyService companyService;
    private ArticleService articleService;

    @Autowired
    public FileServingController(CompanyService companyService, ArticleService articleService) {
        this.companyService = companyService;
        this.articleService = articleService;
    }

    @RequestMapping(value = "/logo/{size}/{imageName}")
    public void getLogo(@PathVariable("imageName") String imageName, @PathVariable("size") String size, HttpServletResponse response) {

        response.setContentType("image/png");

        Long companyId = idFromImageName(imageName);
        CompanyEntityDTO company = companyService.findById(companyId);

        byte[] logo = null;

        if (company != null && size.equals("small")) {
            logo = company.getSmallLogo();
        } else if (company != null && size.equals("normal")) {
            logo = company.getNormalLogo();
        } else {
            throw new ResourceNotFoundException();
        }

        writeResponse(response, logo);
    }

    @RequestMapping(value = "/article/picture/{articleId}.png")
    public void getArticlePicture(@PathVariable("articleId") long articleId, HttpServletResponse response) {

        response.setContentType("image/png");

        ArticleEntityDTO article = articleService.findById(articleId);

        if(article == null){
            throw new ResourceNotFoundException();
        }

        writeResponse(response, article.getPicture());
    }

    private void writeResponse(HttpServletResponse response, byte[] file) {
        ServletOutputStream outputStream = null;

        if (file != null) {
            try {
                outputStream = response.getOutputStream();
                outputStream.write(file);
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Long idFromImageName(String imageName) {
        return Long.parseLong(imageName.split("-")[0]);
    }
}
