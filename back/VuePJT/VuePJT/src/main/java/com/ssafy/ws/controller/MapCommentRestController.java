package com.ssafy.ws.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ws.model.dto.MapComment;
import com.ssafy.ws.model.service.MapCommentService;

@RestController
@RequestMapping("/mapcommentapi")
@CrossOrigin("*")
public class MapCommentRestController {
	@Autowired
	MapCommentService commentService;

	@GetMapping("/comment/{placeId}")
	public ResponseEntity<?> searchByBoardId(@PathVariable int placeId) {
		try {
			List<MapComment> comments = commentService.searchByPlaceId(placeId);
			if (comments != null) {
				return new ResponseEntity<List<MapComment>>(comments, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/comment")
	public ResponseEntity<?> selectAll() {
		try {
			List<MapComment> comments = commentService.selectAll();
			if (comments != null && comments.size() > 0) {
				return new ResponseEntity<List<MapComment>>(comments, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PostMapping("/insert")
	public ResponseEntity<?> insert(@RequestBody MapComment comment) {
		try {
//            System.out.println(comment.getRate());
			int result = commentService.insert(comment);
			return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody MapComment comment) {
//		System.out.println(comment.getContent()); // 출력 안됨
//		System.out.println(comment.getRate()); // 출력 안됨
		if (commentService.modifyComment(comment) != 0)
			return ResponseEntity.ok("comment updated successfully");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update crew board Detail");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		try {
			int result = commentService.delete(id);
			if (result == 0) {
				return new ResponseEntity<Integer>(result, HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("sorry: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}