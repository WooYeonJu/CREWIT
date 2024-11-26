package com.ssafy.ws.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ws.model.dao.CrewDetailBoardDao;
import com.ssafy.ws.model.dto.CrewDetailBoard;

@Service
public class CrewDetailBoardServiceImpl implements CrewDetailBoardService {

	@Autowired
	private CrewDetailBoardDao crewDetailBoardDao;

	@Override
	public List<CrewDetailBoard> selectAll() {
		// TODO Auto-generated method stub
		return crewDetailBoardDao.selectAll();
	}

	@Override
	public List<CrewDetailBoard> searchByTitle(Map forTitle) {
		// TODO Auto-generated method stub
//		System.out.println(title);
		return crewDetailBoardDao.searchByTitle(forTitle);
	}

	@Override
	public List<CrewDetailBoard> selectByCrewId(int id) {
		// TODO Auto-generated method stub
		return crewDetailBoardDao.selectByCrewId(id);
	}

	@Override
	public CrewDetailBoard selectByBoardId(int id) {
		// TODO Auto-generated method stub
		return crewDetailBoardDao.selectByBoardId(id);
	}

	@Override
	public boolean writeCrewBoard(CrewDetailBoard crewDetailBoard) {
		// TODO Auto-generated method stub
		return crewDetailBoardDao.writeCrewBoard(crewDetailBoard)==1;
	}

	@Override
	public boolean modifyCrewBoard(CrewDetailBoard crewDetailBoard) {
		// TODO Auto-generated method stub
		return crewDetailBoardDao.modifyCrewBoard(crewDetailBoard)!=0;
	}

	@Override
	public boolean removeCrewBoard(int id) {
		// TODO Auto-generated method stub
		return crewDetailBoardDao.removeCrewBoard(id)==1;
	}


}
