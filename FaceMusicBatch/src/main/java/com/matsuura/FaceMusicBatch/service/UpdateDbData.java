package com.matsuura.FaceMusicBatch.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.matsuura.FaceMusicBatch.model.MusicData;
import com.matsuura.FaceMusicBatch.util.Constants;

public class UpdateDbData {

	/** 変数 **/

	// SQL文
	private final String INSERT_SQL = "insert into test_db (id, image_url, artist_name, music_name, "
			+ "danceablity, energy, loudness, speechiness, acousticness, instrumentalness, liveness, valence, tempo, "
			+ "duration_time, release_day) "
			+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private final String DELETE_SQL = "truncate table face_music_db.test_db";

	//接続オブジェクト
	private Connection con;

	// プリコンパイルされたSQL文を扱うオブジェクト
	PreparedStatement ps = null;

	// 楽曲リスト
	private ArrayList<MusicData> dataLists;



	/** コンストラクタ **/
	public UpdateDbData (ArrayList<MusicData> lists) {

		this.dataLists = lists;

		for (int i=0; i<dataLists.size(); i++) {

			System.out.println("id:" + dataLists.get(i).getId());
			System.out.println("artists:" + dataLists.get(i).getArtist());
			System.out.println("url:" + dataLists.get(i).getUrl());
			System.out.println("track:" + dataLists.get(i).getTrack());

		}

	}

	/**
	 * DB接続用の関数です
	 */
	private Connection getConnect () throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.jdbc.Driver");

		// 接続先URL
		String url = "jdbc:mysql://" + Constants.DB_SERVER_URL + ":3306/" + Constants.DB_NAME;

		// Connectionの設定をします
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


	/** DB内のデータを一括削除する関数です **/
	public void deleteData () throws SQLException {

		System.out.println("削除開始");

		try {

			// Connectionを取得します
			con = getConnect();

			// コミットモードをfalse（手動更新）にします
			con.setAutoCommit(false);


			// SQL文の設定を行います
			ps = con.prepareStatement(DELETE_SQL);

			// SQL文を実行します
			ps.executeUpdate();

			// コミットします
			con.commit();

			System.out.println("削除完了");


		} catch (Exception e) {

			e.printStackTrace();

			// ロールバックします
			con.rollback();
			System.out.println("削除失敗");

		}

	}


	/** DBのデータを更新する関数です **/
	public void excute () throws SQLException {

		System.out.println("更新開始");

		try {

			// Connectionを取得します
			con = getConnect();

			// コミットモードをfalse（手動更新）にします
			con.setAutoCommit(false);


			// SQL文の設定を行います
			ps = con.prepareStatement(INSERT_SQL);

			for (int i=0; i<dataLists.size(); i++) {

				System.out.println("id:" + dataLists.get(i).getId());
				System.out.println("releaseDay:" + dataLists.get(i).getReleaseDay());

				// パラメータを設定します

				// 楽曲ID
				ps.setString(1, dataLists.get(i).getId());
				// 画像URL
				ps.setString(2, dataLists.get(i).getUrl());
				// 歌手名
				ps.setString(3, dataLists.get(i).getArtist());
				// トラック名
				ps.setString(4, dataLists.get(i).getTrack());
				// danceability
				ps.setDouble(5, dataLists.get(i).getDanceability());
				// energy
				ps.setDouble(6, dataLists.get(i).getEnergy());
				// loudness
				ps.setDouble(7, dataLists.get(i).getLoudness());
				// speechiness
				ps.setDouble(8, dataLists.get(i).getSpeechiness());
				// acousticness
				ps.setDouble(9, dataLists.get(i).getAcousticness());
				// instrumentalness
				ps.setDouble(10, dataLists.get(i).getInstrumentalness());
				// liveness
				ps.setDouble(11, dataLists.get(i).getLiveness());
				// valence
				ps.setDouble(12, dataLists.get(i).getValence());
				// tempo
				ps.setDouble(13, dataLists.get(i).getTempo());
				// duration_time
				ps.setInt(14, dataLists.get(i).getDurationTime());
				// release_day
				ps.setString(15, dataLists.get(i).getReleaseDay());

				// SQL文を実行します
				ps.executeUpdate();

			}


			// コミットします
			con.commit();

			System.out.println("COMMIT完了");


		} catch (Exception e) {

			e.printStackTrace();

			// ロールバックします
			con.rollback();
			System.out.println("COMMIT失敗");

		}
	}




}
