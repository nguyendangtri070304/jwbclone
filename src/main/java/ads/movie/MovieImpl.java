package ads.movie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import ads.object.MovieObject;
import ads.basic.*;

public class MovieImpl extends BasicImpl implements movie {

	public MovieImpl() {
		super("movie");
	}

	@Override
	public boolean addMovie(MovieObject item) {
		// TODO Auto-generated method stub
		if (this.isExisting(item)) {
			return false;
		}
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tblmovies(");
		sql.append("movie_title, movie_description, movie_duration, movie_trailer_url, movie_created_at) ");
		sql.append("VALUES(?,?,?,?,?)");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());

			pre.setString(1, item.getMovie_title());
			pre.setString(2, item.getMovie_description());
			pre.setInt(3, item.getMovie_duration());
			pre.setString(4, item.getMovie_trailer_url());
			pre.setString(5, item.getMovie_created_at());

			return this.add(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return false;
	}

	private boolean isExisting(MovieObject item) {
		boolean flag = false;

		String sql = "SELECT movie_id FROM tblmovies WHERE movie_title ='" + item.getMovie_title() + "'";
		ResultSet rs = this.get(sql, 0);
		if (rs != null) {
			try {
				if (rs.next()) {
					flag = true;
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return flag;

	}

	@Override
	public boolean editMovie(MovieObject item) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tblmovies ");
		sql.append("SET movie_title=?, movie_description=?, movie_duration=?, movie_trailer_url=?, movie_created_at=? ");
		sql.append("WHERE movie_id = ?");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());

			pre.setString(1, item.getMovie_title());
			pre.setString(2, item.getMovie_description());
			pre.setInt(3, item.getMovie_duration());
			pre.setString(4, item.getMovie_trailer_url());
			pre.setString(5, item.getMovie_created_at());

			pre.setInt(6, item.getMovie_id());

			return this.edit(pre);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return false;
	}

	@Override
	public boolean delMovie(MovieObject item) {
		// TODO Auto-generated method stub

		return false;
	}

	private boolean isEmpty(MovieObject item) {
		boolean flag = true;

		StringBuilder sql = new StringBuilder();

		ArrayList<ResultSet> res = this.gets(sql.toString());
		for (ResultSet rs : res) {
			try {
				if (rs != null && rs.next()) {
					flag = false;
					break;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public ArrayList<ResultSet> getMovies(String multiSelect) {
		// TODO Auto-generated method stub

		if (multiSelect != null && !" ".equalsIgnoreCase(multiSelect)) {
			return this.gets(multiSelect);
		} else {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM tblmovies ");
			sql.append("");
			sql.append("ORDER BY movie_id DESC ");

			return this.gets(sql.toString());
		}
	}

	@Override
	public ArrayList<ResultSet> getMovies(MovieObject similar, int at, byte total) {
		// TODO Auto-generated method stub

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tblmovies ");
		sql.append("");
		sql.append("ORDER BY movie_id DESC ");
		// sql.append("LIMIT 10").append(at).append(", ").append(total).append(";");
		sql.append("LIMIT ").append(at).append(", ").append(total).append(";");

		// Dem so luong nguoi su dung trong he thong
		sql.append("SELECT COUNT(movie_id) AS total FROM tblmovies;");

		return this.gets(sql.toString());
	}

	@Override
	public ResultSet getMovie(int id) {
		String sql = "SELECT * FROM tblmovies WHERE movie_id =?";

		return this.get(sql, id);
	}

	@Override
	public ResultSet getMovie(String movie_title) {
		String sql = "SELECT * FROM tblmovies WHERE movie_title LIKE ?";

		return this.get(sql, "%" + movie_title + "%");
	}

	public static void main(String[] args) {
		movie u = new MovieImpl();


	}
}
