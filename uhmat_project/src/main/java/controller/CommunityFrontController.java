package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.MateDetailAction;
import action.MateListAction;
import action.MateWriteProAction;
import vo.ActionForward;

@WebServlet("*.mate")
public class CommunityFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CommunityFrontController");
		request.setCharacterEncoding("UTF-8");	
	
		// 서블릿 주소 추출
		String command = request.getServletPath();
		System.out.println("command : " + command);
				
		// Action 클래스 인스턴스들을 공통으로 관리하는 Action 타입 변수 선언
		Action action = null;
		// 포워딩 정보를 관리하는 ActionForward 타입 변수 선언
		ActionForward forward = null;
		
		// ----------------------------------------------------------------
		// 리스트를 요청하는 서블릿(/MateWriteForm.mate) 요청
		if(command.equals("/MateList.mate")) {
			action = new MateListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("MateListProAction 오류 - " + e.getMessage());
				e.printStackTrace();
			}
			
		// -----------------------------------------------------------------	
		// 글쓰기 폼을 요청하는 서블릿(/MateWriteForm.mate) 요청
		} else if(command.equals("/MateWriteForm.mate")) {
			forward = new ActionForward();
			forward.setPath("community/mate_write.jsp");
			forward.setRedirect(false); // Dispatcher 방식(생략 가능)
			
		} else if(command.equals("/MateWritePro.mate")) {
			action = new MateWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("MateWriteProAction 오류 - " + e.getMessage());
				e.printStackTrace();
						}
		// ------------------------------------------------------------------
		} else if(command.equals("/MateDetail.mate")) {
			action = new MateDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("MateDetailAction 오류 - " + e.getMessage());
				e.printStackTrace();
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
