package com.matsuura.FaceMusicBatch.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** DBの操作を行う関数をまとめたクラスです **/

public class DBUtil {

	/** 変数 **/

	//接続オブジェクト
	private Connection con;


	/**
	 * DB接続用の関数です
	 */
	public Connection getConnect () throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.jdbc.Driver");

		//接続先URL
		String url = "jdbc:mysql://" + Constants.DB_SERVER_URL + ":3306/music_metadata";

		con = DriverManager.getConnection(url, Constants.DB_USER_ID, Constants.DB_PASSWORD);

		return con;

	}

	/**
	 * DB遮断用の関数です
	 */
	public void close () throws SQLException {

		if (con != null) {
			con.close();
		}
	}


}
