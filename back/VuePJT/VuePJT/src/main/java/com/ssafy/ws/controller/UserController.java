package com.ssafy.ws.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.ws.model.dto.Filedto;
import com.ssafy.ws.model.dto.User;
import com.ssafy.ws.model.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/userapi")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	ResourceLoader resLoader;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
		User tmp = userService.login(user);
//		System.out.println(tmp.getAccessToken()); 

		if (tmp != null) {
			session.setAttribute("access-token", tmp.getAccessToken());
			return new ResponseEntity<User>(tmp, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}

	@PostMapping("/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}

	@PostMapping("/user")
	public ResponseEntity<Void> signup(
	        @RequestParam("id") String id,
	        @RequestParam("password") String password,
	        @RequestParam("name") String name,
	        @RequestParam("email") String email,
	        @RequestParam("phone") String phone,
	        @RequestParam("age") int age,
	        @RequestParam(value = "profileImg", required = false) MultipartFile profileImg) throws IOException {

		System.out.println("ddd"); 
		
		User user = new User(id, password, name, email, phone, age);
//	    String profileImgPath = null;
	    if (profileImg != null && !profileImg.isEmpty()) {
//	    	
//	        profileImgPath = saveFile(id, profileImg); // 저장된 파일 경로 받기
	    	
	    	

	    //Resource res = resLoader.getResource("file:C:/Users/SSAFY/Desktop/YJGit/finalPJT/final/finalfinal/front/vuePJTFront/src/assests/");
	    
	    // 유저 정보 설정 (파일 경로 포함)
	    
	    user.setImg(id + "_" + profileImg.getOriginalFilename());
//	    profileImg.transferTo(new File(res.getFile().getAbsolutePath(), user.getImg()));
	    profileImg.transferTo(new File("C:/Users/SSAFY/Desktop/YJGit/finalPJT/final/finalfinal/front/vuePJTFront/src/assests/" +  user.getImg()));
	    }

	    int result = userService.signup(user);
	    if (result >= 1) {
	        return new ResponseEntity<>(HttpStatus.CREATED); // 회원가입 성공
	    }
	    return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 실패
	}

	


//	private String saveFile(String userId, MultipartFile file) throws IOException {
//	    if (file != null && !file.isEmpty()) {
//	        String fileName = userId + "_" + file.getOriginalFilename();
////	        Path filePath = Paths.get("src/assests/" + fileName);
//	        
//
////	        Files.write(filePath, file.getBytes());
//	        boolean isFile = Files.isDirectory(Paths.get("C:/Users/SSAFY/Desktop/YJGit/finalPJT/final/finalfinal/front/vuePJTFront/src/assests"), LinkOption.NOFOLLOW_LINKS);
//	        System.out.println(isFile);
//	        
//	        
//	        // 경로를 포함한 Filedto 객체 생성
//	        Filedto filedto = new Filedto();
//	        filedto.setUserId(userId);
//	        filedto.setFileName(fileName);
//	        filedto.setFilePath(filePath.toString()); // 실제 저장된 경로
//
//	        int result = filedao.fileUpload(filedto);
//	        return filePath.toString(); // 실제 파일 경로 반환
//	    }
//	    return null;
//	}



	@GetMapping("/searchId")
	public ResponseEntity<?> searchId(@RequestParam String name, @RequestParam String email) {

//		System.out.println(requestData.get("name"));
		Map<String, String> forSearchId = new HashMap<>();
		forSearchId.put("name", name);
		forSearchId.put("email", email);

		String id = userService.searchId(forSearchId);
//		System.out.println(id);
		if (id != null) {
//			System.out.println(id);
			return new ResponseEntity<String>(id, HttpStatus.OK);
		}
//		System.out.println("안됨");
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/changepw")
	public ResponseEntity<?> changePw(@RequestBody Map<String, String> requestData) {

//		System.out.println("Received request data: " + requestData);

		String id = requestData.get("id");
		String newPw = requestData.get("newPw");

		if (id == null || newPw == null) {
			return new ResponseEntity<>("Missing id or new password", HttpStatus.BAD_REQUEST);
		}

		int result = userService.changePW(requestData);

		if (result > 0) {
			return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Failed to change password", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/checkId")
	public ResponseEntity<?> checkId(@RequestParam String id) {
		if (id == null || id.isEmpty()) {
			return new ResponseEntity<String>("ID를 입력해주세요", HttpStatus.BAD_REQUEST);
		}

		User checkedUser = userService.checkId(id);
		if (checkedUser != null) {
//			System.out.println("checkId 성공: " + checkedUser.getId());
			return new ResponseEntity<User>(checkedUser, HttpStatus.OK);
		}
		return new ResponseEntity<>("Failed to search User", HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/checkpw")
	public ResponseEntity<?> checkpw(@RequestBody Map<String, String> requestData) {
		String id = requestData.get("id");
		String pw = requestData.get("password");

		if (id == null || pw == null) {
			return new ResponseEntity<>("Missing id or password", HttpStatus.BAD_REQUEST);
		}

		boolean isCorrectPW = userService.checkpw(requestData);
		if (isCorrectPW) {
			System.out.println(isCorrectPW);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<>("Failed to find User", HttpStatus.NO_CONTENT);
	}

	@GetMapping("/mypage")
	public ResponseEntity<?> mypage(@RequestParam String id) {
		if (id == null || id.isEmpty()) {
			return new ResponseEntity<String>("ID를 입력해주세요", HttpStatus.BAD_REQUEST);
		}
		User userInfo = userService.mypage(id);
		
//		System.out.println("service - mypage" + userInfo.getPhone());
		
		if (userInfo != null) {
			String profileImgPath = userInfo.getImg(); // 데이터베이스에서 반환된 이미지 경로를 그대로 사용
			
//			System.out.println(profileImgPath);
			
			if (profileImgPath != null && !profileImgPath.isEmpty()) {
				// profileImgPath가 존재하면 경로를 그대로 사용 (이미 DB에 저장된 경로가 올바른 경우)
				userInfo.setImg(profileImgPath); // 절대 경로로 수정
			} else {
				// 프로필 이미지가 없으면 기본 이미지를 사용
				userInfo.setImg("profile.jpeg"); // 기본 프로필 이미지
			}
			System.out.println("리턴 직전: "+userInfo.getImg());
			return new ResponseEntity<User>(userInfo, HttpStatus.OK);
		}
		return new ResponseEntity<>("Failed to search User", HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/changeEmail")
	public ResponseEntity<?> changeEmail(@RequestBody Map<String, String> newEmail) {
		String id = newEmail.get("id");
		String email = newEmail.get("email");

//		System.out.println(id + " " + email);

		if (id == null || email == null) {
			return new ResponseEntity<>("Missing id or new email", HttpStatus.BAD_REQUEST);
		}

		int result = userService.changeEmail(newEmail);

		if (result > 0) {
			return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Failed to change password", HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/changePhone")
	public ResponseEntity<?> changePhone(@RequestBody Map<String, String> newPhone) {
		String id = newPhone.get("id");
		String phone = newPhone.get("phone");

//		System.out.println(id + " " + phone);

		if (id == null || phone == null) {
			return new ResponseEntity<>("Missing id or new phone", HttpStatus.BAD_REQUEST);
		}

		int result = userService.changePhone(newPhone);

		if (result > 0) {
			return new ResponseEntity<>("phone changed successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Failed to change phone", HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/changeAge")
	public ResponseEntity<?> changeAge(@RequestBody Map<String, String> newAge) {
		String id = newAge.get("id");
		String age = newAge.get("age");

//		System.out.println(id + " " + age);

		if (id == null || age == null) {
			return new ResponseEntity<>("Missing id or new age", HttpStatus.BAD_REQUEST);
		}

		int result = userService.changeAge(newAge);

		if (result > 0) {
			return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Failed to change password", HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/deleteUser")
	public ResponseEntity<?> deleteUser(@RequestParam String id) {

//		System.out.println(id);

		int result = userService.deleteUser(id);
		if (result > 0) {
			return new ResponseEntity<>("User Deleted successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Failed to delete user", HttpStatus.BAD_REQUEST);
	}

}
