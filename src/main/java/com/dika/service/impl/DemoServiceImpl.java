package com.dika.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dika.dao.DemoDao;
import com.dika.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {
    
    @Autowired
    private DemoDao demoDao;

    @Override
    public void testFunction() {
        demoDao.test();
    }

}
