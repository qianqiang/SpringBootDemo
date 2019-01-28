package com.dika.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dika.service.DemoService;


@RestController
@Api(value="demo",tags={"demo"})
public class DemoHandler {
    
    @Autowired
    private DemoService demoService;

    @ApiOperation(value = "测试接口", httpMethod = "GET", notes = "测试get方法")
    @RequestMapping(value ="/testhello",method = RequestMethod.GET)
    public String test123(@ApiParam(name="PARAM", value="hello xx", required=true)@RequestParam(value = "PARAM", required = false) String param){
        System.out.println(param);
        return param;
    }
    
    @RequestMapping(value ="/test",method = RequestMethod.GET)
    public String testdemo(){
        demoService.testFunction();
        return "ok";
    }
    
}
