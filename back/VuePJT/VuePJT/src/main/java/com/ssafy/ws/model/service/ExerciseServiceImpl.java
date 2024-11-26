package com.ssafy.ws.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ws.model.dao.ExerciseDao;
import com.ssafy.ws.model.dto.Exercise;
import com.ssafy.ws.util.JwtUtil;

@Service
public class ExerciseServiceImpl implements ExerciseService{
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	ExerciseDao exerciseDao;

	@Override
	public List<Exercise> selectAll() {
		// TODO Auto-generated method stub
		return exerciseDao.selectAll();
	}

	
}
