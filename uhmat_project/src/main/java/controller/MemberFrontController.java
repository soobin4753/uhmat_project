package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.member.MemberAuthAction;
import action.member.MemberChechDuplicateEmailAction;
import action.member.MemberChechDuplicateNickNameAction;
import action.member.MemberJoinProAction;
import action.member.MemberLoginProAction;
import action.member.MemberLogoutAction;
import action.member.MemberSendAuthMailAction;
import vo.ActionForward;

@WebServlet("*.me")
public class MemberFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	

		String command = request.getServletPath();
		System.out.println(command);
		ActionForward forward = null; 
		Action action = null;

		if (command.equals("/MemberJoinForm.me")) {
			forward = new ActionForward();
			forward.setPath("/member/joinForm.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/MemberJoinPro.me")) {
			try {
				action = new MemberJoinProAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		else if (command.equals("/CheckDuplicateNickName.me")) {
		   
			try {
				PrintWriter out = response.getWriter();
		        boolean checkNick=true;
		         try {
		        	 MemberChechDuplicateNickNameAction nickNameCheckaction = new MemberChechDuplicateNickNameAction();
		        	 checkNick = nickNameCheckaction.execute(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        if(checkNick) {
		            out.print("not-usable");
		        } else {
		            out.print("usable");
		        }
				   
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else if (command.equals("/CheckDuplicateEmail.me")) {
			boolean checkEmail=true;
			PrintWriter out = response.getWriter();
			try {
				MemberChechDuplicateEmailAction emailCheckaction = new MemberChechDuplicateEmailAction();
				checkEmail = emailCheckaction.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	        if(checkEmail) {
	            out.print("not-usable");
	        } else {
	            out.print("usable");
	        }
		} else if (command.equals("/SendAuthMail.me")) {
			try {
				action = new MemberSendAuthMailAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberAuth.me")) {
			action = new MemberAuthAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberLoginForm.me")) {
			forward = new ActionForward();
			forward.setPath("/member/loginForm.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/MemberLoginPro.me")) {
			try {
				action = new MemberLoginProAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberLogout.me")) {
			try {
				action = new MemberLogoutAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProcess(request, response);
	}

}
