package com.ssafy.ws.model.service;

import java.util.List;

import com.ssafy.ws.model.dto.Crew;
import com.ssafy.ws.model.dto.SearchCondition;

public interface CrewService {

	// 크루 리스트 전체 출력 및 검색 기능
	public List<Crew> searchCrew(SearchCondition searchCondition);
		
	// 게시글 상세조회
	public Crew readCrew(int id);
	
	// 게시글 작성
	public boolean writeCrew(Crew crew);
	
	// 게시글 삭제
	public boolean removeCrew(int id);
	
	// 게시글 수정
	public boolean modifyCrew(Crew crew);
}

