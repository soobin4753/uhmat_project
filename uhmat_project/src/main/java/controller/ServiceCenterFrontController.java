package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.*;
import vo.*;

@WebServlet("*.sc")
public class ServiceCenterFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("ServiceCenterFrontController");
		request.setCharacterEncoding("UTF-8");
		
		String command = request.getServletPath();
		System.out.println("command : " + command );
		ActionForward forward = null;
		Action action = null;
		
		// --------------------- Notice -------------------------------------
		if (command.equals("/NoticeList.sc")) {
			try {
				action = new NoticeListAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/NoticeWriteForm.sc")) {
			
			forward = new ActionForward();
			forward.setPath("serviceCenter/notice/noticeWriteForm.jsp");
			forward.setRedirect(false);
			
		} else if(command.equals("/NoticeWritePro.sc")) {
			try {
				action = new NoticeWriteProAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/NoticeDetail.sc")) {
			try {
				action = new NoticeDetailAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/NoticeModifyForm.sc")) {
			try {
				action = new NoticeModifyFormAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/NoticeModify.sc")) {
			try {
				action = new NoticeModifyProAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/NoticeDelete.sc")) {
				try {
					action = new NoticeDeleteAction();
					forward = action.execute(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} 
				
		// --------------------FAQ ------------------------------------
		 else if (command.equals("/FAQList.sc")) {
			try {
				action = new FAQListAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/FAQWriteForm.sc")) {
			forward = new ActionForward();
			forward.setPath("serviceCenter/faq/faqWriteForm.jsp");
			forward.setRedirect(false);
			
		} else if(command.equals("/FAQWritePro.sc")) {
			try {
				action = new FAQWriteProAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/FAQDetail.sc")) {
			try {
				action = new FAQDetailAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/FAQModifyForm.sc")) {
			try {
				action = new FAQModifyFormAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/FAQModify.sc")) {
			try {
				action = new FAQModifyProAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/FAQDelete.sc")) {
				try {
					action = new FAQDeleteAction();
					forward = action.execute(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		//---------------------관리자가 답변-----------------------------------------------

		} else if(command.equals("/FAQDetailReply.sc")) {
			try {
				action = new FAQDetailReplyAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		// 관리자 삭제	
		} else if(command.equals("/FAQDetailReplyDelete.sc")) {
			try {
				action = new FAQDetailReplyDeleteAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		

		
		
		//--------------------------------------------------------------------
		
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
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
