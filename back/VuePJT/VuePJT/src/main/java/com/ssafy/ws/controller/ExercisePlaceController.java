package com.ssafy.ws.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ws.model.dto.ApiResponse;
import com.ssafy.ws.model.dto.ExercisePlace;
import com.ssafy.ws.model.dto.SearchCondition;
import com.ssafy.ws.model.service.ExercisePlaceService;

@RestController
@RequestMapping("/exerciseplaceapi")
@CrossOrigin("*")
public class ExercisePlaceController {

	@Autowired
	private ExercisePlaceService exercisePlaceService;

	
	@GetMapping("/api/load")
	public ApiResponse<?> loadJsonFromApi() throws FileNotFoundException {

		StringBuffer result = new StringBuffer();
		Scanner scanner = new Scanner(new File("res/API_KEY.txt"));

		String test = scanner.next();
//		System.out.println(test);

		
		try {
			URL url = new URL(
					"http://api.odcloud.kr/api/15061935/v1/uddi:85bd069d-4039-4d13-a73c-b33d9cbb9743?page=1&perPage=500&serviceKey="
							+ test);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

//			System.out.println("ee" + urlConnection);

			urlConnection.setRequestMethod("GET");
			urlConnection.setRequestProperty("Content-type", "application/json");

			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

			result.append(br.readLine());
			
//			System.out.println(result);

			JSONObject jsonObject = new JSONObject(result.toString());
			JSONArray jsonArray = (JSONArray) jsonObject.get("data");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = (JSONObject) jsonArray.get(i);
				String name = (String) object.get("업소명");
				String location = (String) object.get("소재지");
				String exercise = (String) object.get("업종");

//				System.out.println(name + " " + location + " " + exercise);
 
				exercisePlaceService.save(new ExercisePlace(name, location, exercise));
//				SaveAreaRequest
			}
			return ApiResponse.success("성공", null);

		} catch (Exception e) {
			return ApiResponse.error("실패", null);
		}

	}

	@GetMapping("/api/read")
	public ResponseEntity<?> select() {
		List<ExercisePlace> result = exercisePlaceService.select();
		if (result != null && result.size() > 0) {
			return new ResponseEntity<List<ExercisePlace>>(result, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/api/search")
	public ResponseEntity<?> searchByCondition(SearchCondition condition) {
		List<ExercisePlace> result = exercisePlaceService.searchByCondition(condition);
//		System.out.println(result.get(0));
		if (result != null && result.size() > 0) {
			return new ResponseEntity<List<ExercisePlace>>(result, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/api/search/{id}")
	public ResponseEntity<?> searchById(@PathVariable int id) {
		ExercisePlace result = exercisePlaceService.searchById(id);
//		System.out.println(result.get(0));
		if (result != null) {
			return new ResponseEntity<ExercisePlace>(result, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
