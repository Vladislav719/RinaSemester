package com.rina;

import com.rina.db.Worker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Владислав on 29.12.2014.
 */
@Controller
@RequestMapping(value = "/")
public class MainController {

    @RequestMapping(value = "info")
    public String infoAboutDepartment(ModelMap map, @RequestParam(required = false) String department) {
        map = new Worker().getDepartmentInfo(map,department);
        return "second";
    }

}
