package com.ssafy.ws.model.dto;


public class Exercise {
	private int id;
	private String exercise;
	
	public Exercise(int id, String exercise) {
		super();
		this.id = id;
		this.exercise = exercise;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getExercise() {
		return exercise;
	}
	public void setExercise(String exercise) {
		this.exercise = exercise;
	}
	@Override
	public String toString() {
		return "Exercise [id=" + id + ", exercise=" + exercise + "]";
	}
	
	

}
