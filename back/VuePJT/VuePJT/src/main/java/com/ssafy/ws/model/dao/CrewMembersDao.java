package com.ssafy.ws.model.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.ws.model.dto.Crew;
import com.ssafy.ws.model.dto.CrewMembers;

public interface CrewMembersDao {
	
	public List<CrewMembers> selectAll();

	public List<CrewMembers> searchByCrewId(int crewId);

	public List<CrewMembers> searchByUserId(String userId);
	
	public List<Crew> searchLeaderByUserId(String userId);
	
	public int insertMember(CrewMembers crewMember);
	
	public int countMember(int crewId);
	
	public int changeCurrMember(Map currMems);
	
	
	public int deleteMember(Map memberInfo);	
	

}
