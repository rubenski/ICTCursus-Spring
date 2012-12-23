package nl.codebasesoftware.produx.controller.management;

import nl.codebasesoftware.produx.domain.Tag;
import nl.codebasesoftware.produx.domain.dto.TagDTO;
import nl.codebasesoftware.produx.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(value= "/tag/{tagName}")
    public @ResponseBody List<TagDTO> testTag(@PathVariable String tagName){
        List<Tag> tags = tagService.findBySubString(tagName);
        List<TagDTO> tagDTOs = new ArrayList<TagDTO>();
        for (Tag tag : tags) {
            tagDTOs.add(tag.toDTO());
        }
        return tagDTOs;
    }

}
