package com.ssafy.ws.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.ws.model.dto.CrewDetailBoard;

public interface CrewDetailBoardService {
	
	List<CrewDetailBoard> searchByTitle(Map forTitle);

	List<CrewDetailBoard> selectByCrewId(int id);
	CrewDetailBoard selectByBoardId(int id);

	List<CrewDetailBoard> selectAll();
	
	boolean writeCrewBoard(CrewDetailBoard crewDetailBoard);
	boolean modifyCrewBoard(CrewDetailBoard crewDetailBoard);
	boolean removeCrewBoard(int boardId);
}
