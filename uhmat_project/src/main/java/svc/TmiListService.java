package svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CommunityDAO;
import vo.ActionForward;
import vo.CommunityTmiDTO;
import static db.JdbcUtil.*;

public class TmiListService {

	public int getTmiListCount() {
		System.out.println("getTmiLisctCount");
		// 1. 리턴할 데이터를 저장할 변수 선언
		int tmiListCount = 0;
		
		// 2. Connection 객체 가져오기 - 공통
		Connection con = getConnection();
		
		// 3. CommunityDAO 객체 가져오기 - 공통
		CommunityDAO dao = CommunityDAO.getInstance();
		
		// 4. CommunityTmiDTO 객체에 Connection 객체 전달
		dao.setConnection(con);
		
		// 5. BoardDAO 객체의 selecttmiListCount() 메서드를 호출하여 전체 게시물 수 조회
		tmiListCount = dao.selectTmiListCount();
				
		// 6. Connection 객체 반환 - 공통
		// static import로 포함시킨 close() 메서드 호출
		close(con);
		
		
		return tmiListCount;
	}

	public ArrayList<CommunityTmiDTO> getTmiBoardList(int pageNum, int listLimit) {
		System.out.println("getTmiBoardList");
		ArrayList<CommunityTmiDTO> tmiBoardList = null;
		
		// 1. Connection 객체 가져오기 - 공통
		Connection con = getConnection();
				
		// 2. CommunityDAO 객체 가져오기 - 공통
		CommunityDAO dao = CommunityDAO.getInstance();
		
		// 3. BoardDAO 객체에 Connetion 객체 전달하기 - 공통
		dao.setConnection(con);
		
		// 4. BoardDAO 객체의 selectTmiBoardList() 메서드를 호출하여 게시물 목록 조회
		// => 파라미터 : 현재페이지번호(pageNum), 페이지 당 게시물 수(listLimit)
		// 	  리턴타입 : ArrayList<CommunityDTO> getTmiboardList
		tmiBoardList = dao.selectTmiBoardList(pageNum, listLimit); 
		System.out.println(tmiBoardList);
		
		// 5. Connection 객체 반환 - 공통
		close(con);
		
		return tmiBoardList;
	}

}
