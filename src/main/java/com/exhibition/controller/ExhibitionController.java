package com.exhibition.controller;

import com.exhibition.service.IExService;
import com.exhibition.service.impl.ExServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exhibition")
public class ExhibitionController {
    @Autowired
    private IExService exService = new ExServiceImpl();
}
