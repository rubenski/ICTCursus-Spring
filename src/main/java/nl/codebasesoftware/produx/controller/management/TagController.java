package nl.codebasesoftware.produx.controller.management;

import nl.codebasesoftware.produx.domain.Tag;
import nl.codebasesoftware.produx.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: rvanloen
 * Date: 19-12-12
 * Time: 14:45
 */
@Controller
public class TagController {

    private TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @RequestMapping(value= "/tags/test", method= RequestMethod.GET)
    public @ResponseBody Tag testTag(@RequestParam(value="tagName", required = true) String tagName){
        Tag tag = tagService.findByName(tagName);
        if(tag == null){
            return null;
        }
        return tag;
    }
}
