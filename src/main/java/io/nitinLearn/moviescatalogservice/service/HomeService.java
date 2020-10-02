package io.nitinLearn.moviescatalogservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.hibernate.query.criteria.internal.expression.function.ParameterizedFunctionExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.nitinLearn.moviescatalogservice.bean.HomeBean;
import io.nitinLearn.moviescatalogservice.bean.Movie;
import io.nitinLearn.moviescatalogservice.bean.Rating;
import io.nitinLearn.moviescatalogservice.bean.UserRating;

@Service
public class HomeService {

//	@Autowired
//	RestTemplate restTemplate;

	@Autowired
	private WebClient.Builder webClientBuilder;

	@Autowired
	RatingService ratingService;

	@Value("${movieInfoUrl}")
	String url;

	private final Logger log = Logger.getLogger(this.getClass());

	public List<HomeBean> getCatalog(String userId) {
		// TODO Auto-generated method stub
		log.info("service has been called " + userId);

		UserRating userRatings = ratingService.getUserRating(userId);

		List<Rating> ratings = userRatings.getUserRating();

		return ratings.stream().map(rating -> {
			return getCatalogItem(rating);

		}).collect(Collectors.toList());
	}

	@HystrixCommand(fallbackMethod = "getFallBackCatalogItem")
	public HomeBean getCatalogItem(Rating rating) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();
		Movie movie = restTemplate.getForObject(url + rating.getMovieId(), Movie.class);
		log.info("movie " + movie.toString());
		return new HomeBean(movie.getName(), movie.getMovieId(), rating.getRating());

	}

	public List<HomeBean> getFallBackCatalogItem(String userId) {
		// TODO Auto-generated method stub
		log.info("service has been called , fallback method " + userId);
		return Arrays.asList(new HomeBean("no movie", "", 0));

	}

}
