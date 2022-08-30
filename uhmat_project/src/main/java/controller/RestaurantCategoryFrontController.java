package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import action.Action;
import action.CheckHashAction;
import action.MapAction;
import action.RestaurantDeleteAction;
import action.RestaurantDetailAction;
import action.RestaurantListAction;
import action.RestaurantModifyFormAction;
import action.RestaurantModifyProAction;
import action.RestaurantWriteProAction;
import action.ReviewDeleteProAction;
import action.ReviewDetailAction;
import action.ReviewLikeAction;
import action.ReviewListAction;
import action.ReviewModifyFormAction;
import action.ReviewModifyProAction;
import action.ReviewWriteProAction;
import vo.ActionForward;
import vo.RestaurantInfoDTO;

/**
 * Servlet implementation class RestaurantReviewFrontController
 */
@WebServlet("*.re")
public class RestaurantCategoryFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String command = request.getServletPath();
		System.out.println(command);
		ActionForward forward = null;
		Action action = null;
		// -------------------------------------------------------
		if (command.equals("/ReviewList.re")) {
			try {
				action = new ReviewListAction();
				forward = action.execute(request, response);
			} catch (Exception e) {

				e.printStackTrace();
			}

		}
		/*
		 * 여기서부터는 식당 요청!! 글쓰기 수정 삭제 등은 관리자로 로그인 되어있어야함!
		 */
		else if (command.equals("/restaurantList.re")) {
			System.out.println("식당 글 목록 요청!!");
			try {
				action = new RestaurantListAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (command.equals("/restaurantWriteForm.re")) {
			System.out.println("식당 글 입력 폼 요청!");
			forward = new ActionForward();

			forward.setPath("food/restaurant/res_write.jsp");

			forward.setRedirect(false);
		} else if (command.equals("/restaurantWritePro.re")) {
			System.out.println("식당 글 입력 요청!");
			try {
				action = new RestaurantWriteProAction();
				forward = action.execute(request, response);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (command.equals("/restaurantDetail.re")) {
			System.out.println("식당 상세보기 요청!");
			try {
				action = new RestaurantDetailAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (command.equals("/restaurantModifyForm.re")) {
			System.out.println("식당 수정 폼 요청!");
			try {
				action = new RestaurantModifyFormAction(); 
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (command.equals("/restaurantModifyPro.re")) {
			System.out.println("식당 수정 업데이트 요청!");
			try {
				action = new RestaurantModifyProAction();  
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (command.equals("/restaurantDelete.re")) {
			System.out.println("식당 삭제 요청!");
			try {
				action = new RestaurantDeleteAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block 
				e.printStackTrace();
			}
		}
		// 추가로 태그와 카테고리 관련된 작업 요청이 더 필요함!!

		 else if(command.equals("/ReviewWriteForm.re")) {
				
			 forward = new ActionForward();
				forward.setPath("food/review/reviewWriteForm.jsp");
				forward.setRedirect(false);

		} else if(command.equals("/ReviewWritePro.re")) {
			 try {
				action = new ReviewWriteProAction();
				 forward = action.execute(request, response);
			} catch (Exception e) {
				
				e.printStackTrace();
			}

		} else if(command.equals("/ReviewDetail.re")) {
			 try {
				action = new ReviewDetailAction();
				 forward = action.execute(request, response);
			} catch (Exception e) {
				
				e.printStackTrace();
			}

		} else if(command.equals("/ReviewModifyForm.re")) {
			 try {
				action = new ReviewModifyFormAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				
				e.printStackTrace();
			}

		} else if(command.equals("/ReviewModifyProAction.re")) {
			 try {
				action = new ReviewModifyProAction();
				 forward = action.execute(request, response);
			} catch (Exception e) {
				
				e.printStackTrace();
			}

		} else if(command.equals("/ReviewDeleteForm.re")) {
			forward = new ActionForward();
			forward.setPath("food/review/reviewDeleteForm.jsp");
			forward.setRedirect(false);
			
		} else if(command.equals("/ReviewDeleteProAction.re")) {
			 try {
				action = new ReviewDeleteProAction();
				 forward = action.execute(request, response);
			} catch (Exception e) {
				
				e.printStackTrace();
			}

		} else if(command.equals("/ReviewLikeAction.re")) {
			 try {
				action = new ReviewLikeAction();
				 forward = action.execute(request, response);
			} catch (Exception e) {
				
				e.printStackTrace();
			}

		} else if(command.equals("/CheckHash.re")) {
			 try {
				action = new CheckHashAction();
				 forward = action.execute(request, response);
			} catch (Exception e) {
				
				e.printStackTrace();
			}

		}else if (command.equals("/map.re")) {

			MapAction mapGet = new MapAction();
			
			String keyword= "";
			
			if(request.getParameter("keyword")!=null) {
				keyword = request.getParameter("keyword");
			}
			System.out.println("keyword : " + keyword);
			
			ArrayList<RestaurantInfoDTO> list = mapGet.execute(keyword);

			String gson = new Gson().toJson(list);
			System.out.println(list);
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().write(gson);
		
		// 지도 보여주기
		} else if (command.equals("/mapForm.re")) {

			forward = new ActionForward();
			forward.setPath("/food/map/map.jsp");
			forward.setRedirect(false);

		}

		if (forward != null)

		{
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		}
	}
	// ---------------------------------------------------------

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
