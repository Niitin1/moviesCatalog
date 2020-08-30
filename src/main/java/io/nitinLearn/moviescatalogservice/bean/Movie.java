package io.nitinLearn.moviescatalogservice.bean;

public class Movie {
	private String name;
	private String movieId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	@Override
	public String toString() {
		return "HomeBean [name=" + name + ", movieId=" + movieId + "]";
	}
	public Movie(String name, String movieId) {
		super();
		this.name = name;
		this.movieId = movieId;
	}
	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
