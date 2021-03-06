package com.imooc.o2o.controller.superadmin;

import com.imooc.o2o.entity.Area;
import com.imooc.o2o.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/superadmin")
public class AreaController {
    Logger logger = LoggerFactory.getLogger(AreaController.class);
    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/listarea", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> listArea() {
        Map<String, Object> areaMap = new HashMap<String, Object>();
        List<Area> list = new ArrayList<Area>();
        logger.info("===start===");
        long startTime = System.currentTimeMillis();
        try {
            list = areaService.getArealist();
            areaMap.put("rows", list);
            areaMap.put("total", list.size());
        } catch (Exception e) {
            e.printStackTrace();
            areaMap.put("success", false);
            areaMap.put("errMsg", e.toString());
        }
        logger.error("test error");
        long endTime = System.currentTimeMillis();
        logger.debug("cosTime:[{}ms]",endTime-startTime);
        logger.info("===end===");
        return areaMap;
    }

}
