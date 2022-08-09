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
import action.TmiDetailAction;
import action.TmiListAction;
import action.TmiModifyFormAction;
import action.TmiModifyProAction; 
import action.TmiWirteProAction;
import vo.ActionForward;


// 어맛커뮤니티의 FrontController
/*
 * 1. 리스트
 * 2. 글 등록
 * 3. 글 상세페이지
 * 4. 글 수정
 * 5. 글 삭제
 * 6. 댓글
 * 7. 답글 (?)
 */

@WebServlet("*.co")

public class CommunityFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CommunityFrontController");
		
		// POST 방식 요청에 대한 한글 처리
		request.setCharacterEncoding("UTF-8");
		
		// 서블릿 주소 추출
		String command = request.getServletPath();
		System.out.println("command : " + command);
		
		// Action 클래스 인스턴스들을 공통으로 관리하는 Action 타입 변수 선언
		Action action = null;
		// 포워딩 정보를 관리하는 ActionForward 타입 변수 선언
		ActionForward forward = null;
		

		// 추출된 서블릿 주소를 if문을 사용하여 판별하고 각 주소에 따른 액션(작업) 요청
		// 글쓰기 폼을 요청하는 서블릿(/MateWriteForm.co) 요청
		if(command.equals("/TmiList.co")) {
			// tmi 게시판 글 전체 목록 조회
			try {
				action = new TmiListAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("TmiListAction 오류 - " + e.getMessage());
				e.printStackTrace();
			}
			
		} else if(command.equals("/TmiWriteForm.co")) {
			// tmi 게시판의 글 쓰기 작업

			forward = new ActionForward();
			forward.setPath("community/tmiWrite.jsp");
			forward.setRedirect(false);
			

		} else if(command.equals("/TmiWritePro.co")) {
			// tmi 게시판의 글 쓰기 작업을 요청
			try {
				action = new TmiWirteProAction();

		} else if(command.equals("/MateWritePro.mate")) {
			action = new MateWriteProAction();
			try {

				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("TmiWriteProAction 오류 - " + e.getMessage());
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
			
		} else if(command.equals("/TmiDetail.co")) {
			// tmi 게시글 상세내용 보기
			try {
				action = new TmiDetailAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("TmiDetailAction 오류 - " + e.getMessage());
				e.printStackTrace();
			}
			
		} else if(command.equals("/TmiModifyForm.co")) {
			// tmi 게시글의 글 수정 작업에 필요한 게시물 조회
			try {
				action = new TmiModifyFormAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("TmiModifyFormAction 오류 - " + e.getMessage());
				e.printStackTrace();
			}
		} else if(command.equals("/TmiModifyPro.co")) {
			// tmi 게시글의 글 수정 작업을 요청하는 작업
			try {
				action = new TmiModifyProAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("TmiModifyProAction 오류 - " + e.getMessage());
				e.printStackTrace();
			}
		}

		// ---------------------------------------------------------------------------
		if(forward != null) { // ActionForward 객체가 null 이 아닐 경우에만 포워딩 작업 수행
			// Redirect 방식 vs Dispatcher 방식 판별하여 각 방식에 대한 포워딩 작업 수행
			if(forward.isRedirect()) { // Redirect 방식

				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);
	}

}

