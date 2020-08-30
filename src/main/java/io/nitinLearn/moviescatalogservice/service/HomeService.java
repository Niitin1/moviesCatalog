package io.nitinLearn.moviescatalogservice.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import io.nitinLearn.moviescatalogservice.bean.HomeBean;

@Service
public class HomeService {
	
	private final Logger log = Logger.getLogger(this.getClass());

	public List<HomeBean> getCatalog(String userId) {
		// TODO Auto-generated method stub
		log.info("service has been called "+userId);
		HomeBean hb = new HomeBean("Transformer", "Test" , 4);
		List<HomeBean> hbs =  new ArrayList<HomeBean>();
		hbs.add(hb);
		return hbs;
	}

}
