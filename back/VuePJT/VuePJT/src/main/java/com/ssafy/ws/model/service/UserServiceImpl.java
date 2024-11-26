package com.ssafy.ws.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.ws.model.dao.UserDao;
import com.ssafy.ws.model.dto.User;
import com.ssafy.ws.util.JwtUtil;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	private UserDao userDao;

	@Override
	public User login(User user) {

		User tmp = userDao.selectOne(user);

		if (tmp != null) {
//			System.out.println(tmp.getId());
//			System.out.println(tmp.getEmail());
			tmp.setAccessToken(jwtUtil.createToken(tmp.getEmail()));
//			System.out.println(tmp.getAccessToken());
		}

		return tmp;
	}

	@Transactional
	@Override
	public int signup(User user) {
		return userDao.insert(user);
	}

	@Override
	public String searchId(Map forSearchId) {
		// TODO Auto-generated method stub
//		System.out.println(userDao.searchId(forSearchId));
		return userDao.searchId(forSearchId);
	}

	@Override
	public int changePW(Map change) {
		// TODO Auto-generated method stub
		return userDao.changePW(change);
	}

	@Override
	public User checkId(String id) {
		// TODO Auto-generated method stub
//		System.out.println(id);
		return userDao.checkId(id);
	}

	@Override
	public User mypage(String id) {
		// TODO Auto-generated method stub
		
//		System.out.println("유저 다오 들어가기 전");
//		System.out.println(userDao.mypage(id).getAge() );
//		System.out.println("유저 다오 들어간 후");
		
		
		return userDao.mypage(id);
	}

	@Override
	public boolean checkpw(Map requestData) {
		// TODO Auto-generated method stub
		User result = userDao.checkpw(requestData);
		if(result != null) {
			return true;
		}
		return false;
	}

	@Override
	public int changeEmail(Map newEmail) {
		// TODO Auto-generated method stub
		return userDao.changeEmail(newEmail);
	}

	@Override
	public int changeAge(Map newAge) {
		// TODO Auto-generated method stub
		return userDao.changeAge(newAge);
	}

	@Override
	public int deleteUser(String id) {
		// TODO Auto-generated method stub
		return userDao.deleteUser(id);
	}

	@Override
	public int changePhone(Map newPhone) {
		// TODO Auto-generated method stub
		return userDao.changePhone(newPhone);
	}

}
