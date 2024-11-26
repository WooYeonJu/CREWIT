package com.ssafy.ws.model.dto;

public class MapComment {

	private int id;
	private int placeId;
	private String writer;
	private String content;
	private int rate;

	public MapComment(int id, int placeId, String writer, String content, int rate) {
		super();
		this.id = id;
		this.placeId = placeId;
		this.writer = writer;
		this.content = content;
		this.rate = rate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlaceId() {
		return placeId;
	}

	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

}