package com.matsuura.FaceMusicBatch.model;

public class MusicData {

	/** 変数 **/

	// 楽曲ID
	private String id;
	// 曲名
	private String track;
	// アーティスト名
	private String artist;
	// imageURL
	private String url;
	// danceablitity
	private double danceability;
	// energy
	private double energy;
	// loudness
	private double loudness;
	// speechiness
	private double speechiness;
	// acousticness
	private double acousticness;
	// instrumentalness
	private double instrumentalness;
	/// liveness
	private double liveness;
	// valence
	private double valence;
	// tempo
	private double tempo;
	// duration_time
	private int durationTime;
	// release_day
	private String releaseDay;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTrack() {
		return track;
	}

	public void setTrack(String track) {
		this.track = track;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public double getDanceability() {
		return danceability;
	}

	public void setDanceability(double danceability) {
		this.danceability = danceability;
	}

	public double getEnergy() {
		return energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}

	public double getLoudness() {
		return loudness;
	}

	public void setLoudness(double loudness) {
		this.loudness = loudness;
	}

	public double getSpeechiness() {
		return speechiness;
	}

	public void setSpeechiness(double speechiness) {
		this.speechiness = speechiness;
	}

	public double getAcousticness() {
		return acousticness;
	}

	public void setAcousticness(double acousticness) {
		this.acousticness = acousticness;
	}

	public double getInstrumentalness() {
		return instrumentalness;
	}

	public void setInstrumentalness(double instrumentalness) {
		this.instrumentalness = instrumentalness;
	}

	public double getLiveness() {
		return liveness;
	}

	public void setLiveness(double liveness) {
		this.liveness = liveness;
	}

	public double getValence() {
		return valence;
	}

	public void setValence(double valence) {
		this.valence = valence;
	}

	public double getTempo() {
		return tempo;
	}

	public void setTempo(double tempo) {
		this.tempo = tempo;
	}

	public int getDurationTime() {
		return durationTime;
	}

	public void setDurationTime(int durationTime) {
		this.durationTime = durationTime;
	}

	public String getReleaseDay() {
		return releaseDay;
	}

	public void setReleaseDay(String releaseDay) {
		this.releaseDay = releaseDay;
	}


}
