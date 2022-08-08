package action;

import java.io.*;

import javax.servlet.http.*;

import svc.*;
import vo.*;

public class FAQModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("NoticeModifyProAction-execute");
		ActionForward forward = null;
		FAQDTO faq = new FAQDTO();
		faq.setNickname(request.getParameter("nickname"));
		faq.setSubject(request.getParameter("subject"));
		faq.setContent(request.getParameter("content"));
		faq.setOriginal_File(request.getParameter("file"));
		faq.setReal_File(request.getParameter("file"));
		faq.setIdx(Integer.parseInt(request.getParameter("idx")));
<<<<<<< HEAD
<<<<<<< HEAD
		faq.setCategory(request.getParameter("category"));
=======
>>>>>>> master
=======
>>>>>>> 10b9fd608ca9e4a54c35035f699131a4a851f3d6
//		System.out.println("notice : " + faq);
		
		FAQModifyProService service = new FAQModifyProService();
		
		boolean isModifySuccess = service.modifyFAQ(faq);
//		System.out.println(isModifySuccess);
		
		if(!isModifySuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			 
			forward = new ActionForward();
			forward.setPath("FAQDetail.sc?idx="+Integer.parseInt(request.getParameter("idx"))+"&pageNum="+request.getParameter("pageNum"));
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
