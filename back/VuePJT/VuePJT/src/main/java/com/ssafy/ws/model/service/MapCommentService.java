package com.ssafy.ws.model.service;

import java.util.List;

import com.ssafy.ws.model.dto.Comment;
import com.ssafy.ws.model.dto.MapComment;

public interface MapCommentService {

    int insert(MapComment comment);
    
    int delete(int id);

    List<MapComment> searchByPlaceId(int placeId);
    
    List<MapComment> selectAll();
    
    int modifyComment(MapComment comment);

}