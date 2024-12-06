package ads.movie;

import java.sql.ResultSet;
import java.util.ArrayList;

import ads.object.MovieObject;

public interface movie {
	
	boolean addMovie(MovieObject item);
	boolean editMovie(MovieObject item);
	boolean delMovie(MovieObject item);
	
	ArrayList<ResultSet> getMovies(String multiSelect);
	ArrayList<ResultSet> getMovies(MovieObject similar, int at, byte total);
	ResultSet getMovie(int id);
	ResultSet getMovie(String movie_title);
	
}
