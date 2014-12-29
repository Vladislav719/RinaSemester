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

    @RequestMapping(value = "workInfo")
    public String infoWork(ModelMap modelMap, @RequestParam(required = false) String work) {
        modelMap = new Worker().getWorkInfo(modelMap,work);
        return "second";
    }

    @RequestMapping(value = "departInfo")
    public String getDepartInfo(ModelMap modelMap, @RequestParam(required = false) String department) {
        modelMap = new Worker().getInfoAboutWorkerInDepartment(modelMap,department);
        return "second";
    }

//    @RequestMapping(value = "sFirst")
//    public String
}
