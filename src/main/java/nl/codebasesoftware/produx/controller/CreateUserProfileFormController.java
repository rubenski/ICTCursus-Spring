package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.UserProfile;
import nl.codebasesoftware.produx.service.UserProfileService;
import nl.codebasesoftware.produx.validator.CreateUserProfileFormValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;


/**
 * User: rvanloen
 * Date: 12-8-12
 * Time: 15:09
 */
@Controller
@RequestMapping("/up")
public class CreateUserProfileFormController {

    Logger LOG = Logger.getLogger(CreateUserProfileFormController.class);

    private CreateUserProfileFormValidator validator;
    private UserProfileService userProfileService;

    @Autowired
    public CreateUserProfileFormController(UserProfileService userProfileService, CreateUserProfileFormValidator validator) {
        this.userProfileService = userProfileService;
        this.validator = validator;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(Model model) {
        /*
        UserProfile userProfile = null;
        if (userProfileId != null) {
            userProfile = userProfileService.findById(userProfileId);
        } else {
            userProfile = new UserProfile();
        }
        */

        UserProfile userProfile = new UserProfile();

        model.addAttribute("userProfile", userProfile);
        model.addAttribute("mainContent", "forms/createUserProfile");
        return "main";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitForm(@ModelAttribute("userProfile") UserProfile userProfile, BindingResult result, SessionStatus status, Model model) {
        validator.validate(userProfile, result);

        model.addAttribute("mainContent", "forms/createUserProfile");
        if (result.hasErrors()) {
            return "main";
        } else {
            userProfileService.update(userProfile);
            status.setComplete();
            return "main";
        }
    }


}
