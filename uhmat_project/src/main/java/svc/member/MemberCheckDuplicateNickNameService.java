package svc.member;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MemberDAO;

<<<<<<<< HEAD:uhmat_project/src/main/java/svc/member/MemberCheckDuplicateNickNameService.java
public class MemberCheckDuplicateNickNameService {

	public boolean checkDuplicateNickName(String nickName) {
========
public class MemberCheckDuplicateEmailService {

	public boolean checkDuplicateEmail(String email) {
>>>>>>>> 10b9fd608ca9e4a54c35035f699131a4a851f3d6:uhmat_project/src/main/java/svc/member/MemberCheckDuplicateEmailService.java
boolean isDuplicate = false;
		
		Connection con = getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		// MemberDAO 의 selectDuplicateId() 메서드를 호출하여 아이디 검색 수행
		// => 파라미터 : 아이디(id)   리턴타입 : boolean(isDuplicate)
<<<<<<<< HEAD:uhmat_project/src/main/java/svc/member/MemberCheckDuplicateNickNameService.java
		isDuplicate = dao.selectDuplicateNickName(nickName);
========
		isDuplicate = dao.selectDuplicateEmail(email);
>>>>>>>> 10b9fd608ca9e4a54c35035f699131a4a851f3d6:uhmat_project/src/main/java/svc/member/MemberCheckDuplicateEmailService.java
		
		close(con);
		
		return isDuplicate;
	}

}
