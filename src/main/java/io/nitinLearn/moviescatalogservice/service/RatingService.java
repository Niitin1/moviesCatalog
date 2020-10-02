package io.nitinLearn.moviescatalogservice.service;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.nitinLearn.moviescatalogservice.bean.Rating;
import io.nitinLearn.moviescatalogservice.bean.UserRating;

@Service
public class RatingService {
	
//	@Autowired
//	RestTemplate restTemplate;
	
	@Value("${ratingInfoUrl}")
	String ratingUrl;

	private final Logger log = Logger.getLogger(this.getClass());
	
	
	@HystrixCommand(fallbackMethod = "getFallBackUserRating")
	public UserRating getUserRating(String userId) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(ratingUrl + userId, UserRating.class);
		
	}
	
	public UserRating getFallBackUserRating(String userId) {
		// TODO Auto-generated method stub
		log.info("call fall back method in rating service ");
		UserRating userRating =new UserRating();
		userRating.setUserRating(Arrays.asList(new Rating("40", 4) ));
		return userRating;
	}


}
