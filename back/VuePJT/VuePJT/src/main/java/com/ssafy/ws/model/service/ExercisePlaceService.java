package com.ssafy.ws.model.service;

import java.util.List;

import com.ssafy.ws.model.dto.ExercisePlace;
import com.ssafy.ws.model.dto.SearchCondition;

public interface ExercisePlaceService {
	
	// 공공데이터포털에 있는 데이터 불러와서 DB에 저장
	public int save(ExercisePlace exercisePlace);

	// DB에 저장한 데이터 불러오기
	public List<ExercisePlace> select();
	
	public List<ExercisePlace> searchByCondition(SearchCondition condition);

	public ExercisePlace searchById(int id);
	
}
