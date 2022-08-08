package action;

import java.io.*;

import javax.servlet.http.*;

import svc.*;
import vo.*;

public class NoticeModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("NoticeModifyProAction-execute");
		
		ActionForward forward = null;
		NoticeDTO notice = new NoticeDTO();
		notice.setNickname(request.getParameter("nickname"));
		notice.setSubject(request.getParameter("subject"));
		notice.setContent(request.getParameter("content"));
		notice.setOriginal_File(request.getParameter("file"));
		notice.setReal_File(request.getParameter("file"));
		notice.setIdx(Integer.parseInt(request.getParameter("idx")));
//		System.out.println("notice : " + notice);
		
		NoticeModifyProService service = new NoticeModifyProService();
		
		boolean isModifySuccess = service.modifyNotice(notice);
		
		if(!isModifySuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			 
			forward = new ActionForward();
			forward.setPath("NoticeDetail.sc?idx="+Integer.parseInt(request.getParameter("idx"))+"&pageNum="+request.getParameter("pageNum"));
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
