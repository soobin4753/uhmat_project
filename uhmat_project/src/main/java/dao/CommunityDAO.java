package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.MateReplyDTO;
import vo.RecipeDTO;
import vo.RecipeReplyDTO;
import vo.CommunityTmiDTO;

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
				sql = "INSERT INTO community_mate VALUES(?,?,?,?,0,now())";
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
		// ----------------------------------------------------------------------------
		// 게시글 삭제하면 댓글도 삭제하는 로직
				public int deleteMateReply(int idx) {
					
					int deleteMateReply = 0;
					
					PreparedStatement pstmt = null;
					
					try {
						String sql = "DELETE FROM mate_reply WHERE board_idx=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, idx);
						deleteMateReply = pstmt.executeUpdate();
						
					} catch (SQLException e) {
						e.printStackTrace();
						System.out.println("sql구문 오류 - deleteMateReply" + e.getMessage());
					}
					
					return deleteMateReply;
				}
		
		// -----------------------------------------------------------------------------
		// 댓글
		public int insertReplyMate(MateReplyDTO mateReply) {
			
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
//				sql = "UPDATE mate_reply SET re_seq=re_seq+1 WHERE re_ref=? AND re_seq>?";
//				pstmt2 = con.prepareStatement(sql);
//				pstmt2.setInt(1, mateReply.getRe_ref());
//				pstmt2.setInt(2, mateReply.getRe_seq());
//				pstmt2.executeUpdate();
				
				// 답글을 mate_reply 테이블에 INSERT 작업
				sql = "INSERT INTO mate_reply VALUES(?,?,?,?,?,?,now(),?)";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setInt(1, num);
				pstmt2.setString(2, mateReply.getNickname());
				pstmt2.setString(3, mateReply.getContent());
				pstmt2.setInt(4, num);
				pstmt2.setInt(5, 0);
				pstmt2.setInt(6, 0);
				pstmt2.setInt(7, mateReply.getBoard_idx());
//				System.out.println(mateReply);
				
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
		
		
		public ArrayList<MateReplyDTO> selectMateReply(int idx) {
	         System.out.println("CommunityDAO - selectMateReply");
	         System.out.println("MateReplyDAO - idx : " + idx);
	         ArrayList<MateReplyDTO> mateReplyList = null;
	         
	         PreparedStatement pstmt  = null;
	         ResultSet rs = null;
	         
	         try {
//	            String sql = "SELECT * FROM mate_reply WHERE board_idx=?";
	        	 String sql = "SELECT * FROM mate_reply WHERE board_idx=? ORDER BY re_ref DESC, re_seq ASC";
	            pstmt = con.prepareStatement(sql);
	            pstmt.setInt(1, idx);
	            
	            rs = pstmt.executeQuery();
	            
	            mateReplyList = new ArrayList<MateReplyDTO>();
	            
	            while(rs.next()) {
	               
	               MateReplyDTO mateReply = new MateReplyDTO();
	               mateReply.setBoard_idx(rs.getInt("board_idx"));
	               mateReply.setContent(rs.getString("content"));
	               mateReply.setDate(rs.getTimestamp("date"));
	               mateReply.setIdx(rs.getInt("idx"));
	               mateReply.setNickname(rs.getString("nickname"));
	               mateReply.setRe_lev(rs.getInt("re_lev"));
	               mateReply.setRe_ref(rs.getInt("re_ref"));
	               mateReply.setRe_seq(rs.getInt("re_seq"));
//	               mateReply.setBoard_idx(rs.getInt("board_idx"));
//	               mateReply.setContent(rs.getString("content"));
//	               mateReply.setIdx(rs.getInt(idx));
//	               mateReply.setRe_lev(rs.getInt("re_lev"));
//	               mateReply.setRe_ref(rs.getInt("re_ref"));
//	               mateReply.setRe_seq(rs.getInt("re_seq"));
//	               mateReply.setDate(rs.getTimestamp("date"));
//	               mateReply.setNickname(rs.getString("nickname"));
//	               
	               mateReplyList.add(mateReply);
	            }
	            System.out.println("mateReplyList :" +mateReplyList );
	            
	         } catch (SQLException e) {
	            System.out.println("SQL 구문 오류 - selectMateReply() : " + e.getMessage());
	            e.printStackTrace();
	         } finally {
	            close(rs);
	            close(pstmt);
	         }
	         return mateReplyList;
	      }
		// ------------------------------------------------------------------------------
		// 댓글 삭제
		public int deleteReplyMate(int reply_idx, String nickname) {

			int deleteCount = 0;
			
			PreparedStatement pstmt = null;
			
			try {
				String sql = "DELETE FROM mate_reply WHERE idx=? AND nickname=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, reply_idx);
				pstmt.setString(2, nickname);
				
				deleteCount = pstmt.executeUpdate();
				
				System.out.println("deleteReplyMate - " + deleteCount);
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 - deleteReplyMate() : " + e.getMessage());
				e.printStackTrace();
			}
			
			return deleteCount;
		}
		public int modifyReplyMate(int reply_idx, String nickname, String content) {
			
			int modifyCount = 0;
			
			PreparedStatement pstmt = null;
			
			try {
				String sql = "UPDATE mate_reply SET content=? where idx=? AND nickname=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, content);
				pstmt.setInt(2, reply_idx);
				pstmt.setString(3, nickname);
				
				modifyCount = pstmt.executeUpdate();
				
				System.out.println("modifyReplyMate - " + modifyCount);
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 - deleteReplyMate() : " + e.getMessage());
				e.printStackTrace();
			}
			

			return modifyCount;
		}
		// ----------------------------------------------------------
		// 대댓글
		public int insertMateRereply(MateReplyDTO mateReply) {
			System.out.println("CommunityDAO - insertMateRereply");
			
			int mateRereplyInsertCount = 0;
			
			PreparedStatement pstmt = null, pstmt2 = null, pstmt3 = null;
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
				
//				 기존 답글들에 대한 순서번호(re_seq) 증가 작업 처리
//				 => 원본글의 참조글번호(re_ref) 와 같고(같은 레코드들 중에서)
//				    원본글의 순서번호(re_seq)보다 큰 레코드들의 순서번호를 1씩 증가시키기
				sql = "UPDATE mate_reply SET re_seq=re_seq+1 WHERE re_seq>?";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setInt(1, mateReply.getRe_seq());
				mateRereplyInsertCount = pstmt2.executeUpdate();
				
				// 답글을 mate_reply 테이블에 INSERT 작업
				sql = "INSERT INTO mate_reply VALUES(?,?,?,?,?,?,now(),?)";
				pstmt3 = con.prepareStatement(sql);
				pstmt3.setInt(1, num);
				pstmt3.setString(2, mateReply.getNickname());
				pstmt3.setString(3, mateReply.getContent());
				pstmt3.setInt(4, mateReply.getRe_ref());
				pstmt3.setInt(5, mateReply.getRe_lev() + 1);
				pstmt3.setInt(6, mateReply.getRe_seq() + 1);
				pstmt3.setInt(7, mateReply.getBoard_idx());
//				System.out.println(mateReply);
				mateRereplyInsertCount = pstmt3.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 - insertReplyMate() : " + e.getMessage());
				e.printStackTrace();
			} finally {
				close(pstmt3);
				close(pstmt2);
				close(pstmt);
				close(rs);
			}
			
			return mateRereplyInsertCount;
			
		}
		
		public MateReplyDTO selectRereply(int reply_idx) {

			MateReplyDTO mateReply = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT * FROM mate_reply WHERE idx=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, reply_idx);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					mateReply = new MateReplyDTO();
					mateReply.setBoard_idx(rs.getInt("board_idx"));
				    mateReply.setContent(rs.getString("content"));
				    mateReply.setDate(rs.getTimestamp("date"));
				    mateReply.setIdx(rs.getInt("idx"));
				    mateReply.setNickname(rs.getString("nickname"));
				    mateReply.setRe_lev(rs.getInt("re_lev"));
				    mateReply.setRe_ref(rs.getInt("re_ref"));
				    mateReply.setRe_seq(rs.getInt("re_seq"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQL 구문 오류 - selectRereply() : " + e.getMessage());
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return mateReply;
		}
		
		
		// ============================================================
		// TMI 댓글 메서드 시작
		
//		// TMI 댓글 작성 메서드
//		public int insertTmiReply(TmiReplyDTO tmiReply) {
//			int tmiReplyInsertCount = 0;
//			
//			PreparedStatement pstmt = null;
//			ResultSet rs = null;
//			
//			int num = 1;
//			
//			try {
//				String sql = "SELECT MAX(idx) FROM tmi_reply";
//				pstmt = con.prepareStatement(sql);
//				rs = pstmt.executeQuery();
//				
//				if(rs.next()) {
//					num = rs.getInt(1);
//				}
//				
//				close(pstmt);
//				
//				sql = "INSERT INTO tmi_reply VALUES(?,?,?,?,?)";
//				pstmt = con.prepareStatement(sql);
//				pstmt.setInt(1, num);
//				pstmt.setString(2, tmiReply.getNickname());
//				pstmt.setString(3, tmiReply.getContent());
//				pstmt.setTimestamp(4, tmiReply.getDate());
//				pstmt.setInt(5, tmiReply.getBoard_idx());
//				
//				tmiReplyInsertCount = pstmt.executeUpdate();
//				
//			} catch (SQLException e) {
//				System.out.println("SQL 구문 작성 오류! - insertTmiReply() " + e.getMessage());
//				e.printStackTrace();
//			} finally {
//				close(rs);
//				close(pstmt);
//			}
//			
//			return tmiReplyInsertCount;
//		}
		// tmi게시판 전체 게시물 수를 조회할 selcetTmiListCount() 메서드 정의
		// => 파라미터 : 없음,	리턴타입 : int(tmilistCount)
		public int selectTmiListCount() {
			System.out.println("CommunityDAO - selectTmiListCount() 호출!");
			int tmiListCount = 0;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT COUNT(*) FROM community_tmi";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					tmiListCount = rs.getInt(1);
					
				}
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 발생! - selectTmiListCount() " + e.getMessage());
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return tmiListCount;
		}
		// tmi게시판 목록을 조회하는 selectTmiBoardList() 메서드 정의
		// => 파라미터 : 현재페이지번호(pageNum), 페이지 당 게시물 수 (listLimit)
		// 	  리턴타입 : ArrayList<CommunityDTO> tmiBoardList
		public ArrayList<CommunityTmiDTO> selectTmiBoardList(int pageNum, int listLimit) {
			System.out.println("CommunityDAO - selectTmiBoardList() 호출!");
			ArrayList<CommunityTmiDTO> tmiBoardList = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			// 현재 페이지 번호를 활용하여 조회 시 시작행 번호를 계산
			int startRow = (pageNum - 1) * listLimit;
			
			try {
				String sql = "SELECT * FROM community_tmi ORDER BY idx DESC LIMIT ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, listLimit);
				
				rs = pstmt.executeQuery();
				
				tmiBoardList = new ArrayList<CommunityTmiDTO>();
				
				// while문을 사용하여 조회 결과에 대한 반복 작업을 수행.
				while(rs.next()) {
					// tmi게시판 1개 게시물 정보를 저장할 CommunityTmiDTO 의 객체를 생성.
					CommunityTmiDTO tmiBoard = new CommunityTmiDTO();
					tmiBoard.setIdx(rs.getInt("idx"));
					tmiBoard.setNickname(rs.getString("nickname"));
					tmiBoard.setSubject(rs.getString("subject"));
					tmiBoard.setDatetime(rs.getTimestamp("datetime"));
					tmiBoard.setReadcount(rs.getInt("readcount"));
					
					tmiBoardList.add(tmiBoard);
				}
				
			} catch (SQLException e) {
				System.out.println("SQL구문 오류 발생! - selectTmiBoardList" + e.getMessage());
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return tmiBoardList;
		}
		// CommunityDAO 객체(인스턴스)의 insertTmiBoard() 메서드를 호출하여 글쓰기 작업을 수행 한 후 결과를 리턴받습니다.
		// => 파라미터 : CommunityDAO 객체(board) 리턴타입 : int(insertCount)
		public int insertTmiBoard(CommunityTmiDTO tmiBoard) {
			System.out.println("CommunityDAO - insertTmiBoard() 호출!");
			
			int tmiInsertCount = 0;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int num = 1;
			
			try {
				String sql = "SELECT MAX(idx) FROM community_tmi";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					num = rs.getInt(1) + 1;
				}
				
				close(pstmt);
				
				sql = "INSERT INTO community_tmi VALUES(?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.setString(2, tmiBoard.getNickname());
				pstmt.setString(3, tmiBoard.getSubject());
				pstmt.setString(4, tmiBoard.getContent());
				pstmt.setInt(5, tmiBoard.getReadcount());
				pstmt.setTimestamp(6, tmiBoard.getDatetime());
				
				tmiInsertCount = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("SQL 구문 작성 오류! - insertTmiBoard() " + e.getMessage());
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return tmiInsertCount;
		}

		// tmi 글 상세정보를 조회하여 요청하는 selectTmiBoard() 메서드 정의
		// => 파라미터 : 글번호(tmiIdx)		리턴타입 : CommunityDTO(idx)
		public CommunityTmiDTO selectTmiBoard(int idx) {
			System.out.println("CommunityDAO - selectTmiBoard() 호출!");
			CommunityTmiDTO tmiBoard = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT * FROM community_tmi WHERE idx=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, idx);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					tmiBoard = new CommunityTmiDTO();
					tmiBoard.setIdx(rs.getInt("idx"));
					tmiBoard.setNickname(rs.getString("nickname"));
					tmiBoard.setSubject(rs.getString("subject"));
					tmiBoard.setContent(rs.getString("content"));
					tmiBoard.setDatetime(rs.getTimestamp("datetime"));
					tmiBoard.setReadcount(rs.getInt("readcount"));
					System.out.println(tmiBoard);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQL 구문 오류 발생! - selectTmiBoard()" + e.getMessage());
				
			} finally {
				close(rs);
				close(pstmt);
			}	
			
			return tmiBoard;
		}

		// tmi 글의 조회수 증가 작업을 처리하는 updateTmiReadCount() 메서드 정의
		// => 파라미터 : idx(CommunityTmiDTO)
		public void updateTmiReadCount(int idx) {
			System.out.println("CommunityDAO - updateTmiReadCount() 호출!");
			PreparedStatement pstmt = null;
			
			try {
				String sql = "UPDATE community_tmi SET readcount=readcount+1 WHERE idx=?";
				// 조회수 증가 작업 update 구문 재 확인 필수!
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, idx);
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQL 구문 오류 발생! - updateReadcount()" + e.getMessage());
				
			} finally {
				close(pstmt);
			}
		}

		// 글 수정, 삭제 권한 판별을 수행하는 isTmiWriter() 메서드 정의
		public boolean isTmiWriter(int idx, String nickname) {
			System.out.println("CommunityDAO - isTmiWriter() 호출!");
			boolean isTmiWriter = false;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT * FROM community_tmi WHERE idx=? AND nickname=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, idx);
				pstmt.setString(2, nickname);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					// 조회 결과 있을 시 번호에 해당하는 닉네임이 일치
					isTmiWriter = true;
				}
				
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 발생! - isTmiWriter()" + e.getMessage());
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
			}
			
			return isTmiWriter;
		}

		// 글 수정 작업을 수행하는 updateTmiBoard() 메서드 정의
		public int updateTmiBoard(CommunityTmiDTO tmiBoard) {
			System.out.println("CommunityDAO - updateTmiBoard() 호출!");
			int updateTmiCount = 0;
			
			PreparedStatement pstmt = null;
			
			try {
				String sql = "UPDATE community_tmi SET nickname=?,subject=?,content=? WHERE idx=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, tmiBoard.getNickname());
				pstmt.setString(2, tmiBoard.getSubject());
				pstmt.setString(3, tmiBoard.getContent());
				pstmt.setInt(4, tmiBoard.getIdx());
				
				updateTmiCount = pstmt.executeUpdate();
				System.out.println(updateTmiCount);
				
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 발생! - updateTmiBoard()" + e.getMessage());
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return updateTmiCount;
		}

		public int deleteTmiBoard(int idx) {
			System.out.println("CommunityDAO - deleteTmiBoard() 호출!");
			int deleteTmiCount = 0;
			
			PreparedStatement pstmt = null;
			
			try {
				String sql = "DELETE FROM community_tmi WHERE idx=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, idx);
				
				deleteTmiCount = pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 - deleteTmiBoard() : " + e.getMessage());
				e.printStackTrace();
			}
			
		

			int deleteCount = 0;
			
			
			
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
		
		
		// ============================================================================= recipe
		public int insertRecipe(RecipeDTO recipe) {
			
			int insertCount = 0;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int num = 1;
			
			try {
				// 새 글 번호로 사용될 번호를 생성하기 위해 기존 게시물의 가장 큰 번호 조회
				// => 조회 결과가 있을 경우 해당 번호 + 1 값을 새 글 번호로 저장
				String sql = "SELECT MAX(idx) FROM community_recipe";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					num = rs.getInt(1) + 1;
				}
				
				close(pstmt);
				
				// 전달받은 데이터를 community_recipe 테이블에 INSERT
				sql = "INSERT INTO community_recipe VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now())";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.setString(2, recipe.getNickname());
				pstmt.setString(3, recipe.getSubject());
				pstmt.setString(4, recipe.getContent());
				pstmt.setInt(5, 0);
				pstmt.setString(6, recipe.getOriginal_File1());
				pstmt.setString(7, recipe.getReal_File1());
				pstmt.setString(8, recipe.getOriginal_File2());
				pstmt.setString(9, recipe.getReal_File2());
				pstmt.setString(10, recipe.getOriginal_File3());
				pstmt.setString(11, recipe.getReal_File3());
				pstmt.setString(12, recipe.getOriginal_File4());
				pstmt.setString(13, recipe.getReal_File4());
				pstmt.setString(14, recipe.getOriginal_File5());
				pstmt.setString(15, recipe.getReal_File5());
				
				insertCount = pstmt.executeUpdate();
						
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return insertCount;
		}
		// ------------------------------------------------------------
		// 레시피 글 갯수 조회
		public int selectRecipeCount() {
			int listCount = 0;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				// 3단계
				String sql = "SELECT COUNT(*) FROM community_recipe";
				pstmt = con.prepareStatement(sql);
				
				// 4단계
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					listCount = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQL 구문 오류 발생! selectRecipeCount - " + e.getMessage());
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return listCount;
		}
		
		public ArrayList<RecipeDTO> selectRecipeList(int pageNum, int listLimit) {
			
			ArrayList<RecipeDTO> recipeList = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			// 현재 페이지 번호를 활용하여 조회 시 시작행 번호 계산
			int startRow = (pageNum - 1) * listLimit;
			
			try {
				// 답글에 대한 처리 과정 추가
				String sql = "SELECT * FROM community_recipe ORDER BY idx DESC LIMIT ?,?";
						
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, listLimit);
				
				rs = pstmt.executeQuery();
				
				// 전체 게시물을 저장할 ArrayList<MateDTO> 객체 생성
				recipeList = new ArrayList<RecipeDTO>();
				
				// while 문을 사용하여 조회 결과에 대한 반복 작업 수행
				while(rs.next()) {
					// 1개 게시물 정보를 저장할 MateDTO 객체 생성
					RecipeDTO recipe = new RecipeDTO();
					// 게시물 정보 저장
					recipe.setContent(rs.getString("content"));
					recipe.setDatetime(rs.getTimestamp("datetime"));
					recipe.setIdx(rs.getInt("idx"));
					recipe.setNickname(rs.getString("nickname"));
					recipe.setOriginal_File1(rs.getString("original_File1"));
					recipe.setOriginal_File2(rs.getString("original_File2"));
					recipe.setOriginal_File3(rs.getString("original_File3"));
					recipe.setOriginal_File4(rs.getString("original_File4"));
					recipe.setOriginal_File5(rs.getString("original_File5"));
					recipe.setReadcount(rs.getInt("readcount"));
					recipe.setReal_File1(rs.getString("real_File1"));
					recipe.setReal_File2(rs.getString("real_File2"));
					recipe.setReal_File3(rs.getString("real_File3"));
					recipe.setReal_File4(rs.getString("real_File4"));
					recipe.setReal_File5(rs.getString("real_File5"));
					recipe.setSubject(rs.getString("subject"));
					System.out.println(recipe);
					
					// 전체 게시물 정보를 저장하는 ArrayList 객체에 1개 게시물 정보 MateDTO 객체 추가
					recipeList.add(recipe);
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQL 구문 오류 발생! - selectRecipeList()");
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return recipeList;
		}
		
		// ------------------------------------------------------------
		// 레시피 글 조회수
		public void increaseRecipeReadcount(int idx) {
			PreparedStatement pstmt = null;
			
			try {
				String sql = "UPDATE community_recipe SET readcount=readcount+1 WHERE idx=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, idx);
				
				pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 - increaseRecipeReadcount() : " + e.getMessage());
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
		}
		
		// 1개 게시물의 상세 정보 조회 작업 수행하는 getRecipe() 메서드
		public RecipeDTO getRecipe(int idx) {

			RecipeDTO recipe = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT * FROM community_recipe WHERE idx=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, idx);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					recipe = new RecipeDTO();
					recipe.setContent(rs.getString("content"));
					recipe.setDatetime(rs.getTimestamp("datetime"));
					recipe.setIdx(rs.getInt("idx"));
					recipe.setNickname(rs.getString("nickname"));
					recipe.setOriginal_File1(rs.getString("original_File1"));
					recipe.setOriginal_File2(rs.getString("original_File2"));
					recipe.setOriginal_File3(rs.getString("original_File3"));
					recipe.setOriginal_File4(rs.getString("original_File4"));
					recipe.setOriginal_File5(rs.getString("original_File5"));
					recipe.setReadcount(rs.getInt("readcount"));
					recipe.setReal_File1(rs.getString("real_File1"));
					recipe.setReal_File2(rs.getString("real_File2"));
					recipe.setReal_File3(rs.getString("real_File3"));
					recipe.setReal_File4(rs.getString("real_File4"));
					recipe.setReal_File5(rs.getString("real_File5"));
					recipe.setSubject(rs.getString("subject"));
					System.out.println(recipe);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQL 구문 오류 - getRecipe() : " + e.getMessage());
			} finally {
				close(rs);
				close(pstmt);
			}
			
			
			return recipe;
		}
		// --------------------------------------------------------------
		// 레시피 글 삭제
		public int deleteRecipe(int idx, String nickname) {
			
			int deleteCount = 0;
			
			PreparedStatement pstmt = null;
			
			try {
				String sql = "DELETE FROM community_recipe WHERE idx=? AND nickname=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, idx);
				pstmt.setString(2, nickname);
				
				deleteCount = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQL 구문 오류 - deleteRecipe() : " + e.getMessage());
			} finally {
				close(pstmt);
			}
			
			return deleteCount;
		}
		// -------------------------------------------------------
		// 레시피 글 업데이트
		public int updateRecipe(RecipeDTO recipe) {
			System.out.println("CommunityDAO - updateRecipe");
			System.out.println("up : "+ recipe.toString() );
			int updateCount = 0;

			PreparedStatement pstmt = null;

			try {
				String sql = "UPDATE community_recipe SET subject=?,content=?,"
						+ "original_File1=?,original_File2=?,original_File3=?,original_File4=?,original_File5=?,"
						+ "real_File1=?,real_File2=?,real_File3=?,real_File4=?,real_File5=? "
						+ "WHERE idx=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, recipe.getSubject());
				pstmt.setString(2, recipe.getContent());
				pstmt.setString(3, recipe.getOriginal_File1());
				pstmt.setString(4, recipe.getOriginal_File2());
				pstmt.setString(5, recipe.getOriginal_File3());
				pstmt.setString(6, recipe.getOriginal_File4());
				pstmt.setString(7, recipe.getOriginal_File5());
				pstmt.setString(8, recipe.getReal_File1());
				pstmt.setString(9, recipe.getReal_File2());
				pstmt.setString(10, recipe.getReal_File3());
				pstmt.setString(11, recipe.getReal_File4());
				pstmt.setString(12, recipe.getReal_File5());
				pstmt.setInt(13, recipe.getIdx());
				
				updateCount = pstmt.executeUpdate();
				System.out.println("레시피수정DAO의 Count" + updateCount);

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQL 구문 오류 - updateRecipe() : " + e.getMessage());
			} finally {
				close(pstmt);
			}

			return updateCount;
		}
		// -----------------------------------------------------
		// 레시피 검색
		public int selectRecipeSearchListcount(String keyword) {
			int listCount = 0;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT COUNT(*) FROM Community_recipe WHERE subject LIKE ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + keyword + "%" );
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					listCount = rs.getInt(1);
				}
				System.out.println("listCount(dao) : " + listCount);
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 발생! selectRecipeSearchListcount -  " + e.getMessage());
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return listCount;
		}
		
		public ArrayList<RecipeDTO> recipeSearchList(int pageNum, int listLimit, String keyword) {

			ArrayList<RecipeDTO> recipeSearchList = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int startRow = (pageNum- 1) * listLimit;
			
			try {
				String sql = "SELECT * FROM community_recipe WHERE subject LIKE ? ORDER BY idx DESC LIMIT ?,? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + keyword + "%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, listLimit);
				rs = pstmt.executeQuery();
				
				recipeSearchList = new ArrayList<RecipeDTO>();
				
				while(rs.next()) {
					RecipeDTO recipe = new RecipeDTO();
					recipe.setContent(rs.getString("content"));
					recipe.setDatetime(rs.getTimestamp("datetime"));
					recipe.setIdx(rs.getInt("idx"));
					recipe.setNickname(rs.getString("nickname"));
					recipe.setOriginal_File1(rs.getString("original_File1"));
					recipe.setOriginal_File2(rs.getString("original_File2"));
					recipe.setOriginal_File3(rs.getString("original_File3"));
					recipe.setOriginal_File4(rs.getString("original_File4"));
					recipe.setOriginal_File5(rs.getString("original_File5"));
					recipe.setReadcount(rs.getInt("readcount"));
					recipe.setReal_File1(rs.getString("real_File1"));
					recipe.setReal_File2(rs.getString("real_File2"));
					recipe.setReal_File3(rs.getString("real_File3"));
					recipe.setReal_File4(rs.getString("real_File4"));
					recipe.setReal_File5(rs.getString("real_File5"));
					recipe.setSubject(rs.getString("subject"));
					
					recipeSearchList.add(recipe);
					
				}
				System.out.println("recipeSearchList : " + recipeSearchList);
				
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 발생! -  " + e.getMessage());
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return recipeSearchList;
		}
		// ----------------------------------------------------------
		// 레시피 댓글
		public int insertReplyRecipe(RecipeReplyDTO recipeReply) {
			
				
				int insertCount = 0;
				
				PreparedStatement pstmt = null, pstmt2 = null;
				ResultSet rs = null;
				
				int num = 1;
				
				try {
					// 새 글 번호로 사용될 번호를 생성하기 위해 기존 게시물의 가장 큰 번호 조회
					// => 조회 결과가 있을 경우 해당 번호 + 1 값을 새 글 번호로 저장
					String sql = "SELECT MAX(idx) FROM recipe_reply";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						num = rs.getInt(1) + 1; // 조회된 가장 큰 번호 + 1 값을 새 글 번호로 저장
					}
					
					// 답글을 mate_reply 테이블에 INSERT 작업
					sql = "INSERT INTO recipe_reply VALUES(?,?,?,?,?,?,now(),?)";
					pstmt2 = con.prepareStatement(sql);
					pstmt2.setInt(1, num);
					pstmt2.setString(2, recipeReply.getNickname());
					pstmt2.setString(3, recipeReply.getContent());
					pstmt2.setInt(4, num);
					pstmt2.setInt(5, 0);
					pstmt2.setInt(6, 0);
					pstmt2.setInt(7, recipeReply.getBoard_idx());
					System.out.println(recipeReply);
					
					insertCount = pstmt2.executeUpdate();
					
					
					
				} catch (SQLException e) {
					System.out.println("SQL 구문 오류 - insertReplyRecipe() : " + e.getMessage());
					e.printStackTrace();
				} finally {
					close(pstmt2);
					close(pstmt);
					close(rs);
				}
				
				
				
				return insertCount;
		}
		public ArrayList<RecipeReplyDTO> selectRecipeReply(int idx) {
			
			System.out.println("CommunityDAO - selectRecipeReply");
	         System.out.println("RecipeReplyDAO - idx : " + idx);
	         ArrayList<RecipeReplyDTO> recipeReplyList = null;
	         
	         PreparedStatement pstmt  = null;
	         ResultSet rs = null;
	         
	         try {
	        	 String sql = "SELECT * FROM recipe_reply WHERE board_idx=? ORDER BY re_ref DESC, re_seq ASC";
	            pstmt = con.prepareStatement(sql);
	            pstmt.setInt(1, idx);
	            
	            rs = pstmt.executeQuery();
	            
	            recipeReplyList = new ArrayList<RecipeReplyDTO>();
	            
	            while(rs.next()) {
	               
	               RecipeReplyDTO recipeReply = new RecipeReplyDTO();
	               recipeReply.setBoard_idx(rs.getInt("board_idx"));
	               recipeReply.setContent(rs.getString("content"));
	               recipeReply.setDate(rs.getTimestamp("date"));
	               recipeReply.setIdx(rs.getInt("idx"));
	               recipeReply.setNickname(rs.getString("nickname"));
	               recipeReply.setRe_lev(rs.getInt("re_lev"));
	               recipeReply.setRe_ref(rs.getInt("re_ref"));
	               recipeReply.setRe_seq(rs.getInt("re_seq"));
	               
	               recipeReplyList.add(recipeReply);
	            }
	            System.out.println("mateReplyList :" + recipeReplyList );
	            
	         } catch (SQLException e) {
	            System.out.println("SQL 구문 오류 - selectRecipeReply() : " + e.getMessage());
	            e.printStackTrace();
	         } finally {
	            close(rs);
	            close(pstmt);
	         }
	         return recipeReplyList;
		}
		public int modifyReplyRecipe(int reply_idx, String nickname, String content) {
			int modifyCount = 0;
			
			PreparedStatement pstmt = null;
			
			try {
				String sql = "UPDATE recipe_reply SET content=? where idx=? AND nickname=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, content);
				pstmt.setInt(2, reply_idx);
				pstmt.setString(3, nickname);
				
				modifyCount = pstmt.executeUpdate();
				
				System.out.println("modifyReplyRecipe - " + modifyCount);
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 - deleteReplyRecipe() : " + e.getMessage());
				e.printStackTrace();
			}
			

			
			return modifyCount;
		}
		// --------------------------------------------------------------
		// 레시피 댓글 삭제
		public int deleteRecipeReply(int reply_idx, String nickname) {
			System.out.println("Recipe 댓글 삭제 - deleteRecipeReply()");
			
			int deleteRecipeReplyCount = 0;
			
			PreparedStatement pstmt = null;
			
			try {
				String sql = "DELETE FROM recipe_reply WHERE idx=? AND nickname=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, reply_idx);
				pstmt.setString(2, nickname);
				
				deleteRecipeReplyCount = pstmt.executeUpdate();
				
				System.out.println("deleteRecipeReply - " + deleteRecipeReplyCount);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQL 구문 오류 - deleteRecipeReply() " + e.getMessage());
			}
			
			return deleteRecipeReplyCount;
		}
		// 레시피 게시물 삭제하면 댓글도 삭제
		public int deleteRecipeReply(int idx) {
			int deleteRecipeReply = 0;
			
			PreparedStatement pstmt = null;
			
			try {
				String sql = "DELETE FROM recipe_reply WHERE board_idx=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, idx);
				deleteRecipeReply = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("sql구문 오류 - deleteRecipeReply" + e.getMessage());
			}
			
			return deleteRecipeReply;
		}
		// ----------------------------------------------------
		// 레시피 대댓글
		public RecipeReplyDTO selectRecipeRereply(int reply_idx) {
			RecipeReplyDTO recipeReply = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT * FROM recipe_reply WHERE idx=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, reply_idx);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					recipeReply = new RecipeReplyDTO();
					recipeReply.setBoard_idx(rs.getInt("board_idx"));
					recipeReply.setContent(rs.getString("content"));
					recipeReply.setDate(rs.getTimestamp("date"));
					recipeReply.setIdx(rs.getInt("idx"));
					recipeReply.setNickname(rs.getString("nickname"));
					recipeReply.setRe_lev(rs.getInt("re_lev"));
					recipeReply.setRe_ref(rs.getInt("re_ref"));
					recipeReply.setRe_seq(rs.getInt("re_seq"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQL 구문 오류 - selectRereply() : " + e.getMessage());
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return recipeReply;
		}
		// 레시피 대댓글 글쓰기
		public int insertRecipeRereply(RecipeReplyDTO recipeRereply) {
			System.out.println("CommunityDAO - insertRecipeRereply");
			
			int RecipeRereplyInsertCount = 0;
			
			PreparedStatement pstmt = null, pstmt2 = null, pstmt3 = null;
			ResultSet rs = null;
			
			int num = 1;
			
			try {
				// 새 글 번호로 사용될 번호를 생성하기 위해 기존 게시물의 가장 큰 번호 조회
				// => 조회 결과가 있을 경우 해당 번호 + 1 값을 새 글 번호로 저장
				String sql = "SELECT MAX(idx) FROM recipe_reply";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					num = rs.getInt(1) + 1; // 조회된 가장 큰 번호 + 1 값을 새 글 번호로 저장
				}
				
//				 기존 답글들에 대한 순서번호(re_seq) 증가 작업 처리
//				 => 원본글의 참조글번호(re_ref) 와 같고(같은 레코드들 중에서)
//				    원본글의 순서번호(re_seq)보다 큰 레코드들의 순서번호를 1씩 증가시키기
				sql = "UPDATE recipe_reply SET re_seq=re_seq+1 WHERE re_seq>?";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setInt(1, recipeRereply.getRe_seq());
				RecipeRereplyInsertCount = pstmt2.executeUpdate();
				
				// 답글을 mate_reply 테이블에 INSERT 작업
				sql = "INSERT INTO recipe_reply VALUES(?,?,?,?,?,?,now(),?)";
				pstmt3 = con.prepareStatement(sql);
				pstmt3.setInt(1, num);
				pstmt3.setString(2, recipeRereply.getNickname());
				pstmt3.setString(3, recipeRereply.getContent());
				pstmt3.setInt(4, recipeRereply.getRe_ref());
				pstmt3.setInt(5, recipeRereply.getRe_lev() + 1);
				pstmt3.setInt(6, recipeRereply.getRe_seq() + 1);
				pstmt3.setInt(7, recipeRereply.getBoard_idx());
//				System.out.println(mateReply);
				RecipeRereplyInsertCount = pstmt3.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 - insertRecipeRereply() : " + e.getMessage());
				e.printStackTrace();
			} finally {
				close(pstmt3);
				close(pstmt2);
				close(pstmt);
				close(rs);
			}
			
			return RecipeRereplyInsertCount;
		}
		
		
		
}
