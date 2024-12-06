package ads.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ads.ConnectionPool;
import ads.ConnectionPoolImpl;

public class BasicImpl implements Basic {

	// Doi tuong lam viec voi Basic
	private String objectName;
	// Bo quan li ket noi duoc chia se
	private ConnectionPool cp = ConnectionPoolImpl.getInstance();
	// Ket noi cua rieng Basic su dung
	protected Connection con;

	public BasicImpl(String objectName) {
		// Xac dinh doi tuong lam viec
		this.objectName = objectName;

		// Xin ket noi
		try {
			this.con = this.cp.getConnection(this.objectName);

			// Kiem tra che do thuc thi cua ket noi
			if (this.con.getAutoCommit()) {
				this.con.setAutoCommit(false); // Huy che do thuc thi tu dong
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean exe(PreparedStatement pre) {
		if (pre != null) {
			// Lay so luong ban ghi duoc tac dong
			try {
				int results = pre.executeUpdate();
				if (results == 0) {
					this.con.rollback();
					return false;
				}

				// Xac lap thuc thi sau cung
				this.con.commit();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				// Tro lai trang thai an toan cua ket noi
				try {
					this.con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		return false;
	}

	@Override
	public boolean add(PreparedStatement pre) {
		// TODO Auto-generated method stub
		return this.exe(pre);
	}

	@Override
	public boolean edit(PreparedStatement pre) {
		// TODO Auto-generated method stub
		return this.exe(pre);
	}

	@Override
	public boolean del(PreparedStatement pre) {
		// TODO Auto-generated method stub
		return this.exe(pre);
	}

	@Override
	public ArrayList<ResultSet> gets(String multiSelect) {
		// TODO Auto-generated method stub
		ArrayList<ResultSet> res = new ArrayList<>();

		try {
			PreparedStatement stmt = this.con.prepareStatement(multiSelect);
			boolean results = stmt.execute();
			do {
				if (results) {
					res.add(stmt.getResultSet());
				}
				results = stmt.getMoreResults(PreparedStatement.KEEP_CURRENT_RESULT);
				// Giu ResultSet hien tai de lam
			} while (results);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultSet get(String sql, int value) {
		// TODO Auto-generated method stub
		//Bien dich cau lenh
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			if(value > 0) {
				pre.setInt(1, value);
			}
			
			return pre.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultSet get(String sql, String name) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setString(1, name);

			return pre.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void releaseConnection() {
		// TODO Auto-generated method stub
		try {
			// Trả lại kết nối cho ConnectionPool
			if (this.con != null) {
				this.cp.releaseConnection(this.con, this.objectName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
