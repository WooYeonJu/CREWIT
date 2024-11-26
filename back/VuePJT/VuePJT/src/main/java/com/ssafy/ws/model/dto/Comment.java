package com.ssafy.ws.model.dto;


public class Comment {

    
    private int id;
    private int crewId;
    private int boardId;
    private String writer;
    private String content;
    
    public Comment() {
        // TODO Auto-generated constructor stub
    }

    public Comment(int id, int crewId, int boardId, String writer, String content) {
        super();
        this.id = id;
        this.crewId = crewId;
        this.boardId = boardId;
        this.writer = writer;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Comment [id=" + id + ", crewId=" + crewId + ", boardId=" + boardId + ", writer=" + writer + ", content="
                + content + "]";
    }
    
    
    
}