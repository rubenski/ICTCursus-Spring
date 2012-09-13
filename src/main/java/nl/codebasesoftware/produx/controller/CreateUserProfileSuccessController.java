package nl.codebasesoftware.produx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * User: rvanloen
 * Date: 14-8-12
 * Time: 0:48
 */
@Controller
public class CreateUserProfileSuccessController {

    @RequestMapping(value = "/createUserProfileSuccess/{userProfileId}")
    public ModelAndView createUserProfileSuccess(@PathVariable("userProfileId") Integer userProfileId) {
        Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("userProfileId", userProfileId);
        return new ModelAndView("createUserProfileSuccess", "myModel", myModel);
    }

}
