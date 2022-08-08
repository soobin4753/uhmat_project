package action;

import java.io.*;

import javax.servlet.http.*;

import svc.*;
import vo.*;

public class NoticeDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		NoticeDeleteService service = new NoticeDeleteService();
		boolean deleteSuccess = service.removeNotice(idx);
		
		if(!deleteSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
			
		} else {
			
			forward = new ActionForward();
			forward.setPath("NoticeList.sc");
			forward.setRedirect(true);
		}
		
		
		return forward;
	}

}
