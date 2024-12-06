package ads.basic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public interface Basic {

	boolean add(PreparedStatement pre);

	boolean edit(PreparedStatement pre);

	boolean del(PreparedStatement pre);

	ArrayList<ResultSet> gets(String multiSelect);
	
	ResultSet get(String sql, int value);
	ResultSet get(String sql, String name);
	
	void releaseConnection();
}
