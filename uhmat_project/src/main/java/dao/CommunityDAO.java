package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
				sql = "INSERT INTO community_mate VALUES (?,?,?,?,0,now())";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.setString(2, mate.getNickname());
				pstmt.setString(3, mate.getSubject());
				pstmt.setString(4, mate.getContent());
				
				
				insertCount = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQL 구문 오류 발생! - insertBoard() - " + e.getMessage());
			} finally {
				// DB 자원 반환(주의! Connection 객체는 반환 금지! => Service 클래스에서 반환)
				close(rs);
				close(pstmt);
			}
			
			
			
			return insertCount;
		}
}
