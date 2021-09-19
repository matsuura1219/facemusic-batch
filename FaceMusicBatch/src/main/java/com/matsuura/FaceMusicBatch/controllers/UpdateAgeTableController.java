package com.matsuura.FaceMusicBatch.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import com.matsuura.FaceMusicBatch.interfaces.GetAccessTokenListener;
import com.matsuura.FaceMusicBatch.interfaces.GetEmotionAndDurationListener;
import com.matsuura.FaceMusicBatch.interfaces.GetOtherDataListener;
import com.matsuura.FaceMusicBatch.interfaces.GetPlayListListener;
import com.matsuura.FaceMusicBatch.model.EmotionAndDurationDataModel;
import com.matsuura.FaceMusicBatch.model.MusicData;
import com.matsuura.FaceMusicBatch.model.OtherDataModel;
import com.matsuura.FaceMusicBatch.service.SpotifyApiClient;
import com.matsuura.FaceMusicBatch.service.UpdateDbData;

/** Age Detection用を更新するためのコントローラーです **/

public class UpdateAgeTableController
		implements GetAccessTokenListener, GetPlayListListener, GetEmotionAndDurationListener, GetOtherDataListener {

	/** 変数 **/
	private String accessToken;

	private ArrayList<String> playList;

	private int api_call_count = 0;

	// EmotionAndDurationAPIのリスト
	private ArrayList<EmotionAndDurationDataModel> emotionAndDurationLists;

	// OtherAPIのリスト
	private ArrayList<OtherDataModel> otherLists;

	// 楽曲データ
	private ArrayList<MusicData> dataLists;

	/** 定数 **/

	// 各年代のプレイリスト
	private final String GENERATION_1980_PLAY_LIST = "37i9dQZF1DXdX26YqusBdd";
	private final String GENERATION_1990_PLAY_LIST = "37i9dQZF1DWWNbVDKoV50L";
	private final String GENERATION_2000_PLAY_LIST = "37i9dQZF1DX4OQAiraQRL0";
	private final String GENERATION_2010_PLAY_LIST = "66k8O1RBpVeI9B3bm90aTB";

	private final String HIT_PLAYLIST_URL = "5hT5SaHgbsfA6uvfpPUwAb";

	//private final String[] idList = {GENERATION_1980_PLAY_LIST, GENERATION_1990_PLAY_LIST, GENERATION_2000_PLAY_LIST,GENERATION_2010_PLAY_LIST };
	private final String[] idList = {HIT_PLAYLIST_URL};
	/** コンストラクタ **/

	public UpdateAgeTableController() {

	}

	/** コントローラーを起動する関数です **/

	public void excute() {

		// Spotofy アクセストークンを取得します
		SpotifyApiClient.getInstance().getAccessToken(this);

		System.out.println("==========");
		System.out.println("アクセストークン取得開始");
		System.out.println("==========");

	}

	/** アクセストークン取得時に実行されるコールバック関数です
	 * @param accessToken String アクセストークン
	 */
	public void onSuccess(String accessToken) {

		this.accessToken = accessToken;

		for (int i = 0; i < idList.length; i++) {

			System.out.println("==========");
			System.out.println("アクセストークン取得成功");
			System.out.println("==========");

			SpotifyApiClient.getInstance().getMusicList(idList[i], accessToken, this);

		}

	}

	public void onSuccess (ArrayList<String> id) {

		api_call_count++;

		if (playList == null) {
			playList = new ArrayList<String>();

		}

		for (int i=0; i< id.size(); i++) {
			playList.add(id.get(i));
		}

		if (api_call_count == idList.length) {

			System.out.println("==========");
			System.out.println("GetEmotionAndDuration取得開始");
			System.out.println("==========");

			for (int i = 0; i < playList.size(); i++) {

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}

				SpotifyApiClient.getInstance().getEmotionAndDurationData(playList.get(i), this.accessToken, this);

			}

		}
	}

	public void onSuccess(EmotionAndDurationDataModel data) {

		if (emotionAndDurationLists == null) {
			emotionAndDurationLists = new ArrayList<EmotionAndDurationDataModel>();
		}

		emotionAndDurationLists.add(data);

		if (emotionAndDurationLists.size() == playList.size()) {

			System.out.println("==========");
			System.out.println("GetEmotionAndDuration取得完了");
			System.out.println("==========");

			System.out.println("==========");
			System.out.println("GetOtherData取得開始");
			System.out.println("==========");

			for (int i = 0; i < playList.size(); i++) {

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}

				SpotifyApiClient.getInstance().getOtherMusicData(emotionAndDurationLists.get(i).getId(),
						this.accessToken, this);
			}

		}

	}

	public void onSuccess(OtherDataModel data) {

		if (otherLists == null) {
			otherLists = new ArrayList<OtherDataModel>();
		}

		otherLists.add(data);

		if (otherLists.size() == playList.size()) {

			System.out.println("==========");
			System.out.println("GetOtherData取得完了");
			System.out.println("==========");

			if (dataLists == null) {
				dataLists = new ArrayList<MusicData>();
			}

			for (int i = 0; i < playList.size(); i++) {

				MusicData musicData = new MusicData();
				musicData.setAcousticness(emotionAndDurationLists.get(i).getAcousticness());
				musicData.setArtist(otherLists.get(i).getArtist());
				musicData.setDanceability(emotionAndDurationLists.get(i).getDanceability());
				musicData.setDurationTime(emotionAndDurationLists.get(i).getDurationTime());
				musicData.setEnergy(emotionAndDurationLists.get(i).getEnergy());
				musicData.setId(emotionAndDurationLists.get(i).getId());
				musicData.setInstrumentalness(emotionAndDurationLists.get(i).getInstrumentalness());
				musicData.setLiveness(emotionAndDurationLists.get(i).getLiveness());
				musicData.setLoudness(emotionAndDurationLists.get(i).getLoudness());
				musicData.setReleaseDay(otherLists.get(i).getReleaseDay());
				musicData.setSpeechiness(emotionAndDurationLists.get(i).getSpeechiness());
				musicData.setTempo(emotionAndDurationLists.get(i).getTempo());
				musicData.setTrack(otherLists.get(i).getTrack());
				musicData.setUrl(otherLists.get(i).getUrl());
				musicData.setValence(emotionAndDurationLists.get(i).getValence());

				dataLists.add(musicData);

			}

			UpdateDbData controller = new UpdateDbData(dataLists);

			try {

				controller.deleteData();
				controller.excute();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

		}

	}

	public void onFailure(String errorCode) {

	}

}
