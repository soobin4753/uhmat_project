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
<<<<<<< HEAD
		
		int idx = Integer.parseInt(request.getParameter("idx"));
//		System.out.println("idx : " + idx);
		
=======
		int idx = Integer.parseInt(request.getParameter("idx"));
		System.out.println("idx : " + idx);
>>>>>>> master
=======
		int idx = Integer.parseInt(request.getParameter("idx"));
		System.out.println("idx : " + idx);
>>>>>>> 10b9fd608ca9e4a54c35035f699131a4a851f3d6
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
