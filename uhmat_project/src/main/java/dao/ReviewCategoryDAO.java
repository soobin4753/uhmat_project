package dao;

import java.sql.*;
import java.util.*;

import vo.*;

import static db.JdbcUtil.*;

public class ReviewCategoryDAO {
	private static ReviewCategoryDAO instance = new ReviewCategoryDAO();
	private static Connection con;
	public void setConnection(Connection con) {
		this.con = con; 
	} 
	private ReviewCategoryDAO() {}
	public static ReviewCategoryDAO getInstance() {
		return instance;
	}
	
	public int selectReviewListCount() {
		
		int listCount = 0;
		
		//구문 작성 전 Setting
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 구문 작성 및 실행
		try {
			String sql = "SELECT COUNT(?) FROM reviewboard";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 1);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();

			System.out.println("SQL 구문 작성 및 실행 오류 - selectReviewListCount()" + e.getMessage());


		} finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}
	public ArrayList<ReviewBoardDTO> selectReviewBoardList(int pageNum, int listLimit) {
		
		ArrayList<ReviewBoardDTO> reviewList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 시작행 번호 계산
		int startRow = (pageNum  - 1) * listLimit;
		
		/*************************************
		 * 댓글 부분 구현될 경우 새로 sql 문 작성 해야 함
		 ****************************************/
		
		String sql = "SELECT * FROM reviewboard "
				+ "ORDER BY idx DESC "
				+ "LIMIT ?, ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, listLimit);
			
			rs = pstmt.executeQuery();
			
			reviewList = new ArrayList<ReviewBoardDTO>();
			
			while(rs.next()) {
				ReviewBoardDTO dto = new ReviewBoardDTO();
				// 게시물 정보 저장
				dto.setIdx(rs.getInt("idx"));
				dto.setRes_name(rs.getString("res_name"));
				dto.setNickname(rs.getString("nickname"));
				dto.setSubject(rs.getString("subject"));


				dto.setPhoto(rs.getString("photo"));


				dto.setContent(rs.getString("content"));
				dto.setLikes(rs.getInt("likes"));
				dto.setRating(rs.getFloat("rating"));
				
				System.out.println(dto);
				
				reviewList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();


			System.out.println("SQL 구문 작성 및 실행 오류 selectReviewBoardList()- " + e.getMessage());


		} finally {
			close(rs);
			close(pstmt);
		}
		
		return reviewList;
	}


	public int insertReview(ReviewBoardDTO dto) {
		int insertCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int num = 1;
		// 1. idx 값을 새 번호로 잡아주기
		try {
			String sql = "SELECT MAX(idx) FROM reviewboard";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt(1) + 1;
			}
			
			//pstmt,rs 사용완료후 반환
			close(rs);
			close(pstmt);
			
			//Insert작업 수행
			sql = "INSERT INTO reviewboard VALUES(?,?,?,?,?,?,0,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, dto.getRes_name());
			pstmt.setString(3, dto.getNickname());
			pstmt.setString(4, dto.getSubject());
			pstmt.setString(5, dto.getPhoto());
			pstmt.setString(6, dto.getContent());
			pstmt.setFloat(7, dto.getRating());
			
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL구문 작성및 실행 오류 insertReview() - " + e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return insertCount;
	}


	
}
