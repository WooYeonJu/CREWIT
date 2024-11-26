package com.ssafy.ws.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.ws.model.dao.CrewDao;
import com.ssafy.ws.model.dto.Crew;
import com.ssafy.ws.model.dto.SearchCondition;

@Service
public class CrewServiceImpl implements CrewService{

	@Autowired
	private CrewDao crewDao;
	
	
//	public BoardServiceImpl(BoardDao boardDao) {
//		this.boardDao = boardDao;
//	}

	@Override
	public List<Crew> searchCrew(SearchCondition searchCondition) {
		
//		
//		System.out.println(crewDao.search(searchCondition).get(1));
		return crewDao.search(searchCondition);
	}

	@Transactional
	@Override
	public Crew readCrew(int id) {
//		System.out.println(crewDao.selectOne(id).getCrewMembers().get(0));
		return crewDao.selectOne(id);
	}

	@Transactional
	@Override
	public boolean writeCrew(Crew crew) {
//		System.out.println(crew.getCrewName());
		return crewDao.insertCrew(crew) == 1;
	}

	@Transactional
	@Override
	public boolean removeCrew(int id) {
		return crewDao.deleteCrew(id) == 1;
	}

	@Override
	public boolean modifyCrew(Crew crew) {
//		System.out.println(crew.getCrewExerciseName());
//		System.out.println(crewDao.updateCrew(crew));
		return crewDao.updateCrew(crew) == 1;
	}

}
