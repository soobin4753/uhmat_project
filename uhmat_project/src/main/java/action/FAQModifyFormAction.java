package action;

import javax.servlet.http.*;

import svc.*;
import vo.*;

public class FAQModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("NoticeModifyFormAction - execute");
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		FAQDetailService service = new FAQDetailService();
		
		FAQDTO faq = service.getFAQ(idx);
		
		request.setAttribute("faq", faq);
		
		forward = new ActionForward();
		forward.setPath("serviceCenter/faq/faqModify.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
