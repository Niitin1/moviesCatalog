package io.nitinLearn.moviescatalogservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import io.nitinLearn.moviescatalogservice.bean.HomeBean;
import io.nitinLearn.moviescatalogservice.bean.Rating;

@Service
public class HomeService {
	
	private final Logger log = Logger.getLogger(this.getClass());

	public List<HomeBean> getCatalog(String userId) {
		// TODO Auto-generated method stub
		log.info("service has been called "+userId);
		
		
		
		List<Rating> ratings = new ArrayList<Rating>();
		ratings.add(new Rating("1234",4));
		ratings.add(new Rating("5678", 4));
		return ratings.stream().map(rating -> new HomeBean("Transformer", "Test" , 4)).collect(Collectors.toList());
		
		/*
		 * HomeBean hb = new HomeBean("Transformer", "Test" , 4); List<HomeBean> hbs =
		 * new ArrayList<HomeBean>(); hbs.add(hb); return hbs;
		 */	
		}

}
