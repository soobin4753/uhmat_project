package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.MateWriteProAction;
import action.TmiListAction;
import vo.ActionForward;

// 어맛커뮤니티 - tmi의 FrontController
/*
 * 1. 리스트
 * 2. 글 등록
 * 3. 글 수정
 * 4. 글 삭제
 * 5. 댓글
 * 6. 답글
 */

@WebServlet("*.mate")
public class TmiFrontController extends HttpServlet {
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
		// 글쓰기 폼을 요청하는 서블릿(/MateWriteForm.mate) 요청
		if(command.equals("/tmiList.tmi")) {
			// tmi 게시판 글 전체 목록 조회
			try {
				action = new TmiListAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("TmiListAction 오류 - " + e.getMessage());
				e.printStackTrace();
			}
			
		} else if(command.equals("/TmiWriteForm.tmi")) {
			forward = new ActionForward();
			forward.setPath("community/tmiWrite.jsp");
			forward.setRedirect(false);
		}
		
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);
	}

}
