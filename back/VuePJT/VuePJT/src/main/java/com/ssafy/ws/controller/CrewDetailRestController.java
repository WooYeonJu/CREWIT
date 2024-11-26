package com.ssafy.ws.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ws.model.dto.CrewDetailBoard;
import com.ssafy.ws.model.service.CrewDetailBoardService;

@RestController
@RequestMapping("/crewDetailapi")
@CrossOrigin("*")
public class CrewDetailRestController {
	@Autowired
	CrewDetailBoardService crewDetailBoardService;

	@GetMapping("/crewDetail")
	public ResponseEntity<?> selectAll() {
		try {
			List<CrewDetailBoard> crewDetailBoard = crewDetailBoardService.selectAll();
			if (crewDetailBoard != null && crewDetailBoard.size() > 0) {
//				System.out.println("seleceAll: "+crewDetailBoard.get(0).getBoardTitle());
				return new ResponseEntity<List<CrewDetailBoard>>(crewDetailBoard, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/crewDetail/crewId/{id}")
	public ResponseEntity<?> selectByCrewId(@PathVariable int id) {
		try {
			List<CrewDetailBoard> crewDetailBoard = crewDetailBoardService.selectByCrewId(id);
			if (crewDetailBoard != null) {
				return new ResponseEntity<List<CrewDetailBoard>>(crewDetailBoard, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	} 
	
	@GetMapping("/crewDetail/boardId/{id}")
	public ResponseEntity<?> selectByBoardId(@PathVariable int id) {
		try {
			CrewDetailBoard crewDetailBoard = crewDetailBoardService.selectByBoardId(id);
			if (crewDetailBoard != null) {
				return new ResponseEntity<CrewDetailBoard>(crewDetailBoard, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	} 

	
	@GetMapping("/crewDetail/search")
	public ResponseEntity<?> searchByTitle(@RequestParam String title, @RequestParam int crewId) {
		Map<String, Object> forTitle = new HashMap<>();
		forTitle.put("title", title);
		forTitle.put("crewId", crewId);
//		System.out.println(title);
		try {
			List<CrewDetailBoard> crewDetailBoard = crewDetailBoardService.searchByTitle(forTitle);
//			System.out.println(videos.size());
			if (crewDetailBoard != null) {
				return new ResponseEntity<List<CrewDetailBoard>>(crewDetailBoard, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	// 게시글 등록
	@PostMapping("/crewDetail")
	public ResponseEntity<String> write(@RequestBody CrewDetailBoard crewDetailBoard) {
		System.out.println(crewDetailBoard);
		System.out.println(crewDetailBoard.getBoardTitle());
		if (crewDetailBoardService.writeCrewBoard(crewDetailBoard)) {
			return ResponseEntity.status(HttpStatus.CREATED).body("crew Board Detail added successfully");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add crew board Detail");
	}

	// 게시글 수정
	@PutMapping("/crewDetail/update")
	public ResponseEntity<String> update(@RequestBody CrewDetailBoard crewDetailBoard) {
		System.out.println(crewDetailBoard.getBoardTitle()); // 출력 안됨
		if (crewDetailBoardService.modifyCrewBoard(crewDetailBoard))
			return ResponseEntity.ok("Board Detail updated successfully");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update crew board Detail");
	}

	@DeleteMapping("/crewDetail/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		if (crewDetailBoardService.removeCrewBoard(id)) {
			return ResponseEntity.status(HttpStatus.OK).body("crew board detail deleted successfully");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete crew Board detail");
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("sorry: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
