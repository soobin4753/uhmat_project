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
		
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
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
					String tagResult = "";
					// 게시물 정보 저장
					dto.setIdx(rs.getInt("idx"));
					dto.setRes_name(rs.getString("res_name"));
					dto.setNickname(rs.getString("nickname"));
					dto.setSubject(rs.getString("subject"));
					dto.setPhoto(rs.getString("photo"));
					dto.setContent(rs.getString("content"));
					dto.setLikes(rs.getInt("likes"));
					dto.setRating(rs.getFloat("rating"));
					
					
					String sql2 = "SELECT tag_name FROM tag_relation WHERE review_idx=?";
					pstmt2  = con.prepareStatement(sql2);
					pstmt2.setInt(1, dto.getIdx());
					rs2 = pstmt2.executeQuery();
					
					tagResult = "#";
					StringJoiner joiner = new StringJoiner("#");
					while(rs2.next()) {
						
						joiner.add(rs2.getString("tag_name"));;
					}
					
					tagResult = tagResult + joiner;
					System.out.println(tagResult);
//				System.out.println(dto);
					dto.setTag_name(tagResult);
					reviewList.add(dto);
					close(rs2);
					close(pstmt2);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("SQL 구문작성오류 - selectReviewList()");
			} finally {
				close(rs);
				close(pstmt);
			}
		return reviewList;
	}

	public ReviewBoardDTO selectReviewBoardList(int idx) {
		ReviewBoardDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		String tagResult = "";
		try {
			String sql = "SELECT * FROM reviewboard WHERE idx =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new ReviewBoardDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setContent(rs.getString("content"));
				dto.setLikes(rs.getInt("likes"));
				dto.setNickname(rs.getString("nickname"));
				dto.setPhoto(rs.getString("photo"));
				dto.setRating(rs.getFloat("rating"));
				dto.setRes_name(rs.getString("res_name"));
				dto.setSubject(rs.getString("subject"));
				
				String sql2 = "SELECT tag_name FROM tag_relation WHERE review_idx=?";
				pstmt2  = con.prepareStatement(sql2);
				pstmt2.setInt(1, dto.getIdx());
				rs2 = pstmt2.executeQuery();
				
				tagResult = "#";
				StringJoiner joiner = new StringJoiner("#");
				while(rs2.next()) {
					
					joiner.add(rs2.getString("tag_name"));;
				}
				
				tagResult = tagResult + joiner;
					System.out.println(tagResult);
					dto.setTag_name(tagResult);
					close(rs2);
					close(pstmt2);
			}
//				System.out.println(dto); DTO값을 잘 전달 받았는지 체크!

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문작성 및 실행오류 - selectReviewBoardList(idx) - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return dto;
	}
	
	public int insertReview(ReviewBoardDTO dto, String tag) {
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
			
			// 전달 받은 tag를 #와 공백을 기준으로 나눔
			
			tag = tag.replaceAll("#", "");
			String str[] = tag.split(" ");
			
			for(String s : str) {
				sql="INSERT INTO tag_relation VALUES(?, ?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.setString(2, s);
				System.out.println(s);
				insertCount = pstmt.executeUpdate();
			}
			
			sql="INSERT INTO reviewboard VALUES(";
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
	public int updateReview(ReviewBoardDTO dto) {

		
		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE reviewboard SET res_name=?, subject=?, rating=?, content=?, photo=? "
				+ "WHERE idx=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getRes_name());
			pstmt.setString(2, dto.getSubject());
			pstmt.setFloat(3, dto.getRating());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getPhoto());
			pstmt.setInt(6, dto.getIdx());
			
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			
			System.out.println("SQL 구문 작성 및 실행오류 - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateCount;
	}
	// override-----------------------------------------------------------------------------
	public int updateReview(int idx, int updateLike, int totalLike) {
		int updateCount = 0;
		String sql = "";
		PreparedStatement pstmt = null;
	
		if (updateLike == 1) {
			sql = "UPDATE reviewboard SET likes=likes + 1 WHERE idx=?";
			System.out.println("리뷰 좋아요 증가");
		} else {
			if(totalLike > 0) {
			sql = "UPDATE reviewboard SET likes=likes - 1 WHERE idx=?";
			} else {
				sql = "UPDATE reviewboard SET likes = 0 WHERE idx=?";
			}
			System.out.println("리뷰 좋아요 취소");
		}
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("sql 구문 작성 및 실행 오류 - updateReview - (reviewBoard좋아요");
		}

		return updateCount;
	}
	
	public boolean checkPassword(String pass) {
		// 비밀번호를 확인해야함 member에서
		boolean isWriter = false;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT FROM member WHERE pass=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pass);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				isWriter = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 실행 및 구문 작성오류 - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return isWriter;
	}
	public int deleteReview(int idx) {
		int deleteCount = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM reviewBoard WHERE idx=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL구문 작성 및 실행 오류 - " + e.getMessage());
			
		} finally {
			close(pstmt);
		}
		
		return deleteCount;
	}
	
	
	public int updateLike(int idx, String nickname) {
		int updateLike = 0;
		boolean isSelect = false;
		/*
		 * idx와 nickname에 해당하는 data를 조회시 return 값이 0일경우
		 * likes 에 + 1을 update
		 * 
		 * return 값이 있을 경우
		 * 해당데이터 delete
		 * 
		 * 둘중의 하나의 처리가 완료 되었을 경우 updateLike = 1;
		 */
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 1. 좋아요가 있는지 없는지 조회
		try {
			String sql = "SELECT * FROM review_likes WHERE idx=? AND nickname=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setString(2, nickname);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				isSelect = true; 
			}
			
			close(pstmt);
			close(rs);
			
			if(!isSelect) { // likes 가 없을 경우, insert
				sql = "INSERT INTO review_likes VALUES(?, 1, ?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, idx);
				pstmt.setString(2, nickname);
				updateLike = pstmt.executeUpdate();
				System.out.println("review_likes - 좋아요 추가");
			
			} else { // likes가 있는 경우 기존의 data를 delete
				
				sql="DELETE FROM review_likes WHERE idx=? AND nickname=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, idx);				
				pstmt.setString(2, nickname);
				updateLike = pstmt.executeUpdate();
				updateLike = -1;
				System.out.println("review_likes - 좋아요 취소");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("sql 구문 실행 및 작성오류 - review_likes update");
		} finally {
			
			close(pstmt);
			
		}
		return updateLike;
	}
	public int selectLikeCount(int idx) {
		
		int totalLike = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT likes FROM reviewboard WHERE idx =?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalLike = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("SQL 구문작성 및 오류 " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return totalLike;
	}
	
}
