package com.ssafy.ws.model.service;

import java.util.Map;

import com.ssafy.ws.model.dto.User;

public interface UserService {
	User login(User user);

	int signup(User user);

	String searchId(Map forSearchId);
	
	int changePW(Map change);

	User checkId(String id);
	
	boolean checkpw(Map requestData);
	
	User mypage(String id);
	
	int changeEmail(Map newEmail);
	
	int changePhone(Map newPhone);

	int changeAge(Map newAge);
	
	int deleteUser(String id);
}
