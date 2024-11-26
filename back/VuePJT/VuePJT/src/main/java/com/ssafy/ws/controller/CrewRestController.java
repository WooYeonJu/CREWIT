package com.ssafy.ws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ws.model.dto.Crew;
import com.ssafy.ws.model.dto.SearchCondition;
import com.ssafy.ws.model.service.CrewService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api-crew")
@CrossOrigin("*")
public class CrewRestController {
	
	@Autowired
	private CrewService crewService;

//	@Autowired
//	public BoardRestController(BoardService boardService) {
//		this.boardService = boardService;
//	}
	
	// 크루 리스트 (검색) 조회
	@GetMapping("/crew/list")
	public ResponseEntity<?> list(@ModelAttribute SearchCondition condition){
		System.out.println("ddd");
		
//		System.out.println(condition);
		
//		if(condition.get("key"))
		
		List<Crew> list = crewService.searchCrew(condition);
		if (list==null || list.size()==0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		System.out.println(list.get(0));
		return new ResponseEntity<List<Crew>>(list,HttpStatus.OK);
	}
	 
	// 게시글 상세 보기
	@GetMapping("/crew/{id}")
	public ResponseEntity<Crew> detail(@PathVariable int id){
//		System.out.println(id);
		Crew crew = crewService.readCrew(id);
		if (crew != null) {
			return new ResponseEntity<Crew>(crew,HttpStatus.OK);
		}
		return new ResponseEntity<Crew>(HttpStatus.NOT_FOUND);
	}
	
	// 게시글 등록 
	@PostMapping("/crew")
	public ResponseEntity<String> write(@RequestBody Crew crew){
//		crew.setCrewCurrMember(1);
//		System.out.println(crew);
//		System.out.println(crew.getCrewName());
		if (crewService.writeCrew(crew)) {
			return ResponseEntity.status(HttpStatus.CREATED).body("crew Board added successfully");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add crew board");
	}
	
	// 게시글 수정
	@PutMapping("/crew")
	public ResponseEntity<String> update(@RequestBody Crew crew){
//		System.out.println(crew.getCrewMembers()); // 출력 안됨
		if(crewService.modifyCrew(crew)) 
			return ResponseEntity.ok("Board updated successfully");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update crew board");
	}
	
	@DeleteMapping("/crew/{id}")
	public ResponseEntity<String> delete(@PathVariable int id){
		if (crewService.removeCrew(id)) {
			return ResponseEntity.status(HttpStatus.OK).body("crew board deleted successfully");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete crew Board");
	}
}
