

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

import com.ssafy.ws.model.dto.Comment;
import com.ssafy.ws.model.service.CommentService;

@RestController
@RequestMapping("/commentapi")
@CrossOrigin("*")
public class CommentRestController {
    @Autowired
    CommentService commentService;

    @GetMapping("/comment/{boardId}")
    public ResponseEntity<?> searchByBoardId(@PathVariable int boardId) {
        try {
            List<Comment> comments = commentService.searchByBoardId(boardId);
            if (comments != null) {
                return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
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
            List<Comment> comments = commentService.selectAll();
            if (comments != null && comments.size() > 0) {
                return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @PostMapping("/comment")
    public ResponseEntity<?> insert(@RequestBody Comment comment) {
        try {
//            System.out.println(movie);
            int result = commentService.insert(comment);
            return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    
    @PutMapping("/comment/update")
	public ResponseEntity<String> update(@RequestBody Comment comment) {
//		System.out.println(comment.getContent()); // 출력 안됨
		if (commentService.modifyComment(comment) != 0)
			return ResponseEntity.ok("comment updated successfully");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update crew board Detail");
	}

    @DeleteMapping("/comment/{id}")
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