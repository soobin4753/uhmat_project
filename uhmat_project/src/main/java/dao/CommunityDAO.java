package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.CommentDTO;
import vo.MateDTO;

public class CommunityDAO {

	// 1. 멤버변수 선언 및 인스턴스 생성
		private static CommunityDAO instance = new CommunityDAO();
		// 2. 생성자 정의
		private CommunityDAO() {}
		// 3. Getter 정의(자동 생성)
		public static CommunityDAO getInstance() {
			return instance;
		}
		// ----------------------------------------------------------------------------------------
		// 외부(Service 클래스)로부터 Connection 객체를 전달받아 관리하기 위해
		// Connection 타입 멤버변수와 Setter 메서드 정의
		private Connection con;
		public void setConnection(Connection con) {
			this.con = con;
		}
		// ----------------------------------------------------------------------------------------
		// 글 갯수 조회
		// 전체 게시물 수를 조회할 mateCount() 메서드 정의
		// => 파라미터 : 없음   리턴타입 : int(listCount)
		public int selectMateCount() {
			
			int listCount = 0;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				// 3단계
				String sql = "SELECT COUNT(*) FROM community_mate";
				pstmt = con.prepareStatement(sql);
				
				// 4단계
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					listCount = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQL 구문 오류 발생! - " + e.getMessage());
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return listCount;
		}
		
		public ArrayList<MateDTO> selectMateList(int pageNum, int listLimit) {
			
			ArrayList<MateDTO> mateList = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			// 현재 페이지 번호를 활용하여 조회 시 시작행 번호 계산
			int startRow = (pageNum - 1) * listLimit;
			
			try {
				// 답글에 대한 처리 과정 추가
				String sql = "SELECT * FROM community_mate ORDER BY idx desc LIMIT ?,?";
						
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, listLimit);
				
				rs = pstmt.executeQuery();
				
				// 전체 게시물을 저장할 ArrayList<MateDTO> 객체 생성
				mateList = new ArrayList<MateDTO>();
				
				// while 문을 사용하여 조회 결과에 대한 반복 작업 수행
				while(rs.next()) {
					// 1개 게시물 정보를 저장할 MateDTO 객체 생성
					MateDTO mate = new MateDTO();
					// 게시물 정보 저장
					mate.setIdx(rs.getInt("idx"));
					mate.setNickname(rs.getString("nickname"));
					mate.setSubject(rs.getString("subject"));
					mate.setContent(rs.getString("content"));
					mate.setReadcount(rs.getInt("readcount"));
					mate.setDatetime(rs.getTimestamp("datetime"));
					System.out.println(mate);
					
					// 전체 게시물 정보를 저장하는 ArrayList 객체에 1개 게시물 정보 MateDTO 객체 추가
					mateList.add(mate);
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQL 구문 오류 발생! - selectMateList()");
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return mateList;
		}
		// -----------------------------------------------------------------------------------------
		// 글쓰기 작업 수행
		public int mateInsertCount(MateDTO mate) {
			
			int insertCount = 0;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int num = 1; // 새 글 번호를 저장할 변수
			
			
			try {
				// 새 글 번호로 사용될 번호를 생성하기 위해 기존 게시물의 가장 큰 번호 조회
				// => 조회 결과가 있을 경우 해당 번호 + 1 값을 새 글 번호로 저장
				String sql = "SELECT MAX(idx) FROM community_mate";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					num = rs.getInt(1) + 1; // 조회된 가장 큰 번호 + 1 값을 새 글 번호로 저장
					
				}
				
				// 사용 완료된 PreparedStatement 객체를 먼저 반환
				close(pstmt);
				
//				// 닉네임 조회
//				sql ="SELECT member.nickname FROM community_mate mate JOIN member member ON mate.nickname = member.nickname";
//				pstmt = con.prepareStatement(sql);
//				rs = pstmt.executeQuery();
//				
//				if(rs.next()) {
//					mate = new MateDTO();
//					mate.setNickname(rs.getString("nickname"));
//				}
//				
//				close(pstmt);
				
				// 전달받은 데이터를 board 테이블에 INSERT
				sql = "INSERT INTO community_mate VALUES(?,?,?,?,0,CURRENT_TIMESTAMP)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.setString(2, mate.getNickname());
				pstmt.setString(3, mate.getSubject());
				pstmt.setString(4, mate.getContent());
				System.out.println(mate);
				
				insertCount = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQL 구문 오류 발생! - mateInsertCount() - " + e.getMessage());
			} finally {
				close(rs);
				close(pstmt);
			}
			
			
			
			return insertCount;
		}
		// -------------------------------------------------------------------------------
		// 조회수 증가 작업을 처리하는 increaseReadcount() 메서드
		public void increaseReadcount(int idx) {
			
			PreparedStatement pstmt = null;
			
			try {
				String sql = "UPDATE community_mate SET readcount=readcount+1 WHERE idx=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, idx);
				
				pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 - increaseReadcount() : " + e.getMessage());
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			
		}
		
		// 1개 게시물의 상세 정보 조회 작업 수행하는 getMate() 메서드
		public MateDTO getMate(int idx) {

			MateDTO mate = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT * FROM community_mate WHERE idx=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, idx);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					mate = new MateDTO();
					mate.setIdx(rs.getInt("idx"));
					mate.setNickname(rs.getString("nickname"));
					mate.setSubject(rs.getString("subject"));
					mate.setContent(rs.getString("content"));
					mate.setReadcount(rs.getInt("readcount"));
					mate.setDatetime(rs.getTimestamp("datetime"));
					System.out.println(mate);
				}
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 - getMate() : " + e.getMessage());
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			
			
			return mate;
		}
		// ---------------------------------------------------------------------
		// 글 수정
		public int updateMate(MateDTO mate) {
			
			int updateCount = 0;
			
			PreparedStatement pstmt = null;
			
			try {
				String sql = "UPDATE community_mate SET subject=?, content=? WHERE idx=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, mate.getSubject());
				pstmt.setString(2, mate.getContent());
				pstmt.setInt(3, mate.getIdx());
				
				updateCount = pstmt.executeUpdate();
				System.out.println(updateCount);
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQL 구문 오류 - updateMate() : " + e.getMessage());
			} finally {
				close(pstmt);
			}
			
			return updateCount;
		}
		// --------------------------------------------------
		public int deleteMate(int idx) {

			int deleteCount = 0;
			
			PreparedStatement pstmt = null;
			
			try {
				String sql = "DELETE FROM community_mate WHERE idx=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, idx);
				
				deleteCount = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQL 구문 오류 - deleteMate() : " + e.getMessage());
			} finally {
				close(pstmt);
			}
			
			return deleteCount;
		}
		// -----------------------------------------------------------------------------
		// 댓글
		public int insertReplyMate(CommentDTO mateComment) {
			
			int insertCount = 0;
			
			PreparedStatement pstmt = null, pstmt2 = null;
			ResultSet rs = null;
			
			int num = 1;
			
			try {
				// 새 글 번호로 사용될 번호를 생성하기 위해 기존 게시물의 가장 큰 번호 조회
				// => 조회 결과가 있을 경우 해당 번호 + 1 값을 새 글 번호로 저장
				String sql = "SELECT MAX(idx) FROM mate_reply";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					num = rs.getInt(1) + 1; // 조회된 가장 큰 번호 + 1 값을 새 글 번호로 저장
				}
				
				
				// 기존 답글들에 대한 순서번호(re_seq) 증가 작업 처리
				// => 원본글의 참조글번호(re_ref) 와 같고(같은 레코드들 중에서)
				//    원본글의 순서번호(re_seq)보다 큰 레코드들의 순서번호를 1씩 증가시키기
				sql = "UPDATE mate_reply SET re_seq=re_seq+1 WHERE re_ref=? AND re_seq>?";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setInt(1, mateComment.getRe_ref());
				pstmt2.setInt(2, mateComment.getRe_seq());
				pstmt2.executeUpdate();
				
				// 답글을 mate_reply 테이블에 INSERT 작업
				sql = "INSERT INTO mate_reply VALUES(?,?,?,?,?,?,CURRENT_TIMESTAMP,?)";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setInt(1, num);
				pstmt2.setString(2, mateComment.getNickname());
				pstmt2.setString(3, mateComment.getContent());
				pstmt2.setInt(4, mateComment.getRe_ref());
				pstmt2.setInt(5, mateComment.getRe_lev() + 1);
				pstmt2.setInt(6, mateComment.getRe_seq() + 1);
				pstmt2.setInt(7, mateComment.getBoard_idx());
//				System.out.println(mateComment);
				
				insertCount = pstmt2.executeUpdate();
				
				// 22-08-09 미완성임
				
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 - insertReplyMate() : " + e.getMessage());
				e.printStackTrace();
			} finally {
				close(pstmt2);
				close(pstmt);
				close(rs);
			}
			
			
			
			return insertCount;
		}
		
		
		
}
