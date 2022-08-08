package dao;


import static db.JdbcUtil.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;


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
				
				// 전달받은 데이터를 board 테이블에 INSERT

				sql = "INSERT INTO community_mate VALUES(?,?,?,?,0,CURRENT_TIMESTAMP)";

				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.setString(2, mate.getNickname());
				pstmt.setString(3, mate.getSubject());
				pstmt.setString(4, mate.getContent());
				
				
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
		
		
		
}
