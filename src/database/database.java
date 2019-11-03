package database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import database.poleInfo;

public class database {
	static final String driver = "com.mysql.cj.jdbc.Driver";
	static final String db = "jdbc:mysql://149.28.49.148:3306/poles";
	static final String user = "root";
	static final String passwd = "";

	static Connection conn = null;
	static Statement stmt = null;

	public database() {
		try {
			Class.forName(driver);
			Enumeration<Driver> drivers = DriverManager.getDrivers();
			Driver d = null;
			while (drivers.hasMoreElements()) {
				d = drivers.nextElement();
				DriverManager.registerDriver(d);
			}
			System.out.println("Connecting to DB...");
			conn = DriverManager.getConnection(db, user, passwd);
			System.out.println("DB connected, initializing statement...");
			stmt = conn.createStatement();
			System.out.println("Statement initialized.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		conn.close();
		stmt.close();
		System.out.println("Connection closed.");
		super.finalize();
	}

	public List<poleInfo> getInfo() throws SQLException {
		List<poleInfo> result = new ArrayList<poleInfo>();
		String operation = "select * from javaPoles";
		ResultSet rs = stmt.executeQuery(operation);
		while (rs.next()) {
			poleInfo newinfo = new poleInfo();
			newinfo.id = rs.getInt("id");
			newinfo.name = rs.getString("name");
			newinfo.max_height = rs.getDouble("max_height");
			newinfo.min_height = rs.getDouble("min_height");
			newinfo.current_height = rs.getDouble("current_height");
			result.add(newinfo);
		}
		return result;
	}
}