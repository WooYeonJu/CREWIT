

package com.ssafy.ws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ws.model.dto.Exercise;
import com.ssafy.ws.model.service.ExerciseService;

@RestController
@RequestMapping("/exerciseapi")
@CrossOrigin("*")
public class ExerciseRestController {
	
	@Autowired
	ExerciseService exerciseService;
	
	@GetMapping("/exercise")
	public ResponseEntity<?> selectAll(){
		List<Exercise> result = exerciseService.selectAll();
		if(result != null && result.size() >0) {
			return new ResponseEntity<List<Exercise>>(result, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}