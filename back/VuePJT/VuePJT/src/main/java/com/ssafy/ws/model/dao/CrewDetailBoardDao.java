package com.ssafy.ws.model.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.ws.model.dto.CrewDetailBoard;

public interface CrewDetailBoardDao {

	List<CrewDetailBoard> searchByTitle(Map forTitle);

	List<CrewDetailBoard> selectByCrewId(int id);
	CrewDetailBoard selectByBoardId(int id);

	List<CrewDetailBoard> selectAll();
	
	int writeCrewBoard(CrewDetailBoard crewDetailBoard);
	int modifyCrewBoard(CrewDetailBoard crewDetailBoard);
	int removeCrewBoard(int boardId);

}
