package ads;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPoolImpl implements ConnectionPool {
	private String driver;
	private String username;
	private String password;
	private String url;

	private Stack<Connection> pool;
	
	//Singleton design pattern
	private static ConnectionPool cp = null;
 
	private ConnectionPoolImpl() {
		// Xác định trình điều khiển
		this.driver = "com.mysql.cj.jdbc.Driver";

		// Xac dinh duong dan chay MySQL
		this.url = "jdbc:mysql://localhost:3306/moviebooking_data?allowMultiQueries=true";

		// Xac dinh tai khoan lam viec
		this.username = "moviebooking_gr11";
		this.password = "@123$%65";

		// Nap trinh dieu khien
		try {
			Class.forName(this.driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Khoi tao bo nho luu tru doi tuong ket noi
		this.pool = new Stack<>();

	}

	@Override
	public Connection getConnection(String objectName) throws SQLException {
		// TODO Auto-generated method stub
		if (this.pool.isEmpty()) {
			// Khoi tao ket noi moi
			System.out.print(objectName + " have created a new Connection.");
			return DriverManager.getConnection(this.url, this.username, this.password);
		} else {
			// Lay ket noi da duoc luu tru
			System.out.print(objectName + " have popped the Connection.");
			return this.pool.pop();
		}
	}

	@Override
	public void releaseConnection(Connection con, String objectName) throws SQLException {
		// TODO Auto-generated method stub
		// Thu hoi lai ket noi
		System.out.print(objectName + " have pushed the Connection.");
		this.pool.push(con);
	}
	
	public static ConnectionPool getInstance() {
		if ( cp == null) {
			synchronized ( ConnectionPoolImpl.class) {
				if(cp == null) {
					cp = new ConnectionPoolImpl();
				}
			}
		}
		
		return cp;
	}

}
