package com.dika.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dika.service.DemoService;


@RestController
public class DemoHandler {
    
    @Autowired
    private DemoService demoService;

    @RequestMapping(value ="/testhello",method = RequestMethod.GET)
    public String test123(@RequestParam(value = "PARAM", required = false) String param){
        System.out.println(param);
        return param;
    }
    
    @RequestMapping(value ="/test",method = RequestMethod.GET)
    public String testdemo(){
        demoService.testFunction();
        return "ok";
    }
    
}
