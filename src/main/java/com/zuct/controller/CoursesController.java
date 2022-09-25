package com.zuct.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.annotation.PropertySource;

import com.zuct.controller.base.CoursesBaseController;

@RestController
@PropertySource("classpath:${configfile.path}/Zuct.properties")
@RequestMapping(value="${endpoint.api}", headers = "Accept=application/json")
public class CoursesController extends CoursesBaseController {

	//OVERRIDE HERE YOUR CUSTOM CONTROLLER

}
