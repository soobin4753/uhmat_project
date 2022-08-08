package action;

import java.util.*;

import javax.servlet.http.*;

import svc.*;
import vo.*;

public class FAQDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("FAQDetailAction - execute");
		ActionForward forward = null;
<<<<<<< HEAD
		
		int idx = Integer.parseInt(request.getParameter("idx"));
//		System.out.println("idx : " + idx);
		
=======
		int idx = Integer.parseInt(request.getParameter("idx"));
		System.out.println("idx : " + idx);
>>>>>>> master
		FAQDetailService service = new FAQDetailService();
		
		service.increaseReadcount(idx);
		
		FAQDTO faq = service.getFAQ(idx);
		
		FAQReplyDTO reply = service.getFAQReply(idx); 
//		System.out.println("reply : " + reply);
		
		request.setAttribute("faq", faq);
		request.setAttribute("reply", reply);
		
		forward = new ActionForward();
		forward.setPath("serviceCenter/faq/faqDetail.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
