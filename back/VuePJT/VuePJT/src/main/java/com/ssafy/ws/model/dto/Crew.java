package com.ssafy.ws.model.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Crew {
	private int id; // 게시글 id
	private String crewName; // 크루 이름(게시글 이름) /
	private String crewLeader; // 리더 이름
	private String crewCreateDate; // 크루 생성 일자
//	private String crewDueDate; // 마감 날짜 /
	private int crewCurrMember; // 현재 멤버
	private int crewMaxMember; // 최대 멤버 /
	private String crewContent; // 크루 설명 /
	private int crewExerciseCode;// 운동 종목/
	private String crewExerciseName; // 운동 이름/
	private String crewLocation; // 운동 장소/
	private String crewExerciseDate; // 운동 시설예약 날짜 및 시간/
	private String[] crewMembers;

	public String[] getCrewMembers() {
		return crewMembers;
	}

	public void setCrewMembers(String[] crewMembers) {
		this.crewMembers = crewMembers;
	}

	
	public Crew() {
		// TODO Auto-generated constructor stub
	}

//	public Crew(int id, String crewName, String crewLeader, String crewCreateDate, String crewDueDate,
//			int crewCurrMember, int crewMaxMember, String crewContent, int crewExerciseCode, String crewLocation,
//			String crewExerciseDate) {
//		super();
//		this.id = id;
//		this.crewName = crewName;
//		this.crewLeader = crewLeader;
//		this.crewCreateDate = crewCreateDate;
//		this.crewDueDate = crewDueDate;
//		this.crewCurrMember = crewCurrMember;
//		this.crewMaxMember = crewMaxMember;
//		this.crewContent = crewContent;
//		this.crewExerciseCode = crewExerciseCode;
//		this.crewLocation = crewLocation;
//		this.crewExerciseDate = crewExerciseDate;
//	}
//
//	public Crew(int id, String crewName, String crewLeader, String crewCreateDate, String crewDueDate,
//			int crewCurrMember, int crewMaxMember, String crewContent, int crewExerciseCode, String crewLocation,
//			String crewExerciseDate, String crewMember) {
//		super();
//		this.id = id;
//		this.crewName = crewName;
//		this.crewLeader = crewLeader;
//		this.crewCreateDate = crewCreateDate;
//		this.crewDueDate = crewDueDate;
//		this.crewCurrMember = crewCurrMember;
//		this.crewMaxMember = crewMaxMember;
//		this.crewContent = crewContent;
//		this.crewExerciseCode = crewExerciseCode;
//		this.crewLocation = crewLocation;
//		this.crewExerciseDate = crewExerciseDate;
//		this.crewMembers = new ArrayList<>();
//		this.crewMembers.add(crewMember);
//	}

	public int getCrewId() {
		return id;
	}

	public void setCrewId(int crewId) {
		this.id = crewId;
	}

	public String getCrewName() {
		return crewName;
	}

	public void setCrewName(String crewName) {
		this.crewName = crewName;
	}

	public String getCrewLeader() {
		return crewLeader;
	}

	public void setCrewLeader(String crewLeader) {
		this.crewLeader = crewLeader;
	}

	public String getCrewCreateDate() {
		return crewCreateDate;
	}

	public void setCrewCreateDate(String crewCreateDate) {
		this.crewCreateDate = crewCreateDate;
	}

//	public String getCrewDueDate() {
//		return crewDueDate;
//	}
//
//	public void setCrewDueDate(String crewDueDate) {
//		this.crewDueDate = crewDueDate;
//	}

	public int getCrewCurrMember() {
		return crewCurrMember;
	}

	public void setCrewCurrMember(int crewCurrMember) {
		this.crewCurrMember = crewCurrMember;
	}

	public int getCrewMaxMember() {
		return crewMaxMember;
	}

	public void setCrewMaxMember(int crewMaxMember) {
		this.crewMaxMember = crewMaxMember;
	}

	public String getCrewContent() {
		return crewContent;
	}

	public void setCrewContent(String crewContent) {
		this.crewContent = crewContent;
	}

	public int getCrewExerciseCode() {
		return crewExerciseCode;
	}

	public void setCrewExerciseCode(int crewExerciseCode) {
		this.crewExerciseCode = crewExerciseCode;
	}

	public String getCrewExerciseName() { // 메서드 이름 수정 확인
		return crewExerciseName;
	}

	public void setCrewExerciseName(String crewExerciseName) { // 메서드 이름 수정 확인
		this.crewExerciseName = crewExerciseName;
	}

	public String getCrewLocation() {
		return crewLocation;
	}

	public void setCrewLocation(String crewLocation) {
		this.crewLocation = crewLocation;
	}

	public String getCrewExerciseDate() {
		return crewExerciseDate;
	}

	public void setCrewExerciseDate(String crewExerciseDate) {
		this.crewExerciseDate = crewExerciseDate;
	}

//	public List<String> getCrewMembers() {
//		return crewMembers;
//	}
//
//	public void setCrewMembers(String crewMembers) {
//		if (crewMembers != null && !crewMembers.isEmpty()) {
//			this.crewMembers = Arrays.asList(crewMembers.split(","));
//		} else {
//			this.crewMembers = new ArrayList<>();
//		}
//	}

	@Override
	public String toString() {
		return "Crew [id=" + id + ", crewName=" + crewName + ", crewLeader=" + crewLeader + ", crewCreateDate="
				+ crewCreateDate + ", crewCurrMember=" + crewCurrMember
				+ ", crewMaxMember=" + crewMaxMember + ", crewContent=" + crewContent + ", crewExerciseCode="
				+ crewExerciseCode + ", crewExerciseName=" + crewExerciseName + ", crewLocation=" + crewLocation
				+ ", crewExerciseDate=" + crewExerciseDate + "]";
	}

}
