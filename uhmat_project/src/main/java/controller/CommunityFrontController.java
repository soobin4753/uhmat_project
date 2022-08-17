package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.MateDeleteProAction;
import action.MateDetailAction;
import action.MateListAction;
import action.MateModifyFromAction;
import action.MateModifyProAction;
import action.MateReplyDeleteAction;
import action.MateReplyModifyAction;
import action.MateReplyWriteAction;
import action.MateRereplyFormAction;
import action.MateRereplyWriteAction;
import action.MateWriteProAction;
import action.RecipeDetailAction;
import action.RecipeListAction;
import action.RecipeWriteProAction;
import vo.ActionForward;

@WebServlet("*.co")
public class CommunityFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CommunityFrontController");
//		System.out.println("깃 커밋 안 올라가져서 테스트");
		request.setCharacterEncoding("UTF-8");	
	
		// 서블릿 주소 추출
		String command = request.getServletPath();
		System.out.println("command : " + command);
				
		// Action 클래스 인스턴스들을 공통으로 관리하는 Action 타입 변수 선언
		Action action = null;
		// 포워딩 정보를 관리하는 ActionForward 타입 변수 선언
		ActionForward forward = null;
		
		// ----------------------------------------------------------------
		// 리스트를 요청하는 서블릿(/MateListForm.co) 요청
		if(command.equals("/MateList.co")) {
			action = new MateListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("MateListProAction 오류 - " + e.getMessage());
				e.printStackTrace();
			}
			
		// -----------------------------------------------------------------	
		// 글쓰기 폼을 요청하는 서블릿(/MateWriteForm.co) 요청
		} else if(command.equals("/MateWriteForm.co")) {
			forward = new ActionForward();
			forward.setPath("community/mate/mate_write.jsp");
			forward.setRedirect(false); // Dispatcher 방식(생략 가능)
			

		} else if(command.equals("/MateWritePro.co")) {

			action = new MateWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("MateWriteProAction 오류 - " + e.getMessage());
				e.printStackTrace();
						}
		// ------------------------------------------------------------------
		// 글 디테일 뷰를 요청하는 서블릿(/MateDetail.co) 요청
		} else if(command.equals("/MateDetail.co")) {
			action = new MateDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("MateDetailAction 오류 - " + e.getMessage());
				e.printStackTrace();
			}
		// -----------------------------------------------------------------------
		// 글 수정에 필요한 게시물 조회 요청하는 서블릿(/MateModifyForm.co) 요청
		} else if(command.equals("/MateModifyForm.co")) {
			action = new MateModifyFromAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("MateModifyFromAction 오류 - " + e.getMessage());
				e.printStackTrace();
			}
					
		} else if(command.equals("/MateModifyPro.co")) {
			action = new MateModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("MateModifyProAction 오류 - " + e.getMessage());
				e.printStackTrace();
			}
		// --------------------------------------------------------------------------
		// 글 삭제 폼 이동
		} else if(command.equals("/MateDeleteForm.co")) {
			forward = new ActionForward();
			forward.setPath("community/mate/mate_delete.jsp");
			forward.setRedirect(false);
			
		} else if(command.equals("/MateDeletePro.co")) {
			action = new MateDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("MateDeleteProAction 오류 - " + e.getMessage());
				e.printStackTrace();
			}
		// -----------------------------------------------------------------------------
		// 댓글 폼 요청
		} else if(command.equals("/MateReplyForm.co")) {
			forward = new ActionForward();
			forward.setPath("community/mate/mate_replyForm.jsp");
			forward.setRedirect(false);
		// 댓글 쓸기
		} else if(command.equals("/MateReplyWrite.co")){
			action = new MateReplyWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("MateReplyWriteAction 오류 - " + e.getMessage());
				e.printStackTrace();
				}
		
		// ------------------------------------------------------
		// 댓글 리스트
		} else if(command.equals("/MateReplyList.co")) {
			action = new MateDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println(" MateDetailAction - reply 오류 - " + e.getMessage());
				e.printStackTrace();
				}
			
		// ---------------------------------------------------------------	
		// 뎃글 수정	
		} else if(command.equals("/MateReplyModifyForm.co")) {
			forward = new ActionForward();
			forward.setPath("community/mate/mate_replyModify.jsp");
			forward.setRedirect(false);
			
		} else if(command.equals("/MateReplyModifyPro.co")) {
			action = new MateReplyModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		// --------------------------------------------------------------------
		// 댓글 삭제
		} else if(command.equals("/MateReplyDeleteForm.co")) {
			forward = new ActionForward();
			forward.setPath("community/mate/mate_replyDelete.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/MateReplyDeletePro.co")) {
			action = new MateReplyDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println(" MateReplyDeleteAction - reply 오류 - " + e.getMessage());
				e.printStackTrace();
			}
		// ----------------------------------------------------------------------
		// 대댓글 글쓰기
		} else if(command.equals("/MateRereplyForm.co")) {
//			forward = new ActionForward();
//			forward.setPath("community/mate/mate_RereplyForm.jsp");
//			forward.setRedirect(false);
			action = new MateRereplyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if(command.equals("/MateRereplyWrite.co")) {
			action = new MateRereplyWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(" MateRereplyWriteAction 오류 - " + e.getMessage());
			}
			
		// ==============================================================================================
		// 레시피 시작
		// 레시피 글쓰기
		} else if(command.equals("/RecipeWriteForm.co")) {
			forward = new ActionForward();
			forward.setPath("community/recipe/recipe_write.jsp");
			forward.setRedirect(false); // Dispatcher 방식(생략 가능)
			
		} else if(command.equals("/RecipeWritePro.co")) {
			action = new RecipeWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("RecipeWriteProAction 오류 - " + e.getMessage());
				e.printStackTrace();
			}
		// ----------------------------------------------------------------------------
		// 레시피 리스트
		} else if(command.equals("/RecipeList.co")) {
			action = new RecipeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("RecipeListAction 오류 - " + e.getMessage());
			}
		// -----------------------------------------------------------------------------
		// 레시피 상세페이지
		} else if(command.equals("/RecipeDetail.co")) {
			action = new RecipeDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("RecipeDetailAction 오류 - " + e.getMessage());
			}
		}
		
		
		
		
		
		// ---------------------------------------------------------------------------
		if(forward != null) { // ActionForward 객체가 null 이 아닐 경우에만 포워딩 작업 수행
			// Redirect 방식 vs Dispatcher 방식 판별하여 각 방식에 대한 포워딩 작업 수행
			if(forward.isRedirect()) { // Redirect 방식
				response.sendRedirect(forward.getPath());
			} else { // Dispatcher 방식
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	
	//----------------------------------------------------------------------------------------------------------------------
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
