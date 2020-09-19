package io.nitinLearn.moviescatalogservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.ribbon.proxy.annotation.Hystrix;

import io.nitinLearn.moviescatalogservice.bean.HomeBean;
import io.nitinLearn.moviescatalogservice.bean.Rating;
import io.nitinLearn.moviescatalogservice.service.HomeService;

@RestController
public class HomeController {
	
	@Autowired
	HomeService homeService;
	
	private final Logger log = Logger.getLogger(this.getClass());
	
	@GetMapping(value = "/")
	public String getTest() {
		log.info("ahgha ");
		return "movieCatalog1";
	}
	
	@GetMapping(value = "/getCatalog/{userId}")
	@Hystrix
	private @ResponseBody List<HomeBean> getCatalog(@PathVariable("userId") String userId){
		log.info("userIDa  "+userId);
		
		return homeService.getCatalog(userId);
	}
	
}
