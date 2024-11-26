package com.ssafy.ws.model.dao;

import java.util.Map;

import com.ssafy.ws.model.dto.User;

public interface UserDao {

	int insert(User user);

	User selectOne(User user);
	
	String searchId(Map forSearchId);

	int changePW(Map change);
	
	User checkId(String id);
	
	User mypage(String id);
	
	User checkpw(Map requestData);
	
	int changeEmail(Map newEmail);
	
	int changePhone(Map newPhone);
	
	int changeAge(Map newAge);
	
	int deleteUser(String id);
}
