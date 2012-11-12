package nl.codebasesoftware.produx.controller;

import nl.codebasesoftware.produx.domain.Region;
import nl.codebasesoftware.produx.rest.JaxbList;
import nl.codebasesoftware.produx.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * User: rvanloen
 * Date: 11-11-12
 * Time: 13:38
 */
@Controller
public class RestRegionController {

    private RegionService regionService;

    @Autowired
    public RestRegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @RequestMapping(value = "/regions/{input}")
    public @ResponseBody JaxbList<Region> getRegionsBySubstring(@PathVariable("input") String input, Model model){
        List<Region> regions = regionService.findRegionsBySubstring(input);
        JaxbList<Region> jaxbRegions = new JaxbList<Region>(regions);
        return jaxbRegions;
    }
}
