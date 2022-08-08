package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.*;
import vo.*;

/**
 * Servlet implementation class RestaurantReviewFrontController
 */
@WebServlet("*.re")
public class RestaurantCategoryFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String command = request.getServletPath();
		System.out.println(command);
		ActionForward forward = null;
		Action action = null;
	//-------------------------------------------------------
		if(command.equals("/ReviewList.re")) {
			 try {
				action = new ReviewListAction();
				 forward = action.execute(request, response);
			} catch (Exception e) {
				
				e.printStackTrace();
			}


		} else if (command.equals("/ReviewWriteForm.re")) {
			
			// 세션 값에 따라 처리 
			forward = new ActionForward();
			forward.setPath("RestaurantCategory/reviewWriteForm.jsp");
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

		} 
	
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		}
	}
	//---------------------------------------------------------

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
