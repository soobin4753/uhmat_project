package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.TmiListService;
import vo.ActionForward;
import vo.CommunityTmiDTO;
import vo.PageInfo;

public class TmiListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("TmiListAction");
		
		ActionForward forward = null;
		
		// 페이징 처리를 위한 변수 선언
		int pageNum = 1;
		int listLimit = 10;
		int pageLimit = 10;
		
		// 단, URL 파라미터로 현재 페이지번호(pageNum)이 전달됐을 경우 가져와서 변수에 저장
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		// 페이징 처리에 필요한 전체 게시물 갯수 조회 작업을 요청합니다.
		// => TmiListService 클래스 인스턴스 생성 후 getTmiListCount() 메서드를 호출하여 총 게시물 수를 조회합니다.
		// => 파라미터 : X (없음), 리턴타입 : int(tmiListCount)
		TmiListService service = new TmiListService();
		int tmiListCount = service.getTmiListCount();
		System.out.println("tmi 전체 게시물 수 : " + tmiListCount);
		
		// ------------------------------------------------------------------------------------------------
		// 페이징 처리를 위한 계산 작업
		// 1. 현재 페이지에서 표시할 전체 페이지 수 계산
		int maxPage = (int)Math.ceil((double)tmiListCount / listLimit);
		
		// 2. 현재 페이지에서 보여줄 시작 페이지 번호(1, 11, 21 등의 시작 번호) 계산
		int startPage = ((int)((double)pageNum / pageLimit + 0.9) - 1) * pageLimit + 1;

		// 3. 현재 페이지에서 보여줄 끝 페이지 번호(10, 20, 30 등의 끝 번호) 계산
		int endPage = startPage + pageLimit - 1;

		// 4. 만약, 끝 페이지(endPage)가 현재 페이지에서 표시할 총 페이지 수 (maxPage)보다 클 경우
		// 끝 페이지 번호를 총 페이지 수로 대체해야 한다!
		if(endPage > maxPage) {
			endPage = maxPage;	
		}
				
		// 페이징 처리 정보를 PageInfo 객체에 저장
		PageInfo pageInfo = new PageInfo(pageNum, maxPage, startPage, endPage, tmiListCount);
				
		// -----------------------------------------------------------------------------------------
		// TmiListService 객체의 getTmiBoardList() 메서드를 호출하여 게시물 목록을 가져옵니다.
		// => 파라미터 : 현재 페이지번호, 페이지 당 게시물 수, 리턴타입 : ArrayList<CommunityTmiDTO> (tmiboardList)
		ArrayList<CommunityTmiDTO> tmiBoardList = service.getTmiBoardList(pageNum, listLimit);
		System.out.println(tmiBoardList);
		// 뷰페이지(jsp)에서 사용할 데이터가 저장된 객체들을 전달하기 위해
		// request 객체의 setAttribute() 메서드를 호출하여 객체를 저장합니다.
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("tmiBoardList", tmiBoardList);
//		System.out.println("액션에서 보내는 데이터 : " + pageInfo.getListCount());
//		System.out.println("액션에서 보내는 데이터 : " + tmiBoardList);
		// setAttribute를 사용했다면 보내는게 성공하는지 확인하기 위해 콘솔에 출력을 해봅니다.
				
		// ActionForward 객체 생성하여 포워딩 정보를 저장합니다.
		// => community 디렉토리 내의 Tmilist.co 페이지를 지정합니다.
		// => URL 및 request 객체 유지한 채 포워딩을 위해 Dispatcher 방식을 지정합니다.
		forward = new ActionForward();
		forward.setPath("community/tmiList.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
