package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ReviewDetailService;
import vo.ActionForward;
import vo.ReviewBoardDTO;

public class ReviewDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReviewDetailAction");
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		ReviewDetailService service = new ReviewDetailService();
		ReviewBoardDTO dto = service.getReviewBoard(idx);
		
		request.setAttribute("dto", dto);
		
		forward = new ActionForward();
		forward.setPath("RestaurantCategory/reviewDetailView.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
