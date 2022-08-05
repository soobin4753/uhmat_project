package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class TmiListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("TmiListAction");
		ActionForward forward = null;
		
		// 페이징 처리를 위한 변수 선언
		int pageNum = 1;
		int listLimit = 20; // 공지사항 수를 생각해서 20개로 지정
		int pageLimit = 10;
		
		// 단, URL 파라미터로 현재 페이지번호(pageNum)이 전달됐을 경우 가져와서 변수에 저장
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		return forward;
	}

}
