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

public class database {
	static final String driver = "com.mysql.cj.jdbc.Driver";
	static final String db = "jdbc:mysql://localhost/poles";
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
		String operation = "create table if not exists javaPoles (No int(8) unsigned primary key auto_increment, id varchar(32) not null, name varchar(128) not null, max_height double(8,2) not null, min_height double(8,2) not null, current_height double(8,2) not null)";
		stmt.execute(operation);
		operation = "select * from javaPoles";
		ResultSet rs = stmt.executeQuery(operation);
		while (rs.next()) {
			poleInfo newinfo = new poleInfo();
			newinfo.No = rs.getInt("No");
			newinfo.id = rs.getString("id");
			newinfo.name = rs.getString("name");
			newinfo.max_height = rs.getDouble("max_height");
			newinfo.min_height = rs.getDouble("min_height");
			newinfo.current_height = rs.getDouble("current_height");
			result.add(newinfo);
		}
		return result;
	}

	void SaveInfo(List<poleInfo> info) throws SQLException {
		stmt.execute("drop table javaPoles");
		stmt.execute(
				"create table if not exists javaPoles (No int(8) unsigned primary key auto_increment, id varchar(32) not null, name varchar(128) not null, max_height double(8,2) not null, min_height double(8,2) not null, current_height double(8,2) not null)");
		for (poleInfo i : info) {
			stmt.execute("insert into javaPoles values (" + i.No + "," + i.id + "," + i.name + "," + i.max_height + ","
					+ i.min_height + "," + i.current_height + ")");
		}
	}
}