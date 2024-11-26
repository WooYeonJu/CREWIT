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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ws.model.dto.Crew;
import com.ssafy.ws.model.dto.CrewMembers;
import com.ssafy.ws.model.service.CrewMembersService;

@RestController
@RequestMapping("/crewMembers")
@CrossOrigin("*")
public class CrewMembersController {
	
	@Autowired
	CrewMembersService crewMembersService;
	
	@GetMapping("/members")
	public ResponseEntity<?> selectAll(){
		List<CrewMembers> result = crewMembersService.selectAll();
		if(result != null && result.size() > 0) {
			return new ResponseEntity<List<CrewMembers>>(result, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/members/searchByCrew/{crewId}")
	public ResponseEntity<?> searchByCrewId(@PathVariable int crewId){
//		System.out.println(crewId);
		List<CrewMembers> result = crewMembersService.searchByCrewId(crewId);
		if(result != null || result.size() > 0) {
			return new ResponseEntity<List<CrewMembers>>(result, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/members/searchByUser/{userId}")
	public ResponseEntity<?> searchByUserId(@PathVariable String userId){
		List<CrewMembers> result = crewMembersService.searchByUserId(userId);
		if(result != null || result.size() > 0) {
			return new ResponseEntity<List<CrewMembers>>(result, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/members/searchByUserLeader/{userId}")
	public ResponseEntity<?> searchLeaderByUserId(@PathVariable String userId){
//		System.out.println(userId);
		List<Crew> result = crewMembersService.searchLeaderByUserId(userId);
//		System.out.println(result.get(0));
		if(result != null || result.size() > 0) {
			return new ResponseEntity<List<Crew>>(result, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/members/insertMember")
	public ResponseEntity<?> insertMember(@RequestBody CrewMembers crewMembers){
		
//		System.out.println(crewMembers.getCrewId());
//		System.out.println(crewMembers.getCrewLeader());
		
		int result = crewMembersService.insertMember(crewMembers);
		
		int currMembers = crewMembersService.countMember(crewMembers.getCrewId());
		Map<String, Integer> currMems = new HashMap<>();
		currMems.put("crewId", crewMembers.getCrewId());
		currMems.put("currMembers", currMembers);
		int changeCurrMemResult = crewMembersService.changeCurrMember(currMems);
		
		if(changeCurrMemResult != 0) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/members/deleteMember")
	public ResponseEntity<?> deleteMember(@RequestParam Map<String, String> memberInfo){
		int result = crewMembersService.deleteMember(memberInfo);
		
		int cId = Integer.parseInt(memberInfo.get("crewId"));
		
		int currMembers = crewMembersService.countMember(cId);
		Map<String, Integer> currMems = new HashMap<>();
		currMems.put("crewId", cId);
		currMems.put("currMembers", currMembers);
		int changeCurrMemResult = crewMembersService.changeCurrMember(currMems);
		
		if(changeCurrMemResult != 0) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}
