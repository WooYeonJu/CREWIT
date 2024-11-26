package com.ssafy.ws.model.dao;

import java.util.List;

import com.ssafy.ws.model.dto.Exercise;

public interface ExerciseDao {

	public List<Exercise> selectAll();
}
