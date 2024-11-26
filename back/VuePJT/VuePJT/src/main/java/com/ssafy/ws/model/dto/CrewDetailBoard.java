package com.ssafy.ws.model.dto;

public class CrewDetailBoard {
	private int crewId;
	private int boardId;
	private String boardTitle;
	private String boardWriter;
	private String content;

//	public CrewDetailBoard() {
//		// TODO Auto-generated constructor stub
//	}

	public CrewDetailBoard(int crewId, int boardId, String boardTitle, String boardWriter, String content) {
		super();
		this.crewId = crewId;
		this.boardId = boardId;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.content = content;
	}

	public int getCrewId() {
		return crewId;
	}

	public void setCrewId(int crewId) {
		this.crewId = crewId;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "CrewDetailBoard [crewId=" + crewId + ", boardId=" + boardId + ", boardTitle=" + boardTitle
				+ ", boardWriter=" + boardWriter + ", content=" + content + "]";
	}

	

}
