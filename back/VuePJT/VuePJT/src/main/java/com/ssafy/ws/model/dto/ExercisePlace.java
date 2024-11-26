package com.ssafy.ws.model.dto;

public class ExercisePlace {

	private int id;
	private String name;
	private String location;
	private String exercise;
	private String homepage;
	private String info;
	private String call;

	public ExercisePlace(String name, String location, String exercise) {
		this.name = name;
		this.location = location;
		this.exercise = exercise;
	}

	public ExercisePlace(int id, String name, String location, String exercise, String homepage, String info,
			String call) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.exercise = exercise;
		this.homepage = homepage;
		this.info = info;
		this.call = call;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getCall() {
		return call;
	}

	public void setCall(String call) {
		this.call = call;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getExercise() {
		return exercise;
	}

	public void setExercise(String exercise) {
		this.exercise = exercise;
	}

	@Override
	public String toString() {
		return "ExercisePlace [id=" + id + ", name=" + name + ", location=" + location + ", exercise=" + exercise + "]";
	}

}
