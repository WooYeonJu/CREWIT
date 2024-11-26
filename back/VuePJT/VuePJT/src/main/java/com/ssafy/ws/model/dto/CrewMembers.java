package com.ssafy.ws.model.dto;

public class CrewMembers {

	private int crewId;
	private String crewName;
	private String crewLeader;
	private String crewMember;


	
	public CrewMembers(int crewId, String crewName, String crewLeader, String crewMember) {
		super();
		this.crewId = crewId;
		this.crewName = crewName;
		this.crewLeader = crewLeader;
		this.crewMember = crewMember;
	}

	public String getCrewName() {
		return crewName;
	}
	
	public void setCrewName(String crewName) {
		this.crewName = crewName;
	}

	public int getCrewId() {
		return crewId;
	}

	public void setCrewId(int crewId) {
		this.crewId = crewId;
	}

	public String getCrewLeader() {
		return crewLeader;
	}

	public void setCrewLeader(String crewLeader) {
		this.crewLeader = crewLeader;
	}

	public String getCrewMember() {
		return crewMember;
	}

	public void setCrewMember(String crewMember) {
		this.crewMember = crewMember;
	}
	
}
