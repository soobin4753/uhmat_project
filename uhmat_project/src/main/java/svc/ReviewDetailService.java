package svc;

import vo.ReviewBoardDTO;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.ReviewCategoryDAO;
public class ReviewDetailService {

	public ReviewBoardDTO getReviewBoard(int idx) {
		System.out.println("ReviewDetailService");
		ReviewBoardDTO dto = null;
		
		// 1. Connction Pool
		Connection con = getConnection();
		
		// 2. DAO 객체 가져오기
		ReviewCategoryDAO dao = ReviewCategoryDAO.getInstance();
		
		// 3. dao 객체에 커넥션 넘겨주기
		dao.setConnection(con);
		
		
		// 4. dao.selectReviewBoardList(idx)
		dto = dao.selectReviewBoardList(idx);
		
		// 5. connection pool 반환
		close(con);
		
		return dto;
	}

		

}
