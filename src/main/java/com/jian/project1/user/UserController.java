package com.jian.project1.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jian.project1.Const;
import com.jian.project1.SecurityUtils;
import com.jian.project1.mail.MailSendService;
import com.jian.project1.model.UserDTO;
import com.jian.project1.model.UserEntity;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private MailSendService mailSender;
	
	@Autowired
	private SecurityUtils sUtils;
	
	
    // 로그인
	@GetMapping("/login")
	public void login() {
		
	}
	
	@ResponseBody
	@PostMapping("/login")
	public int login(@RequestBody UserEntity p, HttpSession hs) {
		return service.login(p, hs);
	}
	
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession hs) {
		hs.invalidate();
		return "redirect:/user/login";
	}

	
	// 회원가입
	@GetMapping("/join")
	public void join() {
		
	}
	
	@ResponseBody
	@PostMapping("/join")
	public int join(@RequestBody UserEntity p) {
		System.out.println("joinUserEmail : " + p.getUserEmail());
		
		int joinResult = service.join(p);
		System.out.println(joinResult);
		
		if(joinResult == 1) {
			// 회원가입에 성공했다면 임의의 autoKey 생성 => 이메일 발송
			String authKey = mailSender.sendAuthMail(p.getUserEmail());
			p.setAuthKey(authKey);
			System.out.println("authKey : " + p.getAuthKey());
			
			service.updAuthKey(p);
		}
		
		return joinResult;
	}
	
	
	// 메일 인증
	@GetMapping("/joinConfirm")
	public String joinConfirm(HttpServletRequest request, UserEntity p) {
		// request에서 값을 받아온다.
		String userEmail = request.getParameter("userEmail");
		String authKey = request.getParameter("authKey");
		
		// 받은 값을 저장
		p.setUserEmail(userEmail);
		p.setAuthKey(authKey);

		
		// 이메일과 authKey가 일치할 경우 authStatus 업데이트
		int joinConfirmResult = service.chkAndUpdAuthStatus(p);
		
		if(joinConfirmResult == 1) {
			return "redirect:/user/joinConfirmSuccess";
		} else {
			return "redirect:/user/joinConfirmFailure";
		}
	}
	
	// 메일 인증 성공
	@GetMapping("/joinConfirmSuccess")
	public void joinConfirmSuccess() {
		
	}
	
	// 메일 인증 실패
	@GetMapping("/joinConfirmFailure")
	public void joinConfirmFailure() {
		
	}
	
	
	// 약관동의
	@GetMapping("/terms") 
	public void terms() {
		
	}
	

	// 마이페이지
	@GetMapping("/mypage")
	public void mypage(Model model, UserEntity p, HttpSession hs) {
		p.setUserPk(sUtils.getLoginUserPk(hs));
		model.addAttribute(Const.KEY_DATE, service.selUser(p));
	}
	
	// 마이페이지 이미지 업로드
	@ResponseBody
	@PostMapping("/mypage")
	public int mypage(MultipartFile profileImg, HttpSession hs) {
		System.out.println("fileName : " + profileImg.getOriginalFilename());
		return service.uploadProfile(profileImg, hs);
	}
	
	
	// 비밀번호 변경
	@GetMapping("/changePw")
	public void changePw() {
		
	}
	
	@ResponseBody
	@PostMapping("/changePw")
	public int changePw(@RequestBody UserDTO p, HttpSession hs) {
		return service.changePw(p, hs);
	}
	
	
	// 회원 탈퇴
	@GetMapping("/withDrawal")
	public void widthDrawal() {
		
	}
	
	@ResponseBody
	@PostMapping("/withDrawal")
	public int withDrawal(@RequestBody UserEntity p, HttpSession hs) {
		System.out.println(p.getUserPk());
		return service.withDrawal(p, hs);
	}
	
	
	// 아이디 찾기
	@GetMapping("/findEmail")
	public void findEmail() {
		
	}
	
	@ResponseBody
	@PostMapping("/findEmail")
	public UserEntity findEmail(@RequestBody UserEntity p) {
		return service.findEmail(p);
	}
	
	
	// 비밀번호 찾기
	@GetMapping("/findPw")
	public void findPw() {
		
	}
	

	
	@ResponseBody
	@PostMapping("/findPw")
	public int findPw(@RequestBody UserEntity p) {
		
		// 이메일, 이름, 전화번호 맞는지 체크하고 새로운 비밀번호 업데이트
		int chkUser = service.chkUserInfo(p);
		
		if(chkUser == 1) {
			return chkUser;
		}
		return chkUser;
	}
	
	
}







