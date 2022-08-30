package action;


import javax.servlet.http.*;

import svc.ReviewDetailService;
import vo.*;


public class ReviewDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("ReviewDetailAction");
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		ReviewDetailService service = new ReviewDetailService();
		ReviewBoardDTO dto = service.getReviewBoard(idx);
		
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		System.out.println(dto);
		forward = new ActionForward();

		forward.setPath("food/review/reviewDetailView.jsp");

		forward.setRedirect(false);
		return forward;

	}

}
