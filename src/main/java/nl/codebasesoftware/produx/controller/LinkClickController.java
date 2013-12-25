package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.dto.entity.ClickEntityDTO;
import nl.codebasesoftware.produx.service.CourseService;
import nl.codebasesoftware.produx.service.LinkClickService;
import nl.codebasesoftware.produx.service.RegisterLinkClickService;
import nl.codebasesoftware.produx.service.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

/**
 * User: rvanloen
 * Date: 13-12-13
 * Time: 13:06
 */
@Controller
public class LinkClickController {

    private RegisterLinkClickService linkClickService;

    @Autowired
    public LinkClickController(RegisterLinkClickService linkClickService){
        this.linkClickService = linkClickService;
    }


    @RequestMapping(value = "/externallinkclicked", method = RequestMethod.POST)
    public @ResponseBody
    String registerClick(HttpServletRequest request){
        linkClickService.registerClickOrIgnore(request);
        return "";
    }
}
