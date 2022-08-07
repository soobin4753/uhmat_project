package controller;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.*;
import vo.*;

@WebServlet({ "/RestaurantFrontController", "*.re" })
public class RestaurantFrontController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("RestaurantFrontController!");
		
		Action action = null;
		ActionForward forward = null;
		String command = request.getServletPath();
		System.out.println("Res_Controller - command : "+command);
		
		//리뷰 관련 요청
		if(command.equals("/reviewList.re")) {
			System.out.println("리뷰 글 목록 요청!!");
			action = new reviewListAction();
			forward = action.execute(request, response);
		}else if(command.equals("/reviewWriteForm.re")) {
			System.out.println("리뷰 입력 폼 요청!");
			forward = new ActionForward();
			forward.setPath("food/review/review_write.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/reviewWritePro.re")) {
			System.out.println("리뷰 글 입력 요청!");
			action = new reviewWriteProAction();
			forward = action.execute(request, response);
		}else if(command.equals("/reviewDetail.re")) {
			System.out.println("리뷰 상세보기 요청!");
			action = new reviewDetailAction();
			forward = action.execute(request, response);
		}else if(command.equals("/reviewModifyForm.re")) {
			System.out.println("리뷰 수정 폼 요청!");
			action = new reviewModifyFormAction(); 
			forward = action.execute(request, response);
		}else if(command.equals("/reviewModifyPro.re")) {
			System.out.println("리뷰 수정 업데이트 요청!");
			action = new reviewModifyProAction();
			forward = action.execute(request, response);
		}else if(command.equals("/reviewDelete.re")) {
			System.out.println("리뷰 삭제 요청!");
			action = new reviewDeleteProAction();
			//삭제 시 확인 필요X! 하지만 글작성자의 로그인 되어있는지 확인 필요!
			forward = action.execute(request, response);
		}else if(command.equals("/reviewReply.re")) {
			System.out.println("댓글 입력 요청!");
			action = new reviewReplyAction();
			forward = action.execute(request, response);
		}
		
		
		// 여기 밑에 부터는 식당정보 관련 요청
		else if(command.equals("/restaurantList.re")) {
			System.out.println("식당 글 목록 요청!!");
			forward = new ActionForward();
			forward.setPath("food/restaurant/restaurant_write.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/restaurantWriteForm.re")) {
			System.out.println("식당 입력 폼 요청!");
			
		}else if(command.equals("/restaurantWritePro.re")) {
			System.out.println("식당 글 입력 요청!");
		}else if(command.equals("/restaurantDetail.re")) {
			System.out.println("식당 상세보기 요청!");
		}else if(command.equals("/restaurantModifyForm.re")) {
			System.out.println("식당 수정 폼 요청!");
		}else if(command.equals("/restaurantModifyPro.re")) {
			System.out.println("식당 수정 업데이트 요청!");
		}else if(command.equals("/restaurantDelete.re")) {
			System.out.println("식당 삭제 요청!");
		}
		//추가로 태그와 카테고리 관련된 작업 요청이 더 필요함!!
		
/* ***********************************************************************************
 		forward 객체의 이동 경로 및 지정된 경로 방식 실행!
 *************************************************************************************/
		
		if(forward !=null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

}
