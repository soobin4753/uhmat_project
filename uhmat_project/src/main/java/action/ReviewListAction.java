package action;

import javax.servlet.http.*;

import java.util.*;


import svc.*;
import vo.*;

public class ReviewListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("ReviewListAction");
		
		ActionForward forward = null;
		
		//페이징 처리를 위한 변수 선언
		int pageNum = 1;
		int listLimit = 6;
		int pageLimit = 10;
		
		
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		//페이징 처리에 필요한 전체 게시물 갯수 조회 작업
		
		ReviewListService reviewListService = new ReviewListService();
		int listCount = reviewListService.getListCount();
		
		System.out.println("전체 게시물 수 : " + listCount);
		
		//페이징 처리를 위한 계산 작업
		// 1.현재 페이지에서 표시할 전체 페이지 수 계산
		int maxPage = (int)Math.ceil((double)listCount/ listLimit);
		
		// 2. 현재 펭지에서 보여줄 시작 페이지 번호
		int startPage = ((int)((double)pageNum / pageLimit + 0.9) - 1) * pageLimit + 1;
		
		// 3. 현재 페이지에서 보여줄 끝 페이지 번호
		int endPage = startPage + pageLimit - 1;
		
		// 4. 끝페이지를 총 페이지 수로 대체
		if(endPage > maxPage) {
			endPage = maxPage;
			}
		
		// 페이징 처리 정보를 pageInfo 객체에 저장
		PageInfo pageInfo = new PageInfo(pageNum, maxPage, startPage, endPage, listCount);
		
		ArrayList<ReviewBoardDTO> reviewList = ReviewListService.getBoardList(pageNum, listLimit);
		
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("reviewList", reviewList);
		
		forward = new ActionForward();
		forward.setPath("food/review/reviewList.jsp");
		forward.setRedirect(false);
	
		return forward;
		
	}

}
