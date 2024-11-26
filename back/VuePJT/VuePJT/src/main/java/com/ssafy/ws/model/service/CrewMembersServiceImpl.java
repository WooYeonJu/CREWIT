package com.ssafy.ws.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ws.model.dao.CrewMembersDao;
import com.ssafy.ws.model.dto.Crew;
import com.ssafy.ws.model.dto.CrewMembers;

@Service
public class CrewMembersServiceImpl implements CrewMembersService {
	
	@Autowired
	CrewMembersDao crewMembersDao;
	
	@Override
	public List<CrewMembers> selectAll() {
		// TODO Auto-generated method stub
		return crewMembersDao.selectAll();
	}

	@Override
	public List<CrewMembers> searchByCrewId(int crewId) {
		// TODO Auto-generated method stub
		return crewMembersDao.searchByCrewId(crewId);
	}

	@Override
	public List<CrewMembers> searchByUserId(String userId) {
		// TODO Auto-generated method stub
		return crewMembersDao.searchByUserId(userId);
	}

	@Override
	public int insertMember(CrewMembers crewMember) {
		// TODO Auto-generated method stub
		return crewMembersDao.insertMember(crewMember);
	}

	@Override
	public int deleteMember(Map memberInfo) {
		// TODO Auto-generated method stub
		return crewMembersDao.deleteMember(memberInfo);
	}

	@Override
	public int countMember(int crewId) {
		// TODO Auto-generated method stub
		return crewMembersDao.countMember(crewId);
	}

	@Override
	public int changeCurrMember(Map currMems) {
		// TODO Auto-generated method stub
		return crewMembersDao.changeCurrMember(currMems);
	}

	@Override
	public List<Crew> searchLeaderByUserId(String userId) {
//		System.out.println(userId);
		// TODO Auto-generated method stub
		return crewMembersDao.searchLeaderByUserId(userId);
	}

}
