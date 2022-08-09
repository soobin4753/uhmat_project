package action;

import javax.servlet.http.*;

import svc.*;
import vo.*;

public class NoticeModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("NoticeModifyFormAction - execute");
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		NoticeDetailService service = new NoticeDetailService();
		
		NoticeDTO notice = service.getNotice(idx);
		
		request.setAttribute("notice", notice);
		
		forward = new ActionForward();
		forward.setPath("serviceCenter/notice/noticeModify.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
