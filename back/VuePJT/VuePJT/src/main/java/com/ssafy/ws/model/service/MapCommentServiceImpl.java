
package com.ssafy.ws.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ws.model.dao.MapCommentDao;
import com.ssafy.ws.model.dto.Comment;
import com.ssafy.ws.model.dto.MapComment;

@Service
public class MapCommentServiceImpl implements MapCommentService {

    @Autowired
    private MapCommentDao commentDao;

	@Override
	public int insert(MapComment comment) {
		// TODO Auto-generated method stub
		return commentDao.insert(comment);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return commentDao.delete(id);
	}

	@Override
	public List<MapComment> searchByPlaceId(int placeId) {
		// TODO Auto-generated method stub
		return commentDao.searchByPlaceId(placeId);
	}

	@Override
	public List<MapComment> selectAll() {
		// TODO Auto-generated method stub
		return commentDao.selectAll();
	}

	@Override
	public int modifyComment(MapComment comment) {
		// TODO Auto-generated method stub
		return commentDao.modifyComment(comment);
	}

}

