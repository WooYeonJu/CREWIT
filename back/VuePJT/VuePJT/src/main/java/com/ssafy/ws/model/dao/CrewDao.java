package com.ssafy.ws.model.dao;

import java.util.List;

import com.ssafy.ws.model.dto.Crew;
import com.ssafy.ws.model.dto.SearchCondition;

public interface CrewDao {

	// 크루 리스트 전체 출력 및 검색 기능
	public List<Crew> search(SearchCondition searchCondition);

	// 게시글 상세조회
	public Crew selectOne(int id);

	// 게시글 등록
	public int insertCrew(Crew crew);

	// 게시글 삭제
	public int deleteCrew(int id);

	// 게시글 수정
	public int updateCrew(Crew crew);
}
