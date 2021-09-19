package com.matsuura.FaceMusicBatch.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.matsuura.FaceMusicBatch.model.CSVData;
import com.matsuura.FaceMusicBatch.util.CommonUtil;
import com.matsuura.FaceMusicBatch.util.Constants;

public class DownloadCSVClient {

	/** 変数 **/

	private static DownloadCSVClient client;

	/** シングルトン **/

	public static DownloadCSVClient getInstance() {

		if (client == null) {
			client = new DownloadCSVClient();
		}

		return client;
	}

	/** 外部URLからCSVファイルをダウンロードする関数です **/

	public ArrayList<CSVData> downloadCSVFile() throws IOException {

		//ファイル読み込みで使用する３つのクラス
		FileInputStream fi = null;
		InputStreamReader is = null;
		BufferedReader br = null;

		ArrayList<CSVData> list = new ArrayList<CSVData>();

		try {

			// 読み込みファイルのインスタンス生成
			// ファイル名を指定する
			fi = new FileInputStream("resources/regional-jp-daily-2021-09-11.csv");
			is = new InputStreamReader(fi);
			br = new BufferedReader(is);

			//読み込み行
			String line;

			//読み込み行数の管理
			int i = 0;

			//1行ずつ読み込みを行う
			while ((line = br.readLine()) != null) {

				if (i >= 2) {

					// カンマで分割した内容を配列に格納する
					String[] data = line.split(",");

					// ランキング
					String position = data[0];

					// 楽曲ID
					String id = "";

					if ((data[1].length() == 5 || data[1].length() == 6) && CommonUtil.isAllNumber(data[1])) {

						int index = Constants.SPOTIDY_ID_PATH.length();
						id = data[2].substring(index);

					} else if ((data[2].length() == 5 || data[2].length() == 6) && CommonUtil.isAllNumber(data[2])) {

						int index = Constants.SPOTIDY_ID_PATH.length();
						id = data[3].substring(index);

					} else if ((data[3].length() == 5 || data[3].length() == 6) && CommonUtil.isAllNumber(data[3])) {

						int index = Constants.SPOTIDY_ID_PATH.length();
						id = data[4].substring(index);

					} else if ((data[4].length() == 5 || data[4].length() == 6) && CommonUtil.isAllNumber(data[4])) {

						int index = Constants.SPOTIDY_ID_PATH.length();
						id = data[5].substring(index);
					}

					CSVData csvData = new CSVData();
					csvData.setPosition(position);
					csvData.setId(id);

					System.out.println("=======================");
					System.out.println(position + " 位");

					System.out.println("id " + id);

					list.add(csvData);

				}

				//行数のインクリメント
				i++;

			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return list;

	}

}
