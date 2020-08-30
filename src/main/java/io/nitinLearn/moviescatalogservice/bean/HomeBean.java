package io.nitinLearn.moviescatalogservice.bean;

public class HomeBean {
	private String name;
	private String desc;
	private int rating;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public HomeBean(String name, String desc, int rating) {
		super();
		this.name = name;
		this.desc = desc;
		this.rating = rating;
	}

	public HomeBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "HomeBean [name=" + name + ", desc=" + desc + ", rating=" + rating + "]";
	}

}
