package com.ssafy.ws.model.service;

import java.util.List;

import com.ssafy.ws.model.dto.Comment;

public interface CommentService {

    int insert(Comment comment);

    int delete(int id);

    List<Comment> searchByBoardId(int boardId);

    List<Comment> selectAll();
    
    int modifyComment(Comment comment);
}