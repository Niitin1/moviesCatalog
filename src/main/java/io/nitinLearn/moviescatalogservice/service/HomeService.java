package io.nitinLearn.moviescatalogservice.service;

import java.util.ArrayList;
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

import io.nitinLearn.moviescatalogservice.bean.HomeBean;
import io.nitinLearn.moviescatalogservice.bean.Movie;
import io.nitinLearn.moviescatalogservice.bean.Rating;
import io.nitinLearn.moviescatalogservice.bean.UserRating;

@Service
public class HomeService {

	/*@Autowired
	RestTemplate restTemplate;*/

	@Autowired
	private WebClient.Builder webClientBuilder;

	@Value("${movieInfoUrl}")
	String url;
	
	@Value("${ratingInfoUrl}")
	String ratingUrl;

	private final Logger log = Logger.getLogger(this.getClass());

	public List<HomeBean> getCatalog(String userId) {
		// TODO Auto-generated method stub
		log.info("service has been called " + userId);
		RestTemplate restTemplate = new RestTemplate();
		

		
		/*  List<Rating> ratings = new ArrayList<Rating>(); ratings.add(new
		  Rating("1234", 4)); ratings.add(new Rating("5678", 3));*/
		 
		UserRating userRatings = restTemplate.getForObject(ratingUrl+userId, UserRating.class);
		List<Rating> ratings = userRatings.getUserRating();

		return ratings.stream().map(rating -> {
			Movie movie = restTemplate.getForObject(url + rating.getMovieId(), Movie.class);
			log.info("movie "+movie.toString());
			// reactive programming(asynchronous programming)
			// Movie movie =
			// webClientBuilder.build().get().uri(url).retrieve().bodyToMono(Movie.class).block();
			return new HomeBean(movie.getName(), movie.getMovieId(), rating.getRating());
		}).collect(Collectors.toList());
	}

}
