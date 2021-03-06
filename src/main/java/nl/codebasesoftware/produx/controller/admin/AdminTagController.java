package nl.codebasesoftware.produx.controller.admin;

import nl.codebasesoftware.produx.domain.Tag;
import nl.codebasesoftware.produx.domain.dto.entity.TagEntityDTO;
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
public class AdminTagController {

    private TagService tagService;

    @Autowired
    public AdminTagController(TagService tagService) {
        this.tagService = tagService;
    }

    @RequestMapping(value = "/tag/search/{tagName}")
    public
    @ResponseBody
    List<TagEntityDTO> search(@PathVariable String tagName) {
        List<Tag> tags = tagService.findBySubString(tagName);
        List<TagEntityDTO> tagEntityDTOs = new ArrayList<TagEntityDTO>();
        for (Tag tag : tags) {
            tagEntityDTOs.add(tag.toDTO());
        }
        return tagEntityDTOs;
    }

    @RequestMapping(value = "/tag/byname/{tagName}")
    public
    @ResponseBody
    TagEntityDTO byName(@PathVariable String tagName) {
        Tag tag = tagService.findByName(tagName);
        return tag == null ? null : tag.toDTO();
    }

}
