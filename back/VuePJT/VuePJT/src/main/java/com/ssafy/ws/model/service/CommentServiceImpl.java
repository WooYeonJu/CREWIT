
package com.ssafy.ws.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.ws.model.dao.CommentDao;
import com.ssafy.ws.model.dto.Comment;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public int insert(Comment comment) {
        // TODO Auto-generated method stub
        return commentDao.insert(comment);
    }

    @Override
    public int delete(int id) {
        // TODO Auto-generated method stub
        return commentDao.delete(id);
    }

    @Override
    public List<Comment> searchByBoardId(int boardId) {
        // TODO Auto-generated method stub
        return commentDao.searchByBoardId(boardId);
    }

    @Override
    public List<Comment> selectAll() {
        // TODO Auto-generated method stub
        return commentDao.selectAll();
    }

	@Override
	public int modifyComment(Comment comment) {
		// TODO Auto-generated method stub
		return commentDao.modifyComment(comment);
	}



}

