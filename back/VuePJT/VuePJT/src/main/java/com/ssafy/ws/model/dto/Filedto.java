package com.ssafy.ws.model.dto;

import org.springframework.beans.factory.annotation.Value;

public class Filedto {
	
	private String userId;
	@Value("C:/Users/SSAFY/Desktop/YJGit/finalPJT/final/finalfinal/front/vuePJTFront/src/assests")
	private String fileName;
	
	private String filePath; 
	
	public Filedto() {
		// TODO Auto-generated constructor stub
	}
	


	public Filedto(String userId, String fileName, String filePath) {
		super();
		this.userId = userId;
		this.fileName = fileName;
		this.filePath = filePath;
	}



	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
	
	@Override
	public String toString() {
		return "Filedto [userId=" + userId + ", fileName=" + fileName + "]";
	}
	

}
