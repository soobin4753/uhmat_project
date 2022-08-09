package svc;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.CommunityDAO;
import vo.CommunityTmiDTO;
import static db.JdbcUtil.*;

public class TmiDetailService {

	public CommunityTmiDTO getTmiBoard(int idx) {
		System.out.println("TmiDetailService - CommunityTmiDTO");
		CommunityTmiDTO tmiBoard = null;
		
		// 1. JdbcUtill 클래스로부터 Connection Pool에 저장된 Connection 객체를 가져옵니다. - 공통작업
		// => Service 클래스에서 트랜잭션 관리를 위해 Connection 객체에 접근해야하기 때문입니다.
		Connection con = getConnection(); // svc.class를 만들면 메서드안에는 무조건 Connection 객체가 사용!
				
		// 2. CommunityTmiDTO 클래스로부터 CommunityDAO 인스턴스를 가져와서 저장합니다. - 공통작업
		// => 싱글톤 디자인 패턴으로 생성된 CommunityDAO 인스턴스 가져오기
		CommunityDAO dao = CommunityDAO.getInstance();
				
		// 3. CommunityDAO 인스턴스에 Connection 객체를 전달합니다. - 공통작업
		dao.setConnection(con);
		
		// CommunityDAO 객체의 selectTmiBoard() 메서드를 호출하여 1개 게시물의 상세 정보 조회 작업 수행
		// => 파라미터 : idx, 리턴타입: CommunityDTO(board)
		tmiBoard = dao.selectTmiBoard(idx);
				
		// 4. Connection 객체 반환 - 공통작업
		close(con);
		
		return tmiBoard;
	}

	public void tmiIncreaseReadcount(int idx) {
		System.out.println("TmiDetailService - tmiIncreaseReadcount");
		
		// 1. JdbcUtill 클래스로부터 Connection Pool에 저장된 Connection 객체를 가져옵니다. - 공통작업
		// => Service 클래스에서 트랜잭션 관리를 위해 Connection 객체에 접근해야하기 때문입니다.
		Connection con = getConnection(); // svc.class를 만들면 메서드안에는 무조건 Connection 객체가 사용!
						
		// 2. CommunityTmiDTO 클래스로부터 CommunityDAO 인스턴스를 가져와서 저장합니다. - 공통작업
		// => 싱글톤 디자인 패턴으로 생성된 CommunityDAO 인스턴스 가져오기
		CommunityDAO dao = CommunityDAO.getInstance();
						
		// 3. CommunityDAO 인스턴스에 Connection 객체를 전달합니다. - 공통작업
		dao.setConnection(con);
				
		// CommunityDAO 객체의 updateTmiReadCount() 메서드를 호출하여 1개 게시물의 상세 정보 조회 작업 수행
		// => 파라미터 : idx, 리턴타입: x
		dao.updateTmiReadCount(idx);
		
		// 조회수 증가 작업 commit
		commit(con);
						
		// 4. Connection 객체 반환 - 공통작업
		close(con);
				
	}
	
}
