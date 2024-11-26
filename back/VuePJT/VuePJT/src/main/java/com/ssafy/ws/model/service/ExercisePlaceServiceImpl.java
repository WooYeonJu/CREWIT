package com.ssafy.ws.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ws.model.dao.ExercisePlaceDao;
import com.ssafy.ws.model.dto.ExercisePlace;
import com.ssafy.ws.model.dto.SearchCondition;
import com.ssafy.ws.util.JwtUtil;

@Service
public class ExercisePlaceServiceImpl implements ExercisePlaceService{
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	ExercisePlaceDao exercisePlaceDao;

	@Override
	public int save(ExercisePlace exercisePlace) {
		// TODO Auto-generated method stub
		return exercisePlaceDao.save(exercisePlace);
	}

	@Override
	public List<ExercisePlace> select() {
		// TODO Auto-generated method stub
		return exercisePlaceDao.select();
	}

	@Override
	public List<ExercisePlace> searchByCondition(SearchCondition condition) {
		// TODO Auto-generated method stub
		return exercisePlaceDao.searchByCondition(condition);
	}

	@Override
	public ExercisePlace searchById(int id) {
		// TODO Auto-generated method stub
		return exercisePlaceDao.searchById(id);
	}
	
	

}
