package action;

import java.io.*;

import javax.servlet.http.*;

import svc.*;
import vo.*;

public class FAQDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("FAQDeleteAction - execute");
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
<<<<<<< HEAD

		
		FAQDeleteService service = new FAQDeleteService();
		boolean deleteSuccess = service.removeFAQ(idx);
//		System.out.println("deleteSuccess : " + deleteSuccess);
=======
		FAQDeleteService service = new FAQDeleteService();
		boolean deleteSuccess = service.removeFAQ(idx);
//		System.out.println("deleteSuccess : " + deleteSuccess);

>>>>>>> 10b9fd608ca9e4a54c35035f699131a4a851f3d6
		boolean deleteReplySuccess = false;

		
		if(!deleteSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 삭제 실패')");
			out.println("history.back()");
			out.println("</script>");

		} else {
<<<<<<< HEAD
			boolean checkReply = service.checkReply(idx);
			
			if(!checkReply) { // 댓글이 없을 경우
				deleteReplySuccess = true;
			} else { // 댓글이 있을 경우
				deleteReplySuccess = service.removeReplyFAQ(idx);
	//			System.out.println("deleteSuccess : " + deleteSuccess);
			}

=======
			deleteReplySuccess = service.removeReplyFAQ(idx);
			System.out.println("deleteSuccess : " + deleteSuccess);
>>>>>>> 10b9fd608ca9e4a54c35035f699131a4a851f3d6
		}
		if(!deleteReplySuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
<<<<<<< HEAD

			out.println("alert('글 삭제 실패!')");
=======
			out.println("alert('글 삭제 실패')");
>>>>>>> 10b9fd608ca9e4a54c35035f699131a4a851f3d6
			out.println("history.back()");
			out.println("</script>");

			
		} else {
			forward = new ActionForward();
			forward.setPath("FAQList.sc");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
