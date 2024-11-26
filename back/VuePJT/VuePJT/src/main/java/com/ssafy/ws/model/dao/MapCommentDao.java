package com.ssafy.ws.model.dao;


import java.util.List;

import com.ssafy.ws.model.dto.MapComment;

public interface MapCommentDao {

    int insert(MapComment comment);
    
    int delete(int id);

    List<MapComment> searchByPlaceId(int placeId);
    
    List<MapComment> selectAll();
    
    int modifyComment(MapComment comment);

}