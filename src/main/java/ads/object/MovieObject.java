package ads.object;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MovieObject {
	private int movie_id;
	private String movie_title;
	private String movie_description;
	private int movie_duration;
	private String movie_trailer_url;
	private String movie_created_at;
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public String getMovie_title() {
		return movie_title;
	}
	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}
	public String getMovie_description() {
		return movie_description;
	}
	public void setMovie_description(String movie_description) {
		this.movie_description = movie_description;
	}
	public int getMovie_duration() {
		return movie_duration;
	}
	public void setMovie_duration(int movie_duration) {
		this.movie_duration = movie_duration;
	}
	public String getMovie_trailer_url() {
		return movie_trailer_url;
	}
	public void setMovie_trailer_url(String movie_trailer_url) {
		this.movie_trailer_url = movie_trailer_url;
	}
	public String getMovie_created_at() {
		return movie_created_at;
	}
	public void setMovie_created_at(String movie_created_at) {
		this.movie_created_at = movie_created_at;
	}

}
